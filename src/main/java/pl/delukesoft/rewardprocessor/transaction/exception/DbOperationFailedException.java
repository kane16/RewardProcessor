package pl.delukesoft.rewardprocessor.transaction.exception;

import org.springframework.http.HttpStatus;
import pl.delukesoft.rewardprocessor.utility.exception.LoggableResponseStatusException;

public class DbOperationFailedException extends LoggableResponseStatusException {

  public DbOperationFailedException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, "DB operation failed");
  }
}
