package com.alorma.foulards.fragment.fulard;

import android.graphics.Color;
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
import com.alorma.foulards.view.FulardCustomization;

public class FoulardShapeSenyeraFragment extends Fragment {

  public static FoulardShapeSenyeraFragment newInstance() {
    return new FoulardShapeSenyeraFragment();
  }

  @BindView(R.id.fulardSenyeraSimple) Fulard fulardSenyeraSimple;
  @BindView(R.id.fulardSenyeraIntern) Fulard fulardSenyeraIntern;

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

    customSimple();
    customSenyeraIntern();
  }

  private void customSimple() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardColor(Color.GREEN);

    fulardSenyeraSimple.fill(customization);
  }

  private void customSenyeraIntern() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardColor(Color.GREEN);
    customization.setRibetExtern(Color.RED);

    fulardSenyeraIntern.fill(customization);
  }
}
