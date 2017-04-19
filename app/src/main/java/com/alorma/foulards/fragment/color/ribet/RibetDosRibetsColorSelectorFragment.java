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
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.R;
import com.alorma.foulards.activity.ColorsActivity;

public class RibetDosRibetsColorSelectorFragment extends Fragment {

  private static final int REQUEST_CODE_COLOR_INTERN = 23;
  private static final int REQUEST_CODE_COLOR_EXTERN = 22;
  @BindView(R.id.ribetInternSelector) View ribetInternSelector;
  @BindView(R.id.ribetExternSelector) View ribetExternSelector;
  private Callback callback;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_dos_ribets_selector, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    ribetInternSelector.setOnClickListener(v -> openColorSelector(REQUEST_CODE_COLOR_INTERN));
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
      } else if (requestCode == REQUEST_CODE_COLOR_EXTERN) {
        getCallback().onRibetExternColorSelector(color);
      }
    } else if (resultCode == Activity.RESULT_CANCELED) {
      if (requestCode == REQUEST_CODE_COLOR_INTERN) {
        getCallback().onRibetInternColorSelector(null);
      } else if (requestCode == REQUEST_CODE_COLOR_EXTERN) {
        getCallback().onRibetExternColorSelector(null);
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

    class Null implements RibetDosRibetsColorSelectorFragment.Callback {

      @Override
      public void onRibetInternColorSelector(FulardColor color) {

      }

      @Override
      public void onRibetExternColorSelector(FulardColor color) {

      }
    }
  }
}

