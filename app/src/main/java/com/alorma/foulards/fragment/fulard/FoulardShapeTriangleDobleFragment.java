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

public class FoulardShapeTriangleDobleFragment extends Fragment {

  public static FoulardShapeTriangleDobleFragment newInstance() {
    return new FoulardShapeTriangleDobleFragment();
  }

  @BindView(R.id.fulardDosColorsSenseRibet) Fulard fulardDosColorsSenseRibet;
  @BindView(R.id.fulardDosColorsAmbRibet) Fulard fulardDosColorsAmbRibet;
  @BindView(R.id.fulardDosColorsAmbRibetDoble) Fulard fulardDosColorsAmbRibetDoble;
  @BindView(R.id.fulardSimpleAmbRibetDoble) Fulard fulardSimpleAmbRibetDoble;

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

    customSenseRibet();
    customAmbRibet();
    customAmbRibetDoble();
    customSimpleAmbRibetDoble();
  }

  private void customSenseRibet() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardDretaColor(Color.YELLOW);
    customization.setFulardEsquerraColor(Color.GREEN);

    fulardDosColorsSenseRibet.fill(customization);
  }

  private void customAmbRibet() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardDretaColor(Color.YELLOW);
    customization.setFulardEsquerraColor(Color.GREEN);
    customization.setRibetColor(Color.BLACK);

    fulardDosColorsAmbRibet.fill(customization);
  }

  private void customAmbRibetDoble() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardDretaColor(Color.YELLOW);
    customization.setFulardEsquerraColor(Color.GREEN);
    customization.setRibetDretaColor(Color.RED);
    customization.setRibetEsquerraColor(Color.BLUE);

    fulardDosColorsAmbRibetDoble.fill(customization);
  }

  private void customSimpleAmbRibetDoble() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardColor(Color.YELLOW);
    customization.setRibetDretaColor(Color.RED);
    customization.setRibetEsquerraColor(Color.BLUE);

    fulardSimpleAmbRibetDoble.fill(customization);
  }
}
