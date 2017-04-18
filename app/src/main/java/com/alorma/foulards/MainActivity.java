package com.alorma.foulards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.buttonShape) View buttonShape;
  @BindView(R.id.buttonColor) View buttonColor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    buttonShape.setOnClickListener(v -> showShape());
    buttonColor.setOnClickListener(v -> showColors());
  }

  private void showShape() {
    Intent intent = new Intent(this, FoulardShapeSelectorActivity.class);
    startActivity(intent);
  }

  private void showColors() {
    Intent intent = new Intent(this, ColorsActivity.class);
    startActivity(intent);
  }
}
