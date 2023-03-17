package pl.delukesoft.rewardprocessor.transaction.controller;

import java.util.Arrays;
import org.springframework.stereotype.Service;
import pl.delukesoft.rewardprocessor.transaction.domain.Transaction;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionStatus;
import pl.delukesoft.rewardprocessor.transaction.exception.InvalidTransactionStatusException;
import pl.delukesoft.rewardprocessor.utility.BaseMapper;

@Service
public class TransactionMapper extends BaseMapper<TransactionDTO, Transaction> {

  @Override
  public Transaction mapToDomain(TransactionDTO dto) {
    TransactionStatus transactionStatus = Arrays.stream(TransactionStatus.values())
        .filter(status -> dto.status.equals(status.getName()))
        .findFirst()
        .orElseThrow(InvalidTransactionStatusException::new);
    return new Transaction(
        dto.id,
        dto.amount,
        transactionStatus,
        dto.createdOn,
        dto.customerId,
        dto.version
    );
  }



  @Override
  public TransactionDTO mapToTransfer(Transaction entity) {
    return new TransactionDTO(
        entity.getId(),
        entity.getAmount(),
        entity.getStatus().toString(),
        entity.getCreatedOn(),
        entity.getCustomerId(),
        entity.getVersion()
    );
  }
}
