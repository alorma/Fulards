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
import com.alorma.foulards.activity.ColorsActivity;
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.R;

public class FulardSimpleBaseColorSelectorFragment extends Fragment {

  private static final int REQUEST_CODE_COLOR = 22;

  private Callback callback;

  @BindView(R.id.colorSelector) View colorSelector;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_fulard_base_color_selector, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    colorSelector.setOnClickListener(v -> {
      openColorSelector();
    });
  }

  private void openColorSelector() {
    Intent intent = new Intent(getContext(), ColorsActivity.class);
    startActivityForResult(intent, REQUEST_CODE_COLOR);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == REQUEST_CODE_COLOR) {
        FulardColor color = (FulardColor) data.getExtras().get(ColorsActivity.Extras.EXTRA_COLOR);
        getCallback().onFulardBaseColorSelector(color);
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
    void onFulardBaseColorSelector(FulardColor color);

    class Null implements Callback {
      @Override
      public void onFulardBaseColorSelector(FulardColor color) {

      }
    }
  }
}