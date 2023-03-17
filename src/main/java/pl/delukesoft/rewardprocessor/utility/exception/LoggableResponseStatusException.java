package pl.delukesoft.rewardprocessor.utility.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoggableResponseStatusException extends ResponseStatusException {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public LoggableResponseStatusException(HttpStatus status, String message, Exception exception) {
    super(status, message);
    logger.error(message, exception);
  }

  public LoggableResponseStatusException(HttpStatus status, String message) {
    super(status, message);
    logger.error(message);
  }

}
