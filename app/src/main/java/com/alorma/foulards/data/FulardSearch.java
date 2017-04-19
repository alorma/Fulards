package com.alorma.foulards.data;

import com.alorma.foulards.FulardColor;
import com.alorma.foulards.FulardType;
import java.io.Serializable;

public class FulardSearch implements Serializable {
  private FulardType fulardType;

  private FulardColor fulardColor;
  private FulardColor fulardColorDreta;
  private FulardColor fulardColorEsquerra;

  private FulardColor ribetColor;

  private FulardColor ribetDretaColor;
  private FulardColor ribetEsquerraColor;

  private FulardColor ribetExtern;
  private FulardColor ribetIntern;

  private FulardColor ribetMiddle;

  private FulardColor ribetMiddleIntern;
  private FulardColor ribetMiddleExtern;

  private boolean senyera;

  public FulardType getFulardType() {
    return fulardType;
  }

  public void setFulardType(FulardType fulardType) {
    this.fulardType = fulardType;
  }

  public FulardColor getFulardColor() {
    return fulardColor;
  }

  public void setFulardColor(FulardColor fulardColor) {
    this.fulardColor = fulardColor;
  }

  public FulardColor getFulardColorDreta() {
    return fulardColorDreta;
  }

  public void setFulardColorDreta(FulardColor fulardColorDreta) {
    this.fulardColorDreta = fulardColorDreta;
  }

  public FulardColor getFulardColorEsquerra() {
    return fulardColorEsquerra;
  }

  public void setFulardColorEsquerra(FulardColor fulardColorEsquerra) {
    this.fulardColorEsquerra = fulardColorEsquerra;
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

  public FulardColor getRibetIntern() {
    return ribetIntern;
  }

  public void setRibetIntern(FulardColor ribetIntern) {
    this.ribetIntern = ribetIntern;
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

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FulardSearch{");
    if (fulardType != null) {
      sb.append("fulardType=").append(fulardType);
    }
    if (fulardColor != null) {
      sb.append(", fulardColor=").append(fulardColor);
    }
    if (fulardColorDreta != null) {
      sb.append(", fulardColorDreta=").append(fulardColorDreta);
    }
    if (fulardColorEsquerra != null) {
      sb.append(", fulardColorEsquerra=").append(fulardColorEsquerra);
    }
    if (ribetColor != null) {
      sb.append(", ribetColor=").append(ribetColor);
    }
    if (ribetDretaColor != null) {
      sb.append(", ribetDretaColor=").append(ribetDretaColor);
    }
    if (ribetEsquerraColor != null) {
      sb.append(", ribetEsquerraColor=").append(ribetEsquerraColor);
    }
    if (ribetExtern != null) {
      sb.append(", ribetExtern=").append(ribetExtern);
    }
    if (ribetIntern != null) {
      sb.append(", ribetIntern=").append(ribetIntern);
    }
    if (ribetMiddle != null) {
      sb.append(", ribetMiddle=").append(ribetMiddle);
    }
    if (ribetMiddleIntern != null) {
      sb.append(", ribetMiddleIntern=").append(ribetMiddleIntern);
    }
    if (ribetMiddleExtern != null) {
      sb.append(", ribetMiddleExtern=").append(ribetMiddleExtern);
    }
    sb.append('}');
    return sb.toString();
  }

  public boolean isSenyera() {
    return senyera;
  }

  public void setSenyera(boolean senyera) {
    this.senyera = senyera;
  }
}
