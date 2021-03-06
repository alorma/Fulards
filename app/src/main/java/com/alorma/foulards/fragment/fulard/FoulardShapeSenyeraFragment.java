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

public class FoulardShapeSenyeraFragment extends FulardSelectorFragment {

  @BindView(R.id.fulardSenyeraSimple) Fulard fulardSenyeraSimple;
  @BindView(R.id.fulardSenyeraIntern) Fulard fulardSenyeraIntern;

  public static FoulardShapeSenyeraFragment newInstance() {
    return new FoulardShapeSenyeraFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_fulards_senyera, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    FulardClickListener onClickListener = new FulardClickListener();
    fulardSenyeraSimple.setOnClickListener(onClickListener);
    fulardSenyeraIntern.setOnClickListener(onClickListener);
  }
}
