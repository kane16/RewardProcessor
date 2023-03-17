package pl.delukesoft.rewardprocessor.reward.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Reward {

  private LocalDateTime from;
  private LocalDateTime to;
  private Long numberOfPoints;

}
