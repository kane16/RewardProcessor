package pl.delukesoft.rewardprocessor.transaction.exception;

import org.springframework.http.HttpStatus;
import pl.delukesoft.rewardprocessor.utility.exception.LoggableResponseStatusException;

public class InvalidTransactionStatusException extends LoggableResponseStatusException {

  public InvalidTransactionStatusException() {
    super(HttpStatus.BAD_REQUEST, "Invalid transaction status provided");
  }

}
