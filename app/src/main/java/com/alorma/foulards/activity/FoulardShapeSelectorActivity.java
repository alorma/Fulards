package com.alorma.foulards.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.FoulardsShape;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.R;
import com.alorma.foulards.fragment.fulard.FoulardShapeSenyeraFragment;
import com.alorma.foulards.fragment.fulard.FoulardShapeSquareFragment;
import com.alorma.foulards.fragment.fulard.FoulardShapeTriangleDobleFragment;
import com.alorma.foulards.fragment.fulard.FoulardShapeTriangleSimpleFragment;
import com.alorma.foulards.fragment.fulard.FulardSelectorFragment;

public class FoulardShapeSelectorActivity extends AppCompatActivity implements FulardSelectorFragment.Callback {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fulard_shapes);
    ButterKnife.bind(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    configTabs();
    onShapeSelected(FoulardsShape.TRIANGLE_SIMPLE);
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

  private void setFragment(FulardSelectorFragment fragment) {
    fragment.setCallback(this);
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.content, fragment);
    ft.commit();
  }

  @Override
  public void onFulardSelected(FulardType fulardType) {
    Intent data = new Intent();
    data.putExtra(Extras.EXTRA_FULARD_TYPE, fulardType);
    setResult(RESULT_OK, data);
    finish();
  }

  public class Extras {
    public static final String EXTRA_FULARD_TYPE = "EXTRA_FULARD_TYPE";
  }
}
