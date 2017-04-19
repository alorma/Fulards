package com.alorma.foulards.fragment.color.fulard;

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

public class FulardDosColorsBaseColorSelectorFragment extends Fragment {

  private static final int REQUEST_CODE_COLOR_DRETA = 22;
  private static final int REQUEST_CODE_COLOR_ESQUERRA = 23;
  @BindView(R.id.colorSelectorDreta) View colorSelectorDreta;
  @BindView(R.id.colorSelectorEsquerra) View colorSelectorEsquerra;
  private Callback callback;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_fulard_base_dos_colors_selector, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    colorSelectorDreta.setOnClickListener(v -> openColorSelector(REQUEST_CODE_COLOR_DRETA));
    colorSelectorEsquerra.setOnClickListener(v -> openColorSelector(REQUEST_CODE_COLOR_ESQUERRA));
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
      if (requestCode == REQUEST_CODE_COLOR_DRETA) {
        getCallback().onFulardBaseColorDretaSelector(color);
      } else if (requestCode == REQUEST_CODE_COLOR_ESQUERRA) {
        getCallback().onFulardBaseColorEsquerraSelector(color);
      }
    } else if (resultCode == Activity.RESULT_CANCELED) {
      if (requestCode == REQUEST_CODE_COLOR_DRETA) {
        getCallback().onFulardBaseColorDretaSelector(null);
      } else if (requestCode == REQUEST_CODE_COLOR_ESQUERRA) {
        getCallback().onFulardBaseColorEsquerraSelector(null);
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
    void onFulardBaseColorDretaSelector(FulardColor color);

    void onFulardBaseColorEsquerraSelector(FulardColor color);

    class Null implements Callback {
      @Override
      public void onFulardBaseColorDretaSelector(FulardColor color) {

      }

      @Override
      public void onFulardBaseColorEsquerraSelector(FulardColor color) {

      }
    }
  }
}
