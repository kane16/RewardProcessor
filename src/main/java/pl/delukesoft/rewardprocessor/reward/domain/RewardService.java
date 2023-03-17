package pl.delukesoft.rewardprocessor.reward.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.delukesoft.rewardprocessor.transaction.domain.Transaction;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionPeriod;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionService;

@Service
@RequiredArgsConstructor
public class RewardService {

  private final TransactionService transactionService;

  public Reward countNumberOfPointsForPeriodAndCustomer(TransactionPeriod period, Long customerId) {
    List<Transaction> transactions = transactionService.getCompletedTransactionsForPeriodAndCustomer(
        period,
        customerId
    );
    long totalNumberOfPoints = transactions.stream()
        .map(this::countPointsForTransaction)
        .reduce(0L, Long::sum);
    return new Reward(period.getFrom(), period.getTo(), totalNumberOfPoints);
  }

  public long countPointsForTransaction(Transaction transaction) {
    int numberOfPointsAward = 0;
    if (transaction.getAmount() > 50) {
      numberOfPointsAward += transaction.getAmount() - 50;
    }
    if (transaction.getAmount() > 100) {
      numberOfPointsAward += transaction.getAmount() - 100;
    }
    return numberOfPointsAward;
  }


}
