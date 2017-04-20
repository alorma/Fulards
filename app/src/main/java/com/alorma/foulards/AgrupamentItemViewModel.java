package com.alorma.foulards;

import com.alorma.foulards.view.FulardCustomization;

public class AgrupamentItemViewModel {
  private String name;
  private boolean verified;
  private FulardCustomization fulardCustomization;

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

  public void setFulardCustomization(FulardCustomization fulardCustomization) {
    this.fulardCustomization = fulardCustomization;
  }

  public FulardCustomization getFulardCustomization() {
    return fulardCustomization;
  }
}
