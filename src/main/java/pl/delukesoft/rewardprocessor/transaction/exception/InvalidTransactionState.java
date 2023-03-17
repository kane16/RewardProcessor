package pl.delukesoft.rewardprocessor.transaction.exception;

import org.springframework.http.HttpStatus;
import pl.delukesoft.rewardprocessor.utility.exception.LoggableResponseStatusException;

public class InvalidTransactionState extends LoggableResponseStatusException {

  public InvalidTransactionState(
      Exception exception
  ) {
    super(HttpStatus.CONFLICT, "Invalid state of transaction to modify", exception);
  }
}
