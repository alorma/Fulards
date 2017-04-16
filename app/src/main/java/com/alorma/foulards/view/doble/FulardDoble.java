package com.alorma.foulards.view.doble;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import com.alorma.foulards.R;
import com.alorma.foulards.view.Fulard;

public abstract class FulardDoble extends Fulard {
  protected Paint paintFulardEsquerra;
  protected Paint paintFulardDreta;

  public FulardDoble(Context context) {
    super(context);
  }

  public FulardDoble(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    paintFulardEsquerra = new Paint();
    paintFulardEsquerra.setAntiAlias(true);
    paintFulardEsquerra.setStyle(Paint.Style.FILL);
    int colorFulardEsquerra = ContextCompat.getColor(getContext(), R.color.grey_fulard_middle);
    paintFulardEsquerra.setColor(colorFulardEsquerra);

    paintFulardDreta = new Paint();
    paintFulardDreta.setAntiAlias(true);
    paintFulardDreta.setStyle(Paint.Style.FILL);
    int colorFulardDreta = ContextCompat.getColor(getContext(), R.color.grey_fulard_light);
    paintFulardDreta.setColor(colorFulardDreta);
  }
}
