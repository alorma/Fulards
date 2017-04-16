package com.alorma.foulards.view.senyera;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import com.alorma.foulards.R;
import com.alorma.foulards.view.Fulard;

public class FulardSenyera extends Fulard {
  protected Paint paintSenyera;

  public FulardSenyera(Context context) {
    super(context);
  }

  public FulardSenyera(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardSenyera(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardSenyera(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    paintSenyera = new Paint();
    paintSenyera.setStyle(Paint.Style.FILL);
    int colorRibet = ContextCompat.getColor(getContext(), R.color.fulard_senyera);
    paintSenyera.setColor(colorRibet);
  }
}
