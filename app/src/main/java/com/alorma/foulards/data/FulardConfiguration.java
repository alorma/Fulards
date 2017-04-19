package com.alorma.foulards.data;

import com.alorma.foulards.FulardColor;
import com.alorma.foulards.FulardType;
import java.io.Serializable;

public class FulardConfiguration implements Serializable {
  private FulardType fulardType;
  private FulardColor fulardColor;
  private FulardColor fulardDretaColor;
  private FulardColor fulardEsquerraColor;
  private FulardColor ribetColor;
  private FulardColor ribetDretaColor;
  private FulardColor ribetEsquerraColor;
  private FulardColor ribetExtern;
  private FulardColor ribetMiddle;
  private FulardColor ribetIntern;
  private FulardColor ribetMiddleIntern;
  private FulardColor ribetMiddleExtern;
  private FulardColor ribetFulardColorern;
  private boolean senyera;

  public FulardConfiguration() {

  }

  public FulardConfiguration(FulardType fulardType, FulardColor fulardColor, FulardColor fulardDretaColor,
      FulardColor fulardEsquerraColor, FulardColor ribetColor, FulardColor ribetDretaColor,
      FulardColor ribetEsquerraColor, FulardColor ribetExtern, FulardColor ribetMiddle, FulardColor ribetIntern,
      FulardColor ribetMiddleIntern, FulardColor ribetMiddleExtern, FulardColor ribetFulardColorern, boolean senyera) {
    this.fulardType = fulardType;
    this.fulardColor = fulardColor;
    this.fulardDretaColor = fulardDretaColor;
    this.fulardEsquerraColor = fulardEsquerraColor;
    this.ribetColor = ribetColor;
    this.ribetDretaColor = ribetDretaColor;
    this.ribetEsquerraColor = ribetEsquerraColor;
    this.ribetExtern = ribetExtern;
    this.ribetMiddle = ribetMiddle;
    this.ribetIntern = ribetIntern;
    this.ribetMiddleIntern = ribetMiddleIntern;
    this.ribetMiddleExtern = ribetMiddleExtern;
    this.ribetFulardColorern = ribetFulardColorern;
    this.senyera = senyera;
  }

  public FulardColor getFulardColor() {
    return fulardColor;
  }

  public void setFulardColor(FulardColor fulardColor) {
    this.fulardColor = fulardColor;
  }

  public FulardColor getFulardDretaColor() {
    return fulardDretaColor;
  }

  public void setFulardDretaColor(FulardColor fulardDretaColor) {
    this.fulardDretaColor = fulardDretaColor;
  }

  public FulardColor getFulardEsquerraColor() {
    return fulardEsquerraColor;
  }

  public void setFulardEsquerraColor(FulardColor fulardEsquerraColor) {
    this.fulardEsquerraColor = fulardEsquerraColor;
  }

  public FulardColor getRibetColor() {
    return ribetColor;
  }

  public void setRibetColor(FulardColor ribetColor) {
    this.ribetColor = ribetColor;
  }

  public FulardColor getRibetDretaColor() {
    return ribetDretaColor;
  }

  public void setRibetDretaColor(FulardColor ribetDretaColor) {
    this.ribetDretaColor = ribetDretaColor;
  }

  public FulardColor getRibetEsquerraColor() {
    return ribetEsquerraColor;
  }

  public void setRibetEsquerraColor(FulardColor ribetEsquerraColor) {
    this.ribetEsquerraColor = ribetEsquerraColor;
  }

  public FulardColor getRibetExtern() {
    return ribetExtern;
  }

  public void setRibetExtern(FulardColor ribetExtern) {
    this.ribetExtern = ribetExtern;
  }

  public FulardColor getRibetMiddle() {
    return ribetMiddle;
  }

  public void setRibetMiddle(FulardColor ribetMiddle) {
    this.ribetMiddle = ribetMiddle;
  }

  public FulardColor getRibetMiddleIntern() {
    return ribetMiddleIntern;
  }

  public void setRibetMiddleIntern(FulardColor ribetMiddleIntern) {
    this.ribetMiddleIntern = ribetMiddleIntern;
  }

  public FulardColor getRibetMiddleExtern() {
    return ribetMiddleExtern;
  }

  public void setRibetMiddleExtern(FulardColor ribetMiddleExtern) {
    this.ribetMiddleExtern = ribetMiddleExtern;
  }

  public FulardColor getRibetFulardColorern() {
    return ribetFulardColorern;
  }

  public void setRibetFulardColorern(FulardColor ribetFulardColorern) {
    this.ribetFulardColorern = ribetFulardColorern;
  }

  public boolean isSenyera() {
    return senyera;
  }

  public void setSenyera(boolean senyera) {
    this.senyera = senyera;
  }

  public FulardType getFulardType() {
    return fulardType;
  }

  public void setFulardType(FulardType fulardType) {
    this.fulardType = fulardType;
  }

  public FulardColor getRibetIntern() {
    return ribetIntern;
  }

  public void setRibetIntern(FulardColor ribetIntern) {
    this.ribetIntern = ribetIntern;
  }
}
