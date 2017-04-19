package com.alorma.foulards;

import com.alorma.foulards.data.FulardConfiguration;

public class AgrupamentItemViewModel {
  private String name;
  private boolean verified;
  private FulardConfiguration fulardConfiguration;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isVerified() {
    return verified;
  }

  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  public void setFulardConfiguration(FulardConfiguration fulardConfiguration) {
    this.fulardConfiguration = fulardConfiguration;
  }

  public FulardConfiguration getFulardConfiguration() {
    return fulardConfiguration;
  }
}
