package pl.delukesoft.rewardprocessor.transaction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(summary = "Create transaction for given data")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Transaction is persisted in DB",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = TransactionDTO.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid transaction status provided"),
      @ApiResponse(responseCode = "500", description = "DB operation failed")
  })
  @PostMapping
  public TransactionDTO createTransaction(@RequestBody TransactionDTO dto) {
    return transactionFacade.createTransaction(dto);
  }

  @Operation(summary = "Modify transaction for given data")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Transaction is modified and reflected in DB",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = TransactionDTO.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid transaction status provided"),
      @ApiResponse(responseCode = "422", description = "Inconsistent id for transaction provided. Id from header doesn't match one from body."),
      @ApiResponse(responseCode = "500", description = "DB operation failed")
  })
  @PutMapping("{id}")
  public TransactionDTO modifyTransaction(
      @RequestBody TransactionDTO dto,
      @PathVariable("id") Long id
  ) {
    return transactionFacade.modifyTransaction(dto, id);
  }

}
