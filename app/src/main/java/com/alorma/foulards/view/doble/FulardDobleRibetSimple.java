package com.alorma.foulards.view.doble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.R;
import com.alorma.foulards.view.FulardCustomization;

public class FulardDobleRibetSimple extends FulardDoble {
  private Rect rect;
  private Path path;
  private Paint paintFulardEsquerra;
  private Paint paintFulardDreta;
  private Paint paintRibet;
  private int ribet;

  public FulardDobleRibetSimple(Context context) {
    super(context);
  }

  public FulardDobleRibetSimple(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardDobleRibetSimple(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardDobleRibetSimple(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    super.init(inEditMode);
    paintFulardEsquerra = new Paint();
    paintFulardEsquerra.setAntiAlias(true);
    paintFulardEsquerra.setStyle(Paint.Style.FILL);
    paintFulardEsquerra.setColor(getGrayLight());

    paintFulardDreta = new Paint();
    paintFulardDreta.setAntiAlias(true);
    paintFulardDreta.setStyle(Paint.Style.FILL);
    paintFulardDreta.setColor(getGrayMiddle());

    paintRibet = new Paint();
    paintRibet.setStyle(Paint.Style.FILL);
    paintRibet.setColor(getGrayDark());

    rect = new Rect();
    path = new Path();
    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_simple);
  }

  @Override
  public FulardType getFulardType() {
    return FulardType.fulard_7;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.getClipBounds(rect);

    // Ribet
    path.moveTo(rect.right, 0);
    path.lineTo(rect.right, rect.bottom);
    path.lineTo(rect.left, rect.bottom);
    path.close();
    canvas.drawPath(path, paintRibet);

    path.reset();

    // Fulard1
    path.moveTo(rect.right - ribet, ribet);
    path.lineTo(rect.right - ribet, rect.bottom - ribet);
    path.lineTo(rect.centerX(), rect.centerY());
    path.close();
    canvas.drawPath(path, paintFulardEsquerra);
    path.reset();

    // Fulard2
    path.moveTo(rect.left + ribet, rect.bottom - ribet);
    path.lineTo(rect.right - ribet, rect.bottom - ribet);
    path.lineTo(rect.centerX(), rect.centerY());
    path.close();
    canvas.drawPath(path, paintFulardDreta);
    path.reset();
  }

  @Override
  public void fill(FulardCustomization customization) {
    if (customization.getFulardDretaColor() != 0) {
      paintFulardDreta.setColor(customization.getFulardDretaColor());
    } else {
      paintFulardDreta.setColor(getGrayMiddle());
    }
    if (customization.getFulardEsquerraColor() != 0) {
      paintFulardEsquerra.setColor(customization.getFulardEsquerraColor());
    } else {
      paintFulardEsquerra.setColor(getGrayLight());
    }
    if (customization.getRibetColor() != 0) {
      paintRibet.setColor(customization.getRibetColor());
    } else {
      paintRibet.setColor(getGrayDark());
    }
    invalidate();
  }
}
