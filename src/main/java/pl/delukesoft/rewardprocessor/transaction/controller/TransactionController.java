package pl.delukesoft.rewardprocessor.transaction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionFacade transactionFacade;

  @PostMapping
  public TransactionDTO createTransaction(@RequestBody TransactionDTO dto) {
    return transactionFacade.createTransaction(dto);
  }

  @PutMapping("{id}")
  public TransactionDTO modifyTransaction(@RequestBody TransactionDTO dto, @PathVariable("id") Long id) {
    return transactionFacade.modifyTransaction(dto, id);
  }

}
