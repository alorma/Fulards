package com.alorma.foulards;

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
    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
  }
}
