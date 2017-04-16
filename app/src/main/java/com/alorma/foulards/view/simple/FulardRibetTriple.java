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
import com.alorma.foulards.R;
import com.alorma.foulards.view.Fulard;

public class FulardRibetTriple extends Fulard {
  private Paint paintFulard;
  private Rect rect;
  private Path path;
  private Paint paintribet;
  private Paint paintRibetIntern;
  private int ribet;
  private Paint paintRibetMiddle;

  public FulardRibetTriple(Context context) {
    super(context);
  }

  public FulardRibetTriple(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardRibetTriple(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardRibetTriple(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    paintFulard = new Paint();
    paintFulard.setAntiAlias(true);
    paintFulard.setStyle(Paint.Style.FILL);
    int color = ContextCompat.getColor(getContext(), R.color.grey_fulard_middle);
    paintFulard.setColor(color);

    paintribet = new Paint();
    paintribet.setStyle(Paint.Style.FILL);
    int colorribet = ContextCompat.getColor(getContext(), R.color.grey_fulard_light);
    paintribet.setColor(colorribet);

    paintRibetMiddle = new Paint();
    paintRibetMiddle.setStyle(Paint.Style.FILL);
    int colorRibetMiddle = ContextCompat.getColor(getContext(), R.color.grey_fulard_middle);
    paintRibetMiddle.setColor(colorRibetMiddle);

    paintRibetIntern = new Paint();
    paintRibetIntern.setStyle(Paint.Style.FILL);
    int colorRibetIntern = ContextCompat.getColor(getContext(), R.color.grey_fulard_light);
    paintRibetIntern.setColor(colorRibetIntern);

    rect = new Rect();
    path = new Path();

    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_doble);
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
    canvas.drawPath(path, paintribet);

    path.reset();

    // Ribet mig
    path.moveTo(rect.right - ribet, ribet);
    path.lineTo(rect.right - ribet, rect.bottom - ribet);
    path.lineTo(rect.left + ribet, rect.bottom - ribet);
    path.close();
    canvas.drawPath(path, paintRibetMiddle);

    path.reset();

    // Ribet intern
    path.moveTo(rect.right - (ribet * 2), (ribet * 2));
    path.lineTo(rect.right - (ribet * 2), rect.bottom - (ribet * 2));
    path.lineTo(rect.left + (ribet * 2), rect.bottom - (ribet * 2));
    path.close();
    canvas.drawPath(path, paintRibetIntern);
    path.reset();

    // Foulard
    path.moveTo(rect.right - (ribet * 3), (ribet * 3));
    path.lineTo(rect.right - (ribet * 3), rect.bottom - (ribet * 3));
    path.lineTo(rect.left + (ribet * 3), rect.bottom - (ribet * 3));
    path.close();
    canvas.drawPath(path, paintFulard);
    path.reset();
  }
}
