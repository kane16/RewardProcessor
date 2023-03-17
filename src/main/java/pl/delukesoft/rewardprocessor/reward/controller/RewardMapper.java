package pl.delukesoft.rewardprocessor.reward.controller;

import org.springframework.stereotype.Service;
import pl.delukesoft.rewardprocessor.reward.domain.Reward;
import pl.delukesoft.rewardprocessor.utility.BaseMapper;

@Service
public class RewardMapper extends BaseMapper<RewardDTO, Reward> {

  @Override
  public Reward mapToDomain(RewardDTO dto) {
    return new Reward(dto.from, dto.to, dto.numberOfPoints);
  }

  @Override
  public RewardDTO mapToTransfer(Reward entity) {
    return new RewardDTO(entity.getFrom(), entity.getTo(), entity.getNumberOfPoints());
  }
}
