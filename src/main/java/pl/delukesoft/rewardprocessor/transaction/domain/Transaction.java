package pl.delukesoft.rewardprocessor.transaction.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private Double amount;

  @Enumerated(EnumType.STRING)
  private TransactionStatus status;

  private LocalDateTime createdOn;

  private Long customerId;

  @Version
  private Long version;

}
