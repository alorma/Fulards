package com.alorma.foulards.data.request;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FulardRequest {
  public String fulardType;
  public String fulardColor;
  public String fulardColorDreta;
  public String fulardColorEsquerra;
  public String ribetColor;
  public String ribetDretaColor;
  public String ribetEsquerraColor;
  public String ribetExtern;
  public String ribetIntern;
  public String ribetMiddle;
  public String ribetMiddleIntern;
  public String ribetMiddleExtern;

  public FulardRequest() {
  }

  public FulardRequest(String fulardType, String fulardColor, String fulardColorDreta, String fulardColorEsquerra,
      String ribetColor, String ribetDretaColor, String ribetEsquerraColor, String ribetExtern, String ribetIntern,
      String ribetMiddle, String ribetMiddleIntern, String ribetMiddleExtern) {
    this.fulardType = fulardType;
    this.fulardColor = fulardColor;
    this.fulardColorDreta = fulardColorDreta;
    this.fulardColorEsquerra = fulardColorEsquerra;
    this.ribetColor = ribetColor;
    this.ribetDretaColor = ribetDretaColor;
    this.ribetEsquerraColor = ribetEsquerraColor;
    this.ribetExtern = ribetExtern;
    this.ribetIntern = ribetIntern;
    this.ribetMiddle = ribetMiddle;
    this.ribetMiddleIntern = ribetMiddleIntern;

    this.ribetMiddleExtern = ribetMiddleExtern;
  }

  public String getFulardType() {
    return fulardType;
  }

  public void setFulardType(String fulardType) {
    this.fulardType = fulardType;
  }

  public String getFulardColor() {
    return fulardColor;
  }

  public void setFulardColor(String fulardColor) {
    this.fulardColor = fulardColor;
  }

  public String getFulardColorDreta() {
    return fulardColorDreta;
  }

  public void setFulardColorDreta(String fulardColorDreta) {
    this.fulardColorDreta = fulardColorDreta;
  }

  public String getFulardColorEsquerra() {
    return fulardColorEsquerra;
  }

  public void setFulardColorEsquerra(String fulardColorEsquerra) {
    this.fulardColorEsquerra = fulardColorEsquerra;
  }

  public String getRibetColor() {
    return ribetColor;
  }

  public void setRibetColor(String ribetColor) {
    this.ribetColor = ribetColor;
  }

  public String getRibetDretaColor() {
    return ribetDretaColor;
  }

  public void setRibetDretaColor(String ribetDretaColor) {
    this.ribetDretaColor = ribetDretaColor;
  }

  public String getRibetEsquerraColor() {
    return ribetEsquerraColor;
  }

  public void setRibetEsquerraColor(String ribetEsquerraColor) {
    this.ribetEsquerraColor = ribetEsquerraColor;
  }

  public String getRibetExtern() {
    return ribetExtern;
  }

  public void setRibetExtern(String ribetExtern) {
    this.ribetExtern = ribetExtern;
  }

  public String getRibetIntern() {
    return ribetIntern;
  }

  public void setRibetIntern(String ribetIntern) {
    this.ribetIntern = ribetIntern;
  }

  public String getRibetMiddle() {
    return ribetMiddle;
  }

  public void setRibetMiddle(String ribetMiddle) {
    this.ribetMiddle = ribetMiddle;
  }

  public String getRibetMiddleIntern() {
    return ribetMiddleIntern;
  }

  public void setRibetMiddleIntern(String ribetMiddleIntern) {
    this.ribetMiddleIntern = ribetMiddleIntern;
  }

  public String getRibetMiddleExtern() {
    return ribetMiddleExtern;
  }

  public void setRibetMiddleExtern(String ribetMiddleExtern) {
    this.ribetMiddleExtern = ribetMiddleExtern;
  }
}
