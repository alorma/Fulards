package com.alorma.foulards;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

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

    onShapeSelected(FoulardsShape.TRIANGLE_SIMPLE);
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
