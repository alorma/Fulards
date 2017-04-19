package com.alorma.foulards.data;

public class Agrupament {
  public String name;
  public boolean verified;

  public Agrupament() {

  }

  public Agrupament(String name, boolean verified) {
    this.name = name;
    this.verified = verified;
  }

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
}
