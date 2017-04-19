package com.alorma.foulards.fragment.fulard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.R;
import com.alorma.foulards.view.Fulard;

public class FoulardShapeTriangleSimpleFragment extends Fragment {

  public static FoulardShapeTriangleSimpleFragment newInstance() {
    return new FoulardShapeTriangleSimpleFragment();
  }

  @BindView(R.id.fulardSimple) Fulard fulardSimple;
  @BindView(R.id.fulardSimpleAmbRibet) Fulard fulardSimpleAmbRibet;
  @BindView(R.id.fulardSimpleAmbRibetDoble) Fulard fulardSimpleAmbRibetDoble;
  @BindView(R.id.fulardSimpleAmbRibetTripe) Fulard fulardSimpleAmbRibetTripe;
  @BindView(R.id.fulardSimpleAmbRibetCuatre) Fulard fulardSimpleAmbRibetCuatre;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_fulards_simple, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
  }
}
