package com.alorma.foulards.view.doble;

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

public class FulardSimpleRibetDoble extends FulardDoble {
  private Rect rect;
  private Path path;
  private Paint paintRibetEsquerra;
  private int ribet;
  private Paint paintRibetDreta;
  private Paint paint;

  public FulardSimpleRibetDoble(Context context) {
    super(context);
  }

  public FulardSimpleRibetDoble(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardSimpleRibetDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardSimpleRibetDoble(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    super.init(inEditMode);
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    int color = ContextCompat.getColor(getContext(), R.color.grey_fulard_middle);
    paint.setColor(color);

    paintRibetEsquerra = new Paint();
    paintRibetEsquerra.setStyle(Paint.Style.FILL);
    int colorRibetEsquerra = ContextCompat.getColor(getContext(), R.color.grey_fulard_light);
    paintRibetEsquerra.setColor(colorRibetEsquerra);

    paintRibetDreta = new Paint();
    paintRibetDreta.setStyle(Paint.Style.FILL);
    int colorRibetDreta = ContextCompat.getColor(getContext(), R.color.grey_fulard_dark);
    paintRibetDreta.setColor(colorRibetDreta);

    rect = new Rect();
    path = new Path();
    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_simple);
  }

  @Override
  public FulardType getFulardType() {
    return FulardType.fulard_8;
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

    // Fulard
    path.moveTo(rect.right - ribet, ribet);
    path.lineTo(rect.right - ribet, rect.bottom - ribet);
    path.lineTo(rect.left + ribet, rect.bottom - ribet);
    path.close();
    canvas.drawPath(path, paint);
    path.reset();
  }
}
