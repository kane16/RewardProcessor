package pl.delukesoft.rewardprocessor.transaction.controller;


import java.time.LocalDateTime;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

  public Long id;
  @NotNull
  @DecimalMin(value = "0", inclusive = false)
  public Double amount;
  @NotNull
  public String status;
  @NotNull
  @PastOrPresent
  public LocalDateTime createdOn;
  @NotNull
  public Long customerId;
  public Long version;

}
