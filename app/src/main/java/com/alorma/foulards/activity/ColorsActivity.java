package com.alorma.foulards.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.R;
import com.alorma.foulards.fragment.ColorSelectorFragment;

public class ColorsActivity extends AppCompatActivity {

  @BindView(R.id.removeColor) View removeColor;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_colors_activity);
    ButterKnife.bind(this);

    ColorSelectorFragment fragment = new ColorSelectorFragment();
    fragment.setCallback(this::onColorSelected);
    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();

    removeColor.setOnClickListener(v -> {
      returnNoColor();
    });
  }

  private void returnNoColor() {
    setResult(RESULT_CANCELED);
    finish();
  }

  private void onColorSelected(FulardColor color) {
    Intent data = new Intent();
    data.putExtra(ColorsActivity.Extras.EXTRA_COLOR, color);
    setResult(RESULT_OK, data);
    finish();
  }

  public class Extras {
    public static final String EXTRA_COLOR = "EXTRA_COLOR";
  }
}
