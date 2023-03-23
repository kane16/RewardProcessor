package pl.delukesoft.rewardprocessor.transaction.exception;

import org.springframework.http.HttpStatus;
import pl.delukesoft.rewardprocessor.utility.exception.LoggableResponseStatusException;

public class InconsistentTransactionIdException extends LoggableResponseStatusException {

  public InconsistentTransactionIdException() {
    super(HttpStatus.UNPROCESSABLE_ENTITY, "Inconsistent id for transaction provided. Id from header doesn't match one from body.");
  }
}
