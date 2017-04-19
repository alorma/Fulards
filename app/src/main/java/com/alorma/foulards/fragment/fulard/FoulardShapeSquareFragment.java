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

public class FoulardShapeSquareFragment extends FulardSelectorFragment {

  @BindView(R.id.fulardCuadratSimple) Fulard fulardCuadratSimple;
  @BindView(R.id.fulardCuadratSimpleAmbRibet) Fulard fulardCuadratSimpleAmbRibet;
  @BindView(R.id.fulardCuadratSimpleAmbRibetDoble) Fulard fulardCuadratSimpleAmbRibetDoble;

  public static FoulardShapeSquareFragment newInstance() {
    return new FoulardShapeSquareFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_fulards_square, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    FulardClickListener onClickListener = new FulardClickListener();
    fulardCuadratSimple.setOnClickListener(onClickListener);
    fulardCuadratSimpleAmbRibet.setOnClickListener(onClickListener);
    fulardCuadratSimpleAmbRibetDoble.setOnClickListener(onClickListener);
  }
}
