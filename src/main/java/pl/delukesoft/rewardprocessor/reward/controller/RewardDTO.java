package pl.delukesoft.rewardprocessor.reward.controller;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RewardDTO {

  public LocalDateTime from;
  public LocalDateTime to;
  public Long numberOfPoints;

}
