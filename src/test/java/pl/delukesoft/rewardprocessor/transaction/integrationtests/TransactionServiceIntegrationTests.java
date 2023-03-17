package pl.delukesoft.rewardprocessor.transaction.integrationtests;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import pl.delukesoft.rewardprocessor.transaction.domain.Transaction;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionPeriod;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionService;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionStatus;
import pl.delukesoft.rewardprocessor.transaction.exception.InvalidTransactionState;
import pl.delukesoft.rewardprocessor.transaction.exception.InvalidTransactionStatusException;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class TransactionServiceIntegrationTests {

  private final Long DEFAULT_CLIENT = 3L;
  @Autowired
  private TransactionService transactionService;

  private Transaction buildTransactionWithAmountAndStatusAndCreatedOn(
      double amount,
      TransactionStatus status,
      LocalDateTime createdOn
  ) {
    return new Transaction(
        null,
        amount,
        status,
        createdOn,
        DEFAULT_CLIENT,
        null
    );
  }

  @Test
  public void createTransactionSuccessful() {
    Transaction transaction = buildTransactionWithAmountAndStatusAndCreatedOn(
        100.0,
        TransactionStatus.COMPLETED,
        LocalDateTime.now()
    );
    Transaction dbTransaction = transactionService.createTransaction(transaction);
    Assertions.assertNotNull(dbTransaction.getVersion());
    Assertions.assertNotNull(dbTransaction.getId());
  }

  @Test
  public void modifyTransactionSuccessful() {
    Transaction transaction = buildTransactionWithAmountAndStatusAndCreatedOn(
        100.0,
        TransactionStatus.COMPLETED,
        LocalDateTime.now()
    );
    Transaction dbTransaction = transactionService.createTransaction(transaction);
    Assertions.assertEquals(100.0, dbTransaction.getAmount());
    dbTransaction.setAmount(200.0);
    Transaction modifiedTransaction = transactionService.modifyTransaction(dbTransaction);
    Assertions.assertEquals(200.0, modifiedTransaction.getAmount());
  }

  @Test
  public void modifyTransaction_FailedWithOptimisticLock() {
    Transaction transaction = buildTransactionWithAmountAndStatusAndCreatedOn(
        100.0,
        TransactionStatus.COMPLETED,
        LocalDateTime.now()
    );
    Transaction dbTransaction = transactionService.createTransaction(transaction);
    Assertions.assertEquals(100.0, dbTransaction.getAmount());
    dbTransaction.setAmount(200.0);
    Transaction modifiedTransaction = transactionService.modifyTransaction(dbTransaction);
    Assertions.assertEquals(200.0, modifiedTransaction.getAmount());
    dbTransaction.setStatus(TransactionStatus.CANCELLED);
    Assertions.assertThrows(
        InvalidTransactionState.class,
        () -> transactionService.modifyTransaction(dbTransaction)
    );
  }

  @Test
  public void getTransactionsWithDifferentCreationDateTime() {
    Stream.of(
            buildTransactionWithAmountAndStatusAndCreatedOn(
                100.0,
                TransactionStatus.COMPLETED,
                LocalDateTime.now()
            ),
            buildTransactionWithAmountAndStatusAndCreatedOn(
                100.0,
                TransactionStatus.COMPLETED,
                LocalDateTime.now().minus(
                    4,
                    ChronoUnit.MONTHS
                )
            ),
            buildTransactionWithAmountAndStatusAndCreatedOn(
                100.0,
                TransactionStatus.COMPLETED,
                LocalDateTime.now().minus(
                    2,
                    ChronoUnit.MONTHS
                )
            )
        )
        .forEach(transaction -> {
          transactionService.createTransaction(transaction);
        });
    TransactionPeriod period = new TransactionPeriod(
        LocalDateTime.now()
            .minus(3, ChronoUnit.MONTHS),
        LocalDateTime.now()
    );
    List<Transaction> resultTransactions = transactionService.getCompletedTransactionsForPeriodAndCustomer(
        period,
        DEFAULT_CLIENT
    );
    Assertions.assertEquals(2, resultTransactions.size());
  }

  @Test
  public void getTransactionsWithDifferentStatus() {
    Stream.of(
            buildTransactionWithAmountAndStatusAndCreatedOn(
                100.0,
                TransactionStatus.COMPLETED,
                LocalDateTime.now()
            ),
            buildTransactionWithAmountAndStatusAndCreatedOn(
                100.0,
                TransactionStatus.PENDING,
                LocalDateTime.now()
            ),
            buildTransactionWithAmountAndStatusAndCreatedOn(
                100.0,
                TransactionStatus.CANCELLED,
                LocalDateTime.now()
            )
        )
        .forEach(transaction -> {
          transactionService.createTransaction(transaction);
        });
    TransactionPeriod period = new TransactionPeriod(
        LocalDateTime.now()
            .minus(3, ChronoUnit.MONTHS),
        LocalDateTime.now()
    );
    List<Transaction> resultTransactions = transactionService.getCompletedTransactionsForPeriodAndCustomer(
        period,
        DEFAULT_CLIENT
    );
    Assertions.assertEquals(1, resultTransactions.size());
  }


}
