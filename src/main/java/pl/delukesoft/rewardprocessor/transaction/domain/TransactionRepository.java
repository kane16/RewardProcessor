package pl.delukesoft.rewardprocessor.transaction.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.delukesoft.rewardprocessor.transaction.domain.Transaction;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionStatus;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findAllByCreatedOnBetweenAndStatusAndCustomerId(
      LocalDateTime from,
      LocalDateTime to,
      TransactionStatus status,
      Long customerId
  );

}
