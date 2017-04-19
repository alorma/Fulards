package com.alorma.foulards;

public enum FulardType {
  fulard_1(Base.simple),
  fulard_2(Base.simple, Ribet.un),
  fulard_3(Base.simple, Ribet.dos),
  fulard_4(Base.simple, Ribet.tres),
  fulard_5(Base.simple, Ribet.cuatre),
  fulard_6(Base.doble),
  fulard_7(Base.doble, Ribet.un),
  fulard_8(Base.simple, Ribet.dos_colors),
  fulard_9(Base.doble, Ribet.dos_colors),
  fulard_10(Base.simple),
  fulard_11(Base.simple, Ribet.un),
  fulard_12(Base.simple, Ribet.dos),
  fulard_13(Base.simple, Ribet.senyera),
  fulard_14(Base.simple, Ribet.senyera_extern);

  public enum Base {
    simple,
    doble;
  }

  public enum Ribet {
    none,
    un,
    dos_colors,
    dos,
    tres,
    cuatre,
    senyera,
    senyera_extern;
  }

  private final Base base;
  private final Ribet ribet;

  FulardType(Base base) {
    this.base = base;
    this.ribet = Ribet.none;
  }

  FulardType(Base base, Ribet ribet) {
    this.base = base;
    this.ribet = ribet;
  }

  public Base getBase() {
    return base;
  }

  public Ribet getRibet() {
    return ribet;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FulardType{");
    sb.append("base=").append(base);
    sb.append(", ribet=").append(ribet);
    sb.append('}');
    return sb.toString();
  }
}
