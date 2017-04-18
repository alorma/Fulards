package com.alorma.foulards.view.simple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.R;
import com.alorma.foulards.view.Fulard;
import com.alorma.foulards.view.FulardCustomization;

public class FulardRibetSimple extends Fulard {
  private Paint paint;
  private Rect rect;
  private Path path;
  private Paint paintRibet;
  private int ribet;

  public FulardRibetSimple(Context context) {
    super(context);
  }

  public FulardRibetSimple(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardRibetSimple(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardRibetSimple(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    int color = ContextCompat.getColor(getContext(), R.color.grey_fulard_middle);
    paint.setColor(color);

    paintRibet = new Paint();
    paintRibet.setStyle(Paint.Style.FILL);
    int colorRibet = ContextCompat.getColor(getContext(), R.color.grey_fulard_light);
    paintRibet.setColor(colorRibet);

    rect = new Rect();
    path = new Path();
    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_simple);
  }

  @Override
  public FulardType getFulardType() {
    return FulardType.fulard_2;
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

    // Foulard
    path.moveTo(rect.right - ribet, ribet);
    path.lineTo(rect.right - ribet, rect.bottom - ribet);
    path.lineTo(rect.left + ribet, rect.bottom - ribet);
    path.close();
    canvas.drawPath(path, paint);
    path.reset();
  }

  @Override
  public void fill(FulardCustomization customization) {
    if (customization.getFulardColor() != 0) {
      paint.setColor(customization.getFulardColor());
    }
    if (customization.getRibetColor() != 0) {
      paintRibet.setColor(customization.getRibetColor());
    }
  }
}
