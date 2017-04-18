package com.alorma.foulards;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife;
import com.alorma.foulards.fragment.fulard.FoulardShapeSenyeraFragment;
import com.alorma.foulards.fragment.fulard.FoulardShapeSquareFragment;
import com.alorma.foulards.fragment.fulard.FoulardShapeTriangleDobleFragment;
import com.alorma.foulards.fragment.fulard.FoulardShapeTriangleSimpleFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    View buttonsColor = findViewById(R.id.buttonColors);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    configTabs();
    onShapeSelected(FoulardsShape.TRIANGLE_SIMPLE);

    buttonsColor.setOnClickListener(v -> showColors());
  }

  private void showColors() {
    Intent intent = new Intent(this, ColorsActivity.class);
    startActivity(intent);
  }

  private void configTabs() {
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.addTab(tabLayout.newTab().setText("Simple").setTag(FoulardsShape.TRIANGLE_SIMPLE), true);
    tabLayout.addTab(tabLayout.newTab().setText("Doble").setTag(FoulardsShape.TRIANGLE_DOBLE), false);
    tabLayout.addTab(tabLayout.newTab().setText("Cuadrat").setTag(FoulardsShape.SQUARE), false);
    tabLayout.addTab(tabLayout.newTab().setText("Senyera").setTag(FoulardsShape.SENYERA), false);

    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getTag() instanceof FoulardsShape) {
          onShapeSelected((FoulardsShape) tab.getTag());
        }
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }

  private void onShapeSelected(FoulardsShape shape) {
    if (shape.equals(FoulardsShape.TRIANGLE_SIMPLE)) {
      setFragment(FoulardShapeTriangleSimpleFragment.newInstance());
    } else if (shape.equals(FoulardsShape.TRIANGLE_DOBLE)) {
      setFragment(FoulardShapeTriangleDobleFragment.newInstance());
    } else if (shape.equals(FoulardsShape.SQUARE)) {
      setFragment(FoulardShapeSquareFragment.newInstance());
    } else if (shape.equals(FoulardsShape.SENYERA)) {
      setFragment(FoulardShapeSenyeraFragment.newInstance());
    }
  }

  private void setFragment(Fragment fragment) {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.content, fragment);
    ft.commit();
  }
}
