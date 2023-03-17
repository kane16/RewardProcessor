package pl.delukesoft.rewardprocessor.reward.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rewards")
@RequiredArgsConstructor
public class RewardController {

  private final RewardFacade rewardFacade;

  @GetMapping("{customerId}")
  public RewardDTO getTotalRewardFromLastThreeMonths(
      @NotNull @Min(0L) @PathVariable("customerId") Long customerId
  ) {
    return rewardFacade.getTotalRewardFromNumberOfMonths(3, customerId);
  }

  @GetMapping("{customerId}/period/months/{numberOfMonths}")
  public RewardDTO getTotalRewardForCustomNumberOfMonths(
      @NotNull @Min(0L) @PathVariable("customerId") Long customerId,
      @NotNull @Min(1L) @PathVariable("numberOfMonths") Integer numberOfMonths
  ) {
    return rewardFacade.getTotalRewardFromNumberOfMonths(numberOfMonths, customerId);
  }


}
