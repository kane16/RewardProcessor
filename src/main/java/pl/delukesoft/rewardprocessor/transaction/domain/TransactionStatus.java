package pl.delukesoft.rewardprocessor.transaction.domain;

import lombok.Getter;

@Getter
public enum TransactionStatus {
  COMPLETED("completed"),
  CANCELLED("cancelled"),
  PENDING("cancelled");

  private final String name;

  TransactionStatus(String name) {
    this.name = name;
  }

}
