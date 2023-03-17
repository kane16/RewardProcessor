package pl.delukesoft.rewardprocessor.transaction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.delukesoft.rewardprocessor.transaction.domain.Transaction;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionService;
import pl.delukesoft.rewardprocessor.transaction.exception.InconsistentTransactionIdException;

@Service
@RequiredArgsConstructor
public class TransactionFacade {

  private final TransactionMapper transactionMapper;
  private final TransactionService transactionService;

  public TransactionDTO createTransaction(TransactionDTO dto) {
    Transaction dbTransaction = transactionService.createTransaction(transactionMapper.mapToDomain(dto));
    return transactionMapper.mapToTransfer(dbTransaction);
  }

  public TransactionDTO modifyTransaction(TransactionDTO dto, Long id) {
    if(dto.id != null && dto.id.equals(id)) {
      throw new InconsistentTransactionIdException();
    } else {
      dto.id = id;
    }
    Transaction modifiedDbTransaction = transactionService.modifyTransaction(transactionMapper.mapToDomain(dto));
    return transactionMapper.mapToTransfer(modifiedDbTransaction);
  }
}
