package pl.delukesoft.rewardprocessor.reward.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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


  @Operation(summary = "Calculate reward for given customer 3 months back")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Calculated reward is returned",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RewardDTO.class))})
  })
  @GetMapping("{customerId}")
  public RewardDTO getTotalRewardFromLastThreeMonths(
      @NotNull @Min(0L) @PathVariable("customerId") Long customerId
  ) {
    return rewardFacade.getTotalRewardFromNumberOfMonths(3, customerId);
  }

  @Operation(summary = "Calculate reward for given customer and given number of months")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Calculated reward is returned",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RewardDTO.class))})
  })
  @GetMapping("{customerId}/period/months/{numberOfMonths}")
  public RewardDTO getTotalRewardForCustomNumberOfMonths(
      @NotNull @Min(0L) @PathVariable("customerId") Long customerId,
      @NotNull @Min(1L) @PathVariable("numberOfMonths") Integer numberOfMonths
  ) {
    return rewardFacade.getTotalRewardFromNumberOfMonths(numberOfMonths, customerId);
  }


}
