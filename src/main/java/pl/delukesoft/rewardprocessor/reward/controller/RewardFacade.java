package pl.delukesoft.rewardprocessor.reward.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.delukesoft.rewardprocessor.reward.domain.RewardService;
import pl.delukesoft.rewardprocessor.transaction.domain.TransactionPeriod;

@Service
@RequiredArgsConstructor
public class RewardFacade {

  private final RewardService rewardService;
  private final RewardMapper rewardMapper;

  public RewardDTO getTotalRewardFromNumberOfMonths(Integer numberOfMonths, Long customerId) {
    LocalDateTime from = LocalDateTime.now().minus(numberOfMonths, ChronoUnit.MONTHS);
    LocalDateTime to = LocalDateTime.now();
    TransactionPeriod period = new TransactionPeriod(from, to);
    return rewardMapper.mapToTransfer(rewardService.countNumberOfPointsForPeriodAndCustomer(
        period,
        customerId
    ));
  }
}
