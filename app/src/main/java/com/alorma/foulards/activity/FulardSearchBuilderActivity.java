package com.alorma.foulards.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.R;
import com.alorma.foulards.data.FulardSearch;
import com.alorma.foulards.fragment.color.fulard.FulardDosColorsBaseColorSelectorFragment;
import com.alorma.foulards.fragment.color.fulard.FulardSimpleBaseColorSelectorFragment;
import com.alorma.foulards.fragment.color.ribet.RibetCuatreRibetsColorSelectorFragment;
import com.alorma.foulards.fragment.color.ribet.RibetDosColorSelectorFragment;
import com.alorma.foulards.fragment.color.ribet.RibetDosRibetsColorSelectorFragment;
import com.alorma.foulards.fragment.color.ribet.RibetSimpleColorSelectorFragment;
import com.alorma.foulards.fragment.color.ribet.RibetTresRibetsColorSelectorFragment;
import com.alorma.foulards.view.Fulard;
import com.alorma.foulards.view.FulardCustomization;
import com.alorma.foulards.view.FulardFactory;

public class FulardSearchBuilderActivity extends AppCompatActivity {

  private static final int REQUEST_CODE_FULARD = 21;

  @BindView(R.id.buttonShape) View buttonShape;
  @BindView(R.id.fulardLayout) ViewGroup fulardLayout;
  @BindView(R.id.search_button) View searchButton;
  @BindDimen(R.dimen.fulard_selector_size) int dimenFulard;

  private Fulard fulard;

  private FulardCustomization customization;
  private Fragment ribetFragment;
  private FulardSearch fulardSearch;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_builder);
    ButterKnife.bind(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    buttonShape.setOnClickListener(v -> showShape());

    searchButton.setOnClickListener(v -> onSearchButtonClick());
  }

  private void onSearchButtonClick() {
    if (customization != null && fulardSearch != null) {
      Bundle extras = new Bundle();
      extras.putSerializable(Extras.EXTRA_CUSTOMIZATION, customization);
      extras.putSerializable(Extras.EXTRA_SEARCH, fulardSearch);

      Intent data = new Intent();
      data.putExtras(extras);

      setResult(RESULT_OK, data);
      finish();
    }
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

    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(dimenFulard, dimenFulard);
    params.gravity = Gravity.CENTER;

    fulardLayout.removeAllViews();
    fulardLayout.addView(fulard, params);

    customization = new FulardCustomization();
    fulard.fill(customization);

    fulardSearch = new FulardSearch();

    if (fulardType.getRibet() == FulardType.Ribet.senyera || fulardType.getRibet() == FulardType.Ribet.senyera_extern) {
      fulardSearch.setSenyera(true);
    }

    fulardSearch.setFulardType(fulardType);

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
    if (color != null) {
      customization.setFulardColor(color.getColorInt());
    } else {
      customization.setFulardColor(0);
    }
    customization.setFulardDretaColor(0);
    customization.setFulardEsquerraColor(0);

    fulardSearch.setFulardColor(color);

    applyCustom();
  }

  private void onBaseDretaColor(FulardColor color) {
    customization.setFulardColor(0);
    if (color != null) {
      customization.setFulardDretaColor(color.getColorInt());
    } else {
      customization.setFulardDretaColor(0);
    }

    fulardSearch.setFulardColorDreta(color);

    applyCustom();
  }

  private void onBaseEsquerraColor(FulardColor color) {
    customization.setFulardColor(0);
    if (color != null) {
      customization.setFulardEsquerraColor(color.getColorInt());
    } else {
      customization.setFulardEsquerraColor(0);
    }

    fulardSearch.setFulardColorEsquerra(color);

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
      case dos:
        showRibetSimpleSelectorDosRibets();
        break;
      case tres:
        showRibetSimpleSelectorTresRibets();
        break;
      case cuatre:
        showRibetSimpleSelectorCuatreRibets();
        break;
      case senyera_extern:
        showRibetSenyeraSimpleSelector();
        break;
      case none:
      case senyera:
        removeRibetColorsFragment();
    }
  }

  private void removeRibetColorsFragment() {
    if (ribetFragment != null) {
      getSupportFragmentManager().beginTransaction().remove(ribetFragment).commit();
    }
  }

  private void setRibetFragment(Fragment fragment) {
    this.ribetFragment = fragment;
    getSupportFragmentManager().beginTransaction().replace(R.id.contentRibetColorsSelector, fragment).commit();
  }

  private void showRibetSimpleSelector() {
    RibetSimpleColorSelectorFragment fragment = new RibetSimpleColorSelectorFragment();
    fragment.setCallback(color -> {
      if (color != null) {
        customization.setRibetColor(color.getColorInt());
      } else {
        customization.setRibetColor(0);
      }
      fulardSearch.setRibetColor(color);
      applyCustom();
    });
    setRibetFragment(fragment);
  }

  private void showRibetSenyeraSimpleSelector() {
    RibetSimpleColorSelectorFragment fragment = new RibetSimpleColorSelectorFragment();
    fragment.setCallback(color -> {
      if (color != null) {
        customization.setRibetExtern(color.getColorInt());
      } else {
        customization.setRibetExtern(0);
      }
      fulardSearch.setRibetExtern(color);
      applyCustom();
    });
    setRibetFragment(fragment);
  }

  private void showRibetDosColorsSelector() {
    RibetDosColorSelectorFragment fragment = new RibetDosColorSelectorFragment();
    fragment.setCallback(new RibetDosColorSelectorFragment.Callback() {
      @Override
      public void onRibetColorDretaSelector(FulardColor color) {
        customization.setRibetColor(0);
        if (color != null) {
          customization.setRibetDretaColor(color.getColorInt());
        } else {
          customization.setRibetDretaColor(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetDretaColor(color);

        applyCustom();
      }

      @Override
      public void onRibetColorEsquerraSelector(FulardColor color) {
        customization.setRibetColor(0);
        if (color != null) {
          customization.setRibetEsquerraColor(color.getColorInt());
        } else {
          customization.setRibetEsquerraColor(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetEsquerraColor(color);

        applyCustom();
      }
    });
    setRibetFragment(fragment);
  }

  private void showRibetSimpleSelectorDosRibets() {
    RibetDosRibetsColorSelectorFragment fragment = new RibetDosRibetsColorSelectorFragment();
    fragment.setCallback(new RibetDosRibetsColorSelectorFragment.Callback() {
      @Override
      public void onRibetInternColorSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(0);
        customization.setRibetDretaColor(0);
        customization.setRibetMiddleIntern(0);
        customization.setRibetMiddleExtern(0);
        customization.setRibetMiddle(0);
        if (color != null) {
          customization.setRibetIntern(color.getColorInt());
        } else {
          customization.setRibetIntern(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetIntern(color);

        applyCustom();
      }

      @Override
      public void onRibetExternColorSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(0);
        customization.setRibetDretaColor(0);
        customization.setRibetMiddleIntern(0);
        customization.setRibetMiddleExtern(0);
        customization.setRibetMiddle(0);
        if (color != null) {
          customization.setRibetExtern(color.getColorInt());
        } else {
          customization.setRibetExtern(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetEsquerraColor(null);
        fulardSearch.setRibetDretaColor(null);
        fulardSearch.setRibetMiddleIntern(null);
        fulardSearch.setRibetMiddleExtern(null);
        fulardSearch.setRibetMiddle(null);
        fulardSearch.setRibetEsquerraColor(color);

        applyCustom();
      }
    });
    setRibetFragment(fragment);
  }

  private void showRibetSimpleSelectorTresRibets() {
    RibetTresRibetsColorSelectorFragment fragment = new RibetTresRibetsColorSelectorFragment();
    fragment.setCallback(new RibetTresRibetsColorSelectorFragment.Callback() {
      @Override
      public void onRibetInternColorSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(0);
        customization.setRibetDretaColor(0);
        customization.setRibetMiddleIntern(0);
        customization.setRibetMiddleExtern(0);
        customization.setRibetMiddle(0);
        if (color != null) {
          customization.setRibetIntern(color.getColorInt());
        } else {
          customization.setRibetIntern(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetEsquerraColor(null);
        fulardSearch.setRibetDretaColor(null);
        fulardSearch.setRibetMiddleIntern(null);
        fulardSearch.setRibetMiddleExtern(null);
        fulardSearch.setRibetIntern(color);

        applyCustom();
      }

      @Override
      public void onRibetExternColorSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(0);
        customization.setRibetDretaColor(0);
        customization.setRibetMiddleIntern(0);
        customization.setRibetMiddleExtern(0);
        customization.setRibetMiddle(0);
        if (color != null) {
          customization.setRibetExtern(color.getColorInt());
        } else {
          customization.setRibetExtern(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetEsquerraColor(null);
        fulardSearch.setRibetDretaColor(null);
        fulardSearch.setRibetMiddleIntern(null);
        fulardSearch.setRibetMiddleExtern(null);
        fulardSearch.setRibetExtern(color);

        applyCustom();
      }

      @Override
      public void onRibetMiddleColorSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(0);
        customization.setRibetDretaColor(0);
        customization.setRibetMiddleIntern(0);
        customization.setRibetMiddleExtern(0);
        if (color != null) {
          customization.setRibetMiddle(color.getColorInt());
        } else {
          customization.setRibetMiddle(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetEsquerraColor(null);
        fulardSearch.setRibetDretaColor(null);
        fulardSearch.setRibetMiddleIntern(null);
        fulardSearch.setRibetMiddleExtern(null);
        fulardSearch.setRibetMiddle(color);

        applyCustom();
      }
    });
    setRibetFragment(fragment);
  }

  private void showRibetSimpleSelectorCuatreRibets() {
    RibetCuatreRibetsColorSelectorFragment fragment = new RibetCuatreRibetsColorSelectorFragment();
    fragment.setCallback(new RibetCuatreRibetsColorSelectorFragment.Callback() {
      @Override
      public void onRibetInternColorSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(0);
        customization.setRibetDretaColor(0);
        customization.setRibetMiddle(0);
        if (color != null) {
          customization.setRibetIntern(color.getColorInt());
        } else {
          customization.setRibetIntern(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetEsquerraColor(null);
        fulardSearch.setRibetDretaColor(null);
        fulardSearch.setRibetMiddle(null);
        fulardSearch.setRibetIntern(color);

        applyCustom();
      }

      @Override
      public void onRibetExternColorSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(0);
        customization.setRibetDretaColor(0);
        customization.setRibetMiddle(0);
        if (color != null) {
          customization.setRibetExtern(color.getColorInt());
        } else {
          customization.setRibetExtern(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetEsquerraColor(null);
        fulardSearch.setRibetDretaColor(null);
        fulardSearch.setRibetMiddle(null);
        fulardSearch.setRibetExtern(color);

        applyCustom();
      }

      @Override
      public void onRibetMiddleInternColorSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(0);
        customization.setRibetDretaColor(0);
        customization.setRibetMiddle(0);
        if (color != null) {
          customization.setRibetMiddleIntern(color.getColorInt());
        } else {
          customization.setRibetMiddleIntern(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetEsquerraColor(null);
        fulardSearch.setRibetDretaColor(null);
        fulardSearch.setRibetMiddle(null);
        fulardSearch.setRibetMiddleIntern(color);

        applyCustom();
      }

      @Override
      public void onRibetMiddleExternColorSelector(FulardColor color) {
        customization.setRibetColor(0);
        customization.setRibetEsquerraColor(0);
        customization.setRibetDretaColor(0);
        customization.setRibetMiddle(0);
        if (color != null) {
          customization.setRibetMiddleExtern(color.getColorInt());
        } else {
          customization.setRibetMiddleExtern(0);
        }

        fulardSearch.setRibetColor(null);
        fulardSearch.setRibetEsquerraColor(null);
        fulardSearch.setRibetDretaColor(null);
        fulardSearch.setRibetMiddle(null);
        fulardSearch.setRibetMiddleExtern(color);

        applyCustom();
      }
    });
    setRibetFragment(fragment);
  }

  public class Extras {
    public static final String EXTRA_SEARCH = "EXTRA_SEARCH";
    public static final String EXTRA_CUSTOMIZATION = "EXTRA_CUSTOMIZATION";
  }
}
