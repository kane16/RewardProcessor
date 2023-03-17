package pl.delukesoft.rewardprocessor.reward.unittests;


import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.delukesoft.rewardprocessor.reward.domain.Reward;
import pl.delukesoft.rewardprocessor.reward.domain.RewardService;
import pl.delukesoft.rewardprocessor.transaction.domain.Transaction;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionPeriod;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionService;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionStatus;

@ExtendWith({MockitoExtension.class})
public class RewardServiceUnitTests {

  @Mock
  private TransactionService transactionService;

  @InjectMocks
  private RewardService rewardService;

  private final Long DEFAULT_CLIENT = 3L;

  private static Stream<Arguments> valuesBetween51And100() {
    return Stream.of(
        Arguments.of(51.0, 1),
        Arguments.of(60.0, 10),
        Arguments.of(80.0, 30),
        Arguments.of(100.0, 50)
    );
  }

  private static Stream<Arguments> valuesOver100() {
    return Stream.of(
        Arguments.of(101, 52),
        Arguments.of(120, 90),
        Arguments.of(200, 250)
    );
  }

  private Transaction createTransactionWithIdAndAmount(Long id, double amount) {
    return new Transaction(
        id,
        amount,
        TransactionStatus.COMPLETED,
        LocalDateTime.now(),
        DEFAULT_CLIENT,
        1L
    );
  }

  @ParameterizedTest
  @ValueSource(doubles = {0.0, 10.0, 20.0, 30.0, 40.0, 50.0})
  public void whenTransactionAmount_BelowOrEqualTo50_ZeroPoints(double amount) {
    Transaction transaction = createTransactionWithIdAndAmount(1L, amount);
    long actualNumberOfPoints = rewardService.countPointsForTransaction(transaction);
    Assertions.assertEquals(0, actualNumberOfPoints);
  }

  @ParameterizedTest
  @MethodSource("valuesBetween51And100")
  public void whenTransactionAmount_Between51And100_OnePointOver50(
      double amount,
      long expectedNumberOfPoints
  ) {
    Transaction transaction = createTransactionWithIdAndAmount(1L, amount);
    long actualNumberOfPoints = rewardService.countPointsForTransaction(transaction);
    Assertions.assertEquals(expectedNumberOfPoints, actualNumberOfPoints);
  }

  @ParameterizedTest
  @MethodSource("valuesOver100")
  public void whenTransactionAmount_Over100_OnePointOver50AndTwoPointsOver100(
      double amount,
      long expectedNumberOfPoints
  ) {
    Transaction transaction = createTransactionWithIdAndAmount(1L, amount);
    long actualNumberOfPoints = rewardService.countPointsForTransaction(transaction);
    Assertions.assertEquals(expectedNumberOfPoints, actualNumberOfPoints);
  }

  @Test
  public void whenCountNumberOfPointsForPeriodAndCustomer_RewardReturn() {
    List<Transaction> transactions = List.of(
        createTransactionWithIdAndAmount(2L, 70),
        createTransactionWithIdAndAmount(3L, 120),
        createTransactionWithIdAndAmount(4L, 120)
    );
    TransactionPeriod period = new TransactionPeriod(
        LocalDateTime.now()
            .minus(3, ChronoUnit.MONTHS),
        LocalDateTime.now()
    );
    when(transactionService.getCompletedTransactionsForPeriodAndCustomer(period, DEFAULT_CLIENT)).thenReturn(transactions);
    Reward reward = rewardService.countNumberOfPointsForPeriodAndCustomer(period, DEFAULT_CLIENT);
    Assertions.assertEquals(200L, reward.getNumberOfPoints());
  }


}
