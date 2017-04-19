package com.alorma.foulards.fragment.color.ribet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.ColorsActivity;
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.R;

public class RibetTresRibetsColorSelectorFragment extends Fragment {

  private static final int REQUEST_CODE_COLOR_INTERN = 23;
  private static final int REQUEST_CODE_COLOR_MIDDLE = 22;
  private static final int REQUEST_CODE_COLOR_EXTERN = 21;

  private Callback callback;

  @BindView(R.id.ribetInternSelector) View ribetInternSelector;
  @BindView(R.id.ribetMiddleSelector) View ribetMiddleSelector;
  @BindView(R.id.ribetExternSelector) View ribetExternSelector;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_tres_ribets_selector, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    ribetInternSelector.setOnClickListener(v -> openColorSelector(REQUEST_CODE_COLOR_INTERN));
    ribetMiddleSelector.setOnClickListener(v -> openColorSelector(REQUEST_CODE_COLOR_MIDDLE));
    ribetExternSelector.setOnClickListener(v -> openColorSelector(REQUEST_CODE_COLOR_EXTERN));
  }

  private void openColorSelector(int requestCode) {
    Intent intent = new Intent(getContext(), ColorsActivity.class);
    startActivityForResult(intent, requestCode);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK) {
      FulardColor color = (FulardColor) data.getExtras().get(ColorsActivity.Extras.EXTRA_COLOR);
      if (requestCode == REQUEST_CODE_COLOR_INTERN) {
        getCallback().onRibetInternColorSelector(color);
      } else if (requestCode == REQUEST_CODE_COLOR_MIDDLE) {
        getCallback().onRibetMiddleColorSelector(color);
      } else if (requestCode == REQUEST_CODE_COLOR_EXTERN) {
        getCallback().onRibetExternColorSelector(color);
      }
    }
  }

  public Callback getCallback() {
    return callback != null ? callback : new Callback.Null();
  }

  public void setCallback(Callback callback) {
    this.callback = callback;
  }

  public interface Callback {
    void onRibetInternColorSelector(FulardColor color);

    void onRibetExternColorSelector(FulardColor color);

    void onRibetMiddleColorSelector(FulardColor color);

    class Null implements RibetTresRibetsColorSelectorFragment.Callback {

      @Override
      public void onRibetInternColorSelector(FulardColor color) {

      }

      @Override
      public void onRibetExternColorSelector(FulardColor color) {

      }

      @Override
      public void onRibetMiddleColorSelector(FulardColor color) {

      }
    }
  }
}

