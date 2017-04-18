package com.alorma.foulards.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import com.alorma.foulards.FulardType;

public abstract class Fulard extends View {

  public Fulard(Context context) {
    super(context);
    init(isInEditMode());
  }

  public Fulard(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(isInEditMode());
  }

  public Fulard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(isInEditMode());
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public Fulard(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(isInEditMode());
  }

  protected abstract void init(boolean inEditMode);

  @Override
  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
  }

  public abstract FulardType getFulardType();
}
