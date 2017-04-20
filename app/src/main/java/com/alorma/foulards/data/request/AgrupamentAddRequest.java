package com.alorma.foulards.data.request;

import com.alorma.foulards.data.Agrupament;

public class AgrupamentAddRequest {
  private Agrupament agrupament;
  private FulardRequest fulardRequest;

  public AgrupamentAddRequest(Agrupament agrupament, FulardRequest fulardRequest) {
    this.agrupament = agrupament;
    this.fulardRequest = fulardRequest;
  }

  public Agrupament getAgrupament() {
    return agrupament;
  }

  public void setAgrupament(Agrupament agrupament) {
    this.agrupament = agrupament;
  }

  public FulardRequest getFulardRequest() {
    return fulardRequest;
  }

  public void setFulardRequest(FulardRequest fulardRequest) {
    this.fulardRequest = fulardRequest;
  }
}
