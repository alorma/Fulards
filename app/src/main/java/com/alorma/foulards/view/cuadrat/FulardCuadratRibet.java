package com.alorma.foulards.view.cuadrat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.R;
import com.alorma.foulards.view.Fulard;
import com.alorma.foulards.view.FulardCustomization;

public class FulardCuadratRibet extends Fulard {
  private Paint paint;
  private Rect rect;
  private Paint paintRibet;
  private int ribet;

  public FulardCuadratRibet(Context context) {
    super(context);
  }

  public FulardCuadratRibet(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardCuadratRibet(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardCuadratRibet(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(getGrayMiddle());

    paintRibet = new Paint();
    paintRibet.setAntiAlias(true);
    paintRibet.setStyle(Paint.Style.FILL);
    paintRibet.setColor(getGrayLight());

    rect = new Rect();

    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_simple);
  }

  @Override
  public FulardType getFulardType() {
    return FulardType.fulard_11;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.getClipBounds(rect);

    canvas.drawRect(rect, paintRibet);

    canvas.drawRect(rect.left + ribet, rect.top + ribet, rect.right - ribet, rect.bottom - ribet, paint);
  }

  @Override
  public void fill(FulardCustomization customization) {
    if (customization.getFulardColor() != 0) {
      paint.setColor(customization.getFulardColor());
    } else {
      paint.setColor(getGrayMiddle());
    }
    if (customization.getRibetColor() != 0) {
      paintRibet.setColor(customization.getRibetColor());
    } else {
      paintRibet.setColor(getGrayLight());
    }
    invalidate();
  }
}
