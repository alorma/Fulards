package com.alorma.foulards.view.simple;

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
import com.alorma.foulards.view.Fulard;
import com.alorma.foulards.view.FulardCustomization;

public class FulardRibetDoble extends Fulard {
  private Paint paintFulard;
  private Rect rect;
  private Path path;
  private Paint paintRibetExtern;
  private int ribet;
  private Paint paintRibetIntern;

  public FulardRibetDoble(Context context) {
    super(context);
  }

  public FulardRibetDoble(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardRibetDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardRibetDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    paintFulard = new Paint();
    paintFulard.setAntiAlias(true);
    paintFulard.setStyle(Paint.Style.FILL);
    paintFulard.setColor(getGrayMiddle());

    paintRibetExtern = new Paint();
    paintRibetExtern.setStyle(Paint.Style.FILL);
    paintRibetExtern.setColor(getGrayMiddle());

    paintRibetIntern = new Paint();
    paintRibetIntern.setStyle(Paint.Style.FILL);
    paintRibetIntern.setColor(getGrayLight());

    rect = new Rect();
    path = new Path();

    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_doble);
  }

  @Override
  public FulardType getFulardType() {
    return FulardType.fulard_3;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.getClipBounds(rect);

    // Ribet extern
    path.moveTo(rect.right, 0);
    path.lineTo(rect.right, rect.bottom);
    path.lineTo(rect.left, rect.bottom);
    path.close();
    canvas.drawPath(path, paintRibetExtern);

    path.reset();

    // Ribet intern
    path.moveTo(rect.right - ribet, ribet);
    path.lineTo(rect.right - ribet, rect.bottom - ribet);
    path.lineTo(rect.left + ribet, rect.bottom - ribet);
    path.close();
    canvas.drawPath(path, paintRibetIntern);

    path.reset();

    // Foulard
    path.moveTo(rect.right - (ribet * 2), (ribet * 2));
    path.lineTo(rect.right - (ribet * 2), rect.bottom - (ribet * 2));
    path.lineTo(rect.left + (ribet * 2), rect.bottom - (ribet * 2));
    path.close();
    canvas.drawPath(path, paintFulard);
    path.reset();
  }

  @Override
  public void fill(FulardCustomization customization) {
    if (customization.getFulardColor() != 0) {
      paintFulard.setColor(customization.getFulardColor());
    } else {
      paintFulard.setColor(getGrayMiddle());
    }
    if (customization.getRibetIntern() != 0) {
      paintRibetIntern.setColor(customization.getRibetIntern());
    } else {
      paintRibetIntern.setColor(getGrayLight());
    }
    if (customization.getRibetExtern() != 0) {
      paintRibetExtern.setColor(customization.getRibetExtern());
    } else {
      paintRibetExtern.setColor(getGrayMiddle());
    }
  }
}
