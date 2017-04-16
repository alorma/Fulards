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
import com.alorma.foulards.R;
import com.alorma.foulards.view.Fulard;

public class FulardDobleSenseRibet extends Fulard {
  private Rect rect;
  private Path path;
  private Paint paintFulardEsquerra;
  private Paint paintFulardDreta;

  public FulardDobleSenseRibet(Context context) {
    super(context);
  }

  public FulardDobleSenseRibet(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardDobleSenseRibet(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardDobleSenseRibet(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

    rect = new Rect();
    path = new Path();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.getClipBounds(rect);

    // Fulard1
    path.moveTo(rect.right, 0);
    path.lineTo(rect.right, rect.bottom);
    path.lineTo(rect.centerX(), rect.centerY());
    path.close();
    canvas.drawPath(path, paintFulardEsquerra);
    path.reset();

    // Fulard2
    path.moveTo(rect.left, rect.bottom);
    path.lineTo(rect.right, rect.bottom);
    path.lineTo(rect.centerX(), rect.centerY());
    path.close();
    canvas.drawPath(path, paintFulardDreta);
    path.reset();
  }
}
