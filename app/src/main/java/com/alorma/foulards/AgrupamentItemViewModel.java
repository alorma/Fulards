package com.alorma.foulards;

public class AgrupamentItemViewModel {
  private String name;
  private boolean verified;

  public String getName() {
    return name;
  }

  public boolean isVerified() {
    return verified;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVerified(boolean verified) {
    this.verified = verified;
  }
}
