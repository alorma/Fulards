package com.alorma.foulards;

import android.graphics.Color;

public enum FulardColor {
  blanc("#FFFFFF", "Blanc"),
  gris_clar("#cccccc", "Gris clar"),
  gris("#707070", "Gris"),
  negre("#212121", "Negre"),
  rosa("#cc33cc", "Rosa"),
  lila("#c290c5", "Lila"),
  morat("#771571", "Morat"),
  blau_cel("#0099cc", "Blau cel"),
  blau("#2a26b2", "Blau"),
  blau_mari("#191963", "Blau marí"),
  turquesa("#1aacb5", "Turquesa"),
  verd_llimona("#a4d412", "Verd llimona"),
  verd("#279c56", "Verd"),
  verd_fosc("#04574d", "Verd fosc"),
  groc("#fddc2b", "Groc"),
  ocre("#cccc00", "Ocre"),
  taronja_clar("#fd912f", "Taronja clar"),
  taronja("#fb5821", "Taronja"),
  vermell("#c80428", "Vermell"),
  grana("#660000", "Grana"),
  beix("#b5a584", "Beix"),
  marro("#5b4427", "Marró");

  private String colorHex;
  private String humanName;

  FulardColor(String colorHex, String humanName) {
    this.colorHex = colorHex;
    this.humanName = humanName;
  }

  public String getColorHex() {
    return colorHex;
  }

  public int getColorInt() {
    return Color.parseColor(colorHex);
  }

  public String getHumanName() {
    return humanName;
  }

  public void setHumanName(String humanName) {
    this.humanName = humanName;
  }
}
