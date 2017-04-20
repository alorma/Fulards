package com.alorma.foulards.view;

import com.alorma.foulards.FulardType;
import java.io.Serializable;

public class FulardCustomization implements Serializable {
  private int fulardColor;

  private int fulardDretaColor;
  private int fulardEsquerraColor;

  private int ribetColor;

  private int ribetDretaColor;
  private int ribetEsquerraColor;

  private int ribetExtern;
  private int ribetMiddle;
  private int ribetMiddleIntern;
  private int ribetMiddleExtern;
  private int ribetIntern;

  private boolean senyera;
  private FulardType type;

  public int getFulardColor() {
    return fulardColor;
  }

  public void setFulardColor(int fulardColor) {
    this.fulardColor = fulardColor;
  }

  public int getFulardDretaColor() {
    return fulardDretaColor;
  }

  public void setFulardDretaColor(int fulardDretaColor) {
    this.fulardDretaColor = fulardDretaColor;
  }

  public int getFulardEsquerraColor() {
    return fulardEsquerraColor;
  }

  public void setFulardEsquerraColor(int fulardEsquerraColor) {
    this.fulardEsquerraColor = fulardEsquerraColor;
  }

  public int getRibetColor() {
    return ribetColor;
  }

  public void setRibetColor(int ribetColor) {
    this.ribetColor = ribetColor;
  }

  public int getRibetDretaColor() {
    return ribetDretaColor;
  }

  public void setRibetDretaColor(int ribetDretaColor) {
    this.ribetDretaColor = ribetDretaColor;
  }

  public int getRibetEsquerraColor() {
    return ribetEsquerraColor;
  }

  public void setRibetEsquerraColor(int ribetEsquerraColor) {
    this.ribetEsquerraColor = ribetEsquerraColor;
  }

  public int getRibetExtern() {
    return ribetExtern;
  }

  public void setRibetExtern(int ribetExtern) {
    this.ribetExtern = ribetExtern;
  }

  public int getRibetMiddle() {
    return ribetMiddle;
  }

  public void setRibetMiddle(int ribetMiddle) {
    this.ribetMiddle = ribetMiddle;
  }

  public int getRibetMiddleIntern() {
    return ribetMiddleIntern;
  }

  public void setRibetMiddleIntern(int ribetMiddleIntern) {
    this.ribetMiddleIntern = ribetMiddleIntern;
  }

  public int getRibetMiddleExtern() {
    return ribetMiddleExtern;
  }

  public void setRibetMiddleExtern(int ribetMiddleExtern) {
    this.ribetMiddleExtern = ribetMiddleExtern;
  }

  public int getRibetIntern() {
    return ribetIntern;
  }

  public void setRibetIntern(int ribetIntern) {
    this.ribetIntern = ribetIntern;
  }

  public boolean isSenyera() {
    return senyera;
  }

  public void setSenyera(boolean senyera) {
    this.senyera = senyera;
  }

  public void setType(FulardType type) {
    this.type = type;
  }

  public FulardType getType() {
    return type;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FulardCustomization{");
    sb.append("fulardColor=").append(fulardColor);
    sb.append(", fulardDretaColor=").append(fulardDretaColor);
    sb.append(", fulardEsquerraColor=").append(fulardEsquerraColor);
    sb.append(", ribetColor=").append(ribetColor);
    sb.append(", ribetDretaColor=").append(ribetDretaColor);
    sb.append(", ribetEsquerraColor=").append(ribetEsquerraColor);
    sb.append(", ribetExtern=").append(ribetExtern);
    sb.append(", ribetMiddle=").append(ribetMiddle);
    sb.append(", ribetMiddleIntern=").append(ribetMiddleIntern);
    sb.append(", ribetMiddleExtern=").append(ribetMiddleExtern);
    sb.append(", ribetIntern=").append(ribetIntern);
    sb.append(", senyera=").append(senyera);
    sb.append(", type=").append(type);
    sb.append('}');
    return sb.toString();
  }
}
