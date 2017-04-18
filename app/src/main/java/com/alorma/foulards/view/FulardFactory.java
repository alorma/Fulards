package com.alorma.foulards.view;

import android.content.Context;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.view.cuadrat.FulardCuadrat;
import com.alorma.foulards.view.cuadrat.FulardCuadratRibet;
import com.alorma.foulards.view.cuadrat.FulardCuadratRibetDoble;
import com.alorma.foulards.view.doble.FulardDobleRibetDoble;
import com.alorma.foulards.view.doble.FulardDobleRibetSimple;
import com.alorma.foulards.view.doble.FulardDobleSenseRibet;
import com.alorma.foulards.view.doble.FulardSimpleRibetDoble;
import com.alorma.foulards.view.senyera.FulardRibetDobleSenyera;
import com.alorma.foulards.view.senyera.FulardRibetSenyera;
import com.alorma.foulards.view.simple.FulardRibetCuatre;
import com.alorma.foulards.view.simple.FulardRibetDoble;
import com.alorma.foulards.view.simple.FulardRibetSimple;
import com.alorma.foulards.view.simple.FulardRibetTriple;
import com.alorma.foulards.view.simple.FulardSimple;

public class FulardFactory {

  public FulardFactory(Context context) {

  }

  public Fulard get(Context context, FulardType fulardType) {
    switch (fulardType) {
      case fulard_1:
        return new FulardSimple(context);
      case fulard_2:
        return new FulardRibetSimple(context);
      case fulard_3:
        return new FulardRibetDoble(context);
      case fulard_4:
        return new FulardRibetTriple(context);
      case fulard_5:
        return new FulardRibetCuatre(context);
      case fulard_6:
        return new FulardDobleRibetDoble(context);
      case fulard_7:
        return new FulardDobleRibetSimple(context);
      case fulard_8:
        return new FulardSimpleRibetDoble(context);
      case fulard_9:
        return new FulardDobleSenseRibet(context);
      case fulard_10:
        return new FulardCuadrat(context);
      case fulard_11:
        return new FulardCuadratRibet(context);
      case fulard_12:
        return new FulardCuadratRibetDoble(context);
      case fulard_13:
        return new FulardRibetSenyera(context);
      case fulard_14:
        return new FulardRibetDobleSenyera(context);
      default:
        return null;
    }
  }
}
