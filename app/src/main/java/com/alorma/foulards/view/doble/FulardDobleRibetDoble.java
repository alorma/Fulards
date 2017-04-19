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

public class FulardDobleRibetDoble extends FulardDoble {
  private Rect rect;
  private Path path;
  private Paint paintRibetEsquerra;
  private int ribet;
  private Paint paintRibetDreta;

  public FulardDobleRibetDoble(Context context) {
    super(context);
  }

  public FulardDobleRibetDoble(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardDobleRibetDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardDobleRibetDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    super.init(inEditMode);
    paintRibetEsquerra = new Paint();
    paintRibetEsquerra.setStyle(Paint.Style.FILL);
    paintRibetEsquerra.setColor(getGrayLight());

    paintRibetDreta = new Paint();
    paintRibetDreta.setStyle(Paint.Style.FILL);
    paintRibetDreta.setColor(getGrayMiddle());

    rect = new Rect();
    path = new Path();
    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_simple);
  }

  @Override
  public FulardType getFulardType() {
    return FulardType.fulard_6;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.getClipBounds(rect);

    // Ribet1
    path.moveTo(rect.right, 0);
    path.lineTo(rect.right, rect.bottom);
    path.lineTo(rect.centerX(), rect.centerY());
    path.close();
    canvas.drawPath(path, paintRibetEsquerra);
    path.reset();

    // Ribet2
    path.moveTo(rect.left, rect.bottom);
    path.lineTo(rect.right, rect.bottom);
    path.lineTo(rect.centerX(), rect.centerY());
    path.close();
    canvas.drawPath(path, paintRibetDreta);
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
      paintFulardDreta.setColor(getGrayLight());
    }
    if (customization.getFulardEsquerraColor() != 0) {
      paintFulardEsquerra.setColor(customization.getFulardEsquerraColor());
    } else {
      paintFulardEsquerra.setColor(getGrayMiddle());
    }
    if (customization.getRibetDretaColor() != 0) {
      paintRibetDreta.setColor(customization.getRibetDretaColor());
    } else {
      paintRibetDreta.setColor(getGrayMiddle());
    }
    if (customization.getRibetEsquerraColor() != 0) {
      paintRibetEsquerra.setColor(customization.getRibetEsquerraColor());
    } else {
      paintRibetEsquerra.setColor(getGrayLight());
    }
    invalidate();
  }
}
