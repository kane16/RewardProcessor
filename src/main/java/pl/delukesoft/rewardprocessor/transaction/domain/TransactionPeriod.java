package pl.delukesoft.rewardprocessor.transaction.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransactionPeriod {

  LocalDateTime from;
  LocalDateTime to;

}
