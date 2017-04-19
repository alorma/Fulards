package com.alorma.foulards.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.R;
import com.alorma.foulards.adapter.ColorsAdapter;
import java.util.Arrays;

public class ColorSelectorFragment extends Fragment {

  private Callback callback;

  @BindView(R.id.colorsRecycler) RecyclerView recyclerView;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.fragment_color_selector, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    showColors();
  }

  private void showColors() {
    ColorsAdapter adapter = new ColorsAdapter(LayoutInflater.from(getContext()));
    adapter.setCallback(this::onColorSelected);
    adapter.add(Arrays.asList(FulardColor.values()));

    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    recyclerView.setAdapter(adapter);
  }

  private void onColorSelected(FulardColor color) {
    getCallback().onColorSelected(color);
  }

  public Callback getCallback() {
    return callback != null ? callback : new ColorSelectorFragment.Callback.Null();
  }

  public void setCallback(ColorSelectorFragment.Callback callback) {
    this.callback = callback;
  }

  public interface Callback {
    void onColorSelected(FulardColor color);

    class Null implements Callback {
      @Override
      public void onColorSelected(FulardColor color) {

      }
    }
  }
}
