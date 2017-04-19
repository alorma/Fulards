package com.alorma.foulards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.fragment.color.fulard.FulardDosColorsBaseColorSelectorFragment;
import com.alorma.foulards.fragment.color.fulard.FulardSimpleBaseColorSelectorFragment;
import com.alorma.foulards.fragment.color.ribet.RibetDosColorSelectorFragment;
import com.alorma.foulards.fragment.color.ribet.RibetSimpleColorSelectorFragment;
import com.alorma.foulards.view.Fulard;
import com.alorma.foulards.view.FulardCustomization;
import com.alorma.foulards.view.FulardFactory;

public class MainActivity extends AppCompatActivity {

  private static final int REQUEST_CODE_FULARD = 21;

  @BindView(R.id.buttonShape) View buttonShape;
  @BindView(R.id.fulardLayout) ViewGroup fulardLayout;
  private Fulard fulard;

  private FulardCustomization customization;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    customization = new FulardCustomization();

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    buttonShape.setOnClickListener(v -> showShape());
  }

  private void showShape() {
    Intent intent = new Intent(this, FoulardShapeSelectorActivity.class);
    startActivityForResult(intent, REQUEST_CODE_FULARD);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {
      if (requestCode == REQUEST_CODE_FULARD) {
        FulardType fulardType = (FulardType) data.getExtras().get(FoulardShapeSelectorActivity.Extras.EXTRA_FULARD_TYPE);
        onFulardTypeSelected(fulardType);
      }
    }
  }

  private void onFulardTypeSelected(FulardType fulardType) {
    fulard = new FulardFactory().get(this, fulardType);

    int width = getResources().getDimensionPixelOffset(R.dimen.fulard_selector_size);
    int height = getResources().getDimensionPixelOffset(R.dimen.fulard_selector_size);

    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
    params.gravity = Gravity.CENTER;

    fulardLayout.removeAllViews();
    fulardLayout.addView(fulard, params);

    FulardCustomization customization = buildCustomization();

    fulard.fill(customization);

    showColorsSelectorFulard(fulardType);
    showColorsSelectorRibets(fulardType);
  }

  private FulardCustomization buildCustomization() {
    FulardCustomization build = new FulardCustomization();

    build.setFulardColor(customization.getFulardColor());
    build.setFulardDretaColor(customization.getFulardDretaColor());
    build.setFulardEsquerraColor(customization.getFulardEsquerraColor());
    build.setRibetColor(customization.getRibetColor());
    build.setRibetIntern(customization.getRibetIntern());
    build.setRibetExtern(customization.getRibetExtern());
    build.setRibetDretaColor(customization.getRibetDretaColor());
    build.setRibetEsquerraColor(customization.getRibetEsquerraColor());
    build.setRibetMiddle(customization.getRibetMiddle());
    build.setRibetMiddleIntern(customization.getRibetMiddleIntern());
    build.setRibetMiddleExtern(customization.getRibetMiddleExtern());

    return build;
  }

  // region fulard color
  private void showColorsSelectorFulard(FulardType fulardType) {
    FulardType.Base base = fulardType.getBase();

    if (base == FulardType.Base.simple) {
      FulardSimpleBaseColorSelectorFragment fragment = new FulardSimpleBaseColorSelectorFragment();
      fragment.setCallback(this::onBaseSimpleColor);
      getSupportFragmentManager().beginTransaction().replace(R.id.contentFulardColorsSelector, fragment).commit();
    } else if (base == FulardType.Base.doble) {
      FulardDosColorsBaseColorSelectorFragment fragment = new FulardDosColorsBaseColorSelectorFragment();
      fragment.setCallback(new FulardDosColorsBaseColorSelectorFragment.Callback() {
        @Override
        public void onFulardBaseColorDretaSelector(FulardColor color) {
          onBaseDretaColor(color);
        }

        @Override
        public void onFulardBaseColorEsquerraSelector(FulardColor color) {
          onBaseEsquerraColor(color);
        }
      });
      getSupportFragmentManager().beginTransaction().replace(R.id.contentFulardColorsSelector, fragment).commit();
    }
  }

  private void onBaseSimpleColor(FulardColor color) {
    customization.setFulardColor(color.getColorInt());
    customization.setFulardDretaColor(0);
    customization.setFulardEsquerraColor(0);

    applyCustom();
  }

  private void onBaseDretaColor(FulardColor color) {
    customization.setFulardColor(0);
    customization.setFulardDretaColor(color.getColorInt());
    applyCustom();
  }

  private void onBaseEsquerraColor(FulardColor color) {
    customization.setFulardColor(0);
    customization.setFulardEsquerraColor(color.getColorInt());
    applyCustom();
  }

  private void applyCustom() {
    if (fulard != null) {
      fulard.fill(buildCustomization());
    }
  }
  // endregion

  private void showColorsSelectorRibets(FulardType fulardType) {
    FulardType.Ribet ribet = fulardType.getRibet();

    switch (ribet) {
      case un:
        showRibetSimpleSelector();
        break;
      case dos_colors:
        showRibetDosColorsSelector();
        break;
    }
  }

  private void showRibetSimpleSelector() {
    RibetSimpleColorSelectorFragment fragment = new RibetSimpleColorSelectorFragment();
    fragment.setCallback(color -> {
      customization.setRibetColor(color.getColorInt());
      applyCustom();
    });
    getSupportFragmentManager().beginTransaction().replace(R.id.contentRibetColorsSelector, fragment).commit();
  }

  private void showRibetDosColorsSelector() {
    RibetDosColorSelectorFragment fragment = new RibetDosColorSelectorFragment();
    fragment.setCallback(new RibetDosColorSelectorFragment.Callback() {
      @Override
      public void onRibetColorDretaSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetDretaColor(color.getColorInt());
        applyCustom();
      }

      @Override
      public void onRibetColorEsquerraSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(color.getColorInt());
        applyCustom();
      }
    });
    getSupportFragmentManager().beginTransaction().replace(R.id.contentRibetColorsSelector, fragment).commit();
  }
}
