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
import com.alorma.foulards.activity.ColorsActivity;
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.R;

public class RibetDosColorSelectorFragment extends Fragment {

  private static final int REQUEST_CODE_COLOR_DRETA = 22;
  private static final int REQUEST_CODE_COLOR_ESQUERRA = 23;

  private Callback callback;

  @BindView(R.id.colorSelectorDreta) View colorSelectorDreta;
  @BindView(R.id.colorSelectorEsquerra) View colorSelectorEsquerra;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_ribet_dos_colors_selector, null, false);
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
      if (requestCode == REQUEST_CODE_COLOR_DRETA) {
        FulardColor color = (FulardColor) data.getExtras().get(ColorsActivity.Extras.EXTRA_COLOR);
        getCallback().onRibetColorDretaSelector(color);
      } else if (requestCode == REQUEST_CODE_COLOR_ESQUERRA) {
        FulardColor color = (FulardColor) data.getExtras().get(ColorsActivity.Extras.EXTRA_COLOR);
        getCallback().onRibetColorEsquerraSelector(color);
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
    void onRibetColorDretaSelector(FulardColor color);

    void onRibetColorEsquerraSelector(FulardColor color);

    class Null implements Callback {

      @Override
      public void onRibetColorDretaSelector(FulardColor color) {

      }

      @Override
      public void onRibetColorEsquerraSelector(FulardColor color) {

      }
    }
  }
}
