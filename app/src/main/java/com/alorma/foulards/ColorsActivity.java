package com.alorma.foulards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.alorma.foulards.fragment.ColorSelectorFragment;

public class ColorsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_colors_activity);

    ColorSelectorFragment fragment = new ColorSelectorFragment();
    fragment.setCallback(this::onColorSelected);
    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
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
