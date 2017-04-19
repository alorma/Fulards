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

public class FulardCuadratRibetDoble extends Fulard {
  private Paint paintFulard;
  private Rect rect;
  private Paint paintRibetIntern;
  private int ribet;
  private Paint paintRibetExtern;

  public FulardCuadratRibetDoble(Context context) {
    super(context);
  }

  public FulardCuadratRibetDoble(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardCuadratRibetDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardCuadratRibetDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    paintFulard = new Paint();
    paintFulard.setAntiAlias(true);
    paintFulard.setStyle(Paint.Style.FILL);
    paintFulard.setColor(getGrayMiddle());

    paintRibetIntern = new Paint();
    paintRibetIntern.setAntiAlias(true);
    paintRibetIntern.setStyle(Paint.Style.FILL);
    paintRibetIntern.setColor(getGrayLight());

    paintRibetExtern = new Paint();
    paintRibetExtern.setAntiAlias(true);
    paintRibetExtern.setStyle(Paint.Style.FILL);
    paintRibetExtern.setColor(getGrayMiddle());

    rect = new Rect();

    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_doble);
  }

  @Override
  public FulardType getFulardType() {
    return FulardType.fulard_12;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.getClipBounds(rect);

    canvas.drawRect(rect, paintRibetExtern);

    canvas.drawRect(rect.left + ribet,
        rect.top + ribet,
        rect.right - ribet,
        rect.bottom - ribet,
        paintRibetIntern);

    canvas.drawRect(rect.left + (ribet * 2),
        rect.top + (ribet * 2),
        rect.right - (ribet * 2),
        rect.bottom - (ribet * 2),
        paintFulard);
  }

  @Override
  public void fill(FulardCustomization customization) {
    if (customization.getFulardColor() != 0) {
      paintFulard.setColor(customization.getFulardColor());
    } else {
      paintFulard.setColor(getGrayMiddle());
    }
    if (customization.getRibetExtern() != 0) {
      paintRibetExtern.setColor(customization.getRibetExtern());
    } else {
      paintRibetExtern.setColor(getGrayMiddle());
    }
    if (customization.getRibetIntern() != 0) {
      paintRibetIntern.setColor(customization.getRibetIntern());
    } else {
      paintRibetIntern.setColor(getGrayLight());
    }
    invalidate();
  }
}
