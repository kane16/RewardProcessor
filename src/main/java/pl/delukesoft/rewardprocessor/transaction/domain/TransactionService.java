package pl.delukesoft.rewardprocessor.transaction.domain;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import pl.delukesoft.rewardprocessor.transaction.exception.DbOperationFailedException;
import pl.delukesoft.rewardprocessor.transaction.exception.InvalidTransactionState;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepository;

  @Transactional
  public Transaction createTransaction(Transaction transaction) {
    transaction.setId(null);
    try {
      return transactionRepository.save(transaction);
    } catch (Exception exc) {
      throw new DbOperationFailedException();
    }
  }

  @Transactional
  public Transaction modifyTransaction(Transaction transaction) {
    try {
      return transactionRepository.save(transaction);
    } catch (OptimisticLockingFailureException exception) {
      throw new InvalidTransactionState(exception);
    } catch (Exception exc) {
      throw new DbOperationFailedException();
    }
  }

  public List<Transaction> getCompletedTransactionsForPeriodAndCustomer(
      TransactionPeriod period,
      Long customerId
  ) {
    return transactionRepository.findAllByCreatedOnBetweenAndStatusAndCustomerId(
        period.getFrom(),
        period.getTo(),
        TransactionStatus.COMPLETED,
        customerId
    );
  }
}
