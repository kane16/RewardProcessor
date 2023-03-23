package pl.delukesoft.rewardprocessor.transaction.controller;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
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
