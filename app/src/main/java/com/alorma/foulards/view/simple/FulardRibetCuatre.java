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

public class FulardRibetCuatre extends Fulard {
  private Paint paintFulard;
  private Rect rect;
  private Path path;
  private Paint paintRibet;
  private Paint paintRibetIntern;
  private int ribet;
  private Paint paintRibetMiddleIntern;
  private Paint paintRibetMiddleExtern;

  public FulardRibetCuatre(Context context) {
    super(context);
  }

  public FulardRibetCuatre(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FulardRibetCuatre(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FulardRibetCuatre(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void init(boolean inEditMode) {
    paintRibet = new Paint();
    paintRibet.setStyle(Paint.Style.FILL);
    int colorRibet = ContextCompat.getColor(getContext(), R.color.grey_fulard_dark);
    paintRibet.setColor(colorRibet);

    paintRibetMiddleExtern = new Paint();
    paintRibetMiddleExtern.setStyle(Paint.Style.FILL);
    int colorRibetMiddleIntern = ContextCompat.getColor(getContext(), R.color.grey_fulard_light);
    paintRibetMiddleExtern.setColor(colorRibetMiddleIntern);

    paintRibetMiddleIntern = new Paint();
    paintRibetMiddleIntern.setStyle(Paint.Style.FILL);
    int colorRibetMiddleExtern = ContextCompat.getColor(getContext(), R.color.grey_fulard_dark);
    paintRibetMiddleIntern.setColor(colorRibetMiddleExtern);

    paintRibetIntern = new Paint();
    paintRibetIntern.setStyle(Paint.Style.FILL);
    int colorRibetIntern = ContextCompat.getColor(getContext(), R.color.grey_fulard_light);
    paintRibetIntern.setColor(colorRibetIntern);

    paintFulard = new Paint();
    paintFulard.setAntiAlias(true);
    paintFulard.setStyle(Paint.Style.FILL);
    int color = ContextCompat.getColor(getContext(), R.color.grey_fulard_dark);
    paintFulard.setColor(color);

    rect = new Rect();
    path = new Path();

    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_cuatre);
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
    canvas.drawPath(path, paintRibet);
    path.reset();

    // Ribet mig
    path.moveTo(rect.right - ribet, ribet);
    path.lineTo(rect.right - ribet, rect.bottom - ribet);
    path.lineTo(rect.left + ribet, rect.bottom - ribet);
    path.close();
    canvas.drawPath(path, paintRibetMiddleExtern);
    path.reset();

    // Ribet intern
    path.moveTo(rect.right - (ribet * 2), (ribet * 2));
    path.lineTo(rect.right - (ribet * 2), rect.bottom - (ribet * 2));
    path.lineTo(rect.left + (ribet * 2), rect.bottom - (ribet * 2));
    path.close();
    canvas.drawPath(path, paintRibetMiddleIntern);
    path.reset();

    // Foulard
    path.moveTo(rect.right - (ribet * 3), (ribet * 3));
    path.lineTo(rect.right - (ribet * 3), rect.bottom - (ribet * 3));
    path.lineTo(rect.left + (ribet * 3), rect.bottom - (ribet * 3));
    path.close();
    canvas.drawPath(path, paintRibetIntern);
    path.reset();

    // Foulard
    path.moveTo(rect.right - (ribet * 4), (ribet * 4));
    path.lineTo(rect.right - (ribet * 4), rect.bottom - (ribet * 4));
    path.lineTo(rect.left + (ribet * 4), rect.bottom - (ribet * 4));
    path.close();
    canvas.drawPath(path, paintFulard);
    path.reset();
  }
}
