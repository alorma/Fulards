package com.alorma.foulards.fragment.fulard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.R;
import com.alorma.foulards.view.Fulard;

public class FoulardShapeTriangleDobleFragment extends FulardSelectorFragment {

  @BindView(R.id.fulardDosColorsSenseRibet) Fulard fulardDosColorsSenseRibet;
  @BindView(R.id.fulardDosColorsAmbRibet) Fulard fulardDosColorsAmbRibet;
  @BindView(R.id.fulardDosColorsAmbRibetDoble) Fulard fulardDosColorsAmbRibetDoble;
  @BindView(R.id.fulardSimpleAmbRibetDoble) Fulard fulardSimpleAmbRibetDoble;

  public static FoulardShapeTriangleDobleFragment newInstance() {
    return new FoulardShapeTriangleDobleFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_fulards_doble, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    FulardClickListener onClickListener = new FulardClickListener();
    fulardDosColorsSenseRibet.setOnClickListener(onClickListener);
    fulardDosColorsAmbRibet.setOnClickListener(onClickListener);
    fulardDosColorsAmbRibetDoble.setOnClickListener(onClickListener);
    fulardSimpleAmbRibetDoble.setOnClickListener(onClickListener);
  }
}
