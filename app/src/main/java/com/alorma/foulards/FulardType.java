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
  fulard_14(Base.simple, Ribet.senyera_intern);

  public enum Base {
    simple(1),
    doble(2);

    private int colors;

    Base(int colors) {
      this.colors = colors;
    }

    public int getColors() {
      return colors;
    }
  }

  public enum Ribet {
    none(0),
    un(1),
    dos_colors(2),
    dos(2),
    tres(3),
    cuatre(4),
    senyera(0),
    senyera_intern(1);

    private int colors;

    Ribet(int colors) {
      this.colors = colors;
    }
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

}
