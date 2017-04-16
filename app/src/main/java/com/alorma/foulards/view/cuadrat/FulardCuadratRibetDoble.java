package com.alorma.foulards.view.cuadrat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import com.alorma.foulards.R;
import com.alorma.foulards.view.Fulard;

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
    int color = ContextCompat.getColor(getContext(), R.color.grey_fulard_middle);
    paintFulard.setColor(color);

    paintRibetIntern = new Paint();
    paintRibetIntern.setAntiAlias(true);
    paintRibetIntern.setStyle(Paint.Style.FILL);
    int colorRibetIntern = ContextCompat.getColor(getContext(), R.color.grey_fulard_light);
    paintRibetIntern.setColor(colorRibetIntern);

    paintRibetExtern = new Paint();
    paintRibetExtern.setAntiAlias(true);
    paintRibetExtern.setStyle(Paint.Style.FILL);
    int colorRibetExtern = ContextCompat.getColor(getContext(), R.color.grey_fulard_middle);
    paintRibetExtern.setColor(colorRibetExtern);

    rect = new Rect();

    ribet = getResources().getDimensionPixelOffset(R.dimen.ribet_doble);
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
}
