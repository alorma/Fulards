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

    customSimple();
    customSimpleAmbRibet();
    customSimpleAmbRibetDoble();
    customSimpleAmbRibetTriple();
    customSimpleAmbRibetCuatre();
  }

  private void customSimple() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardColor(Color.LTGRAY);

    fulardSimple.fill(customization);
  }

  private void customSimpleAmbRibet() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardColor(Color.BLACK);
    customization.setRibetColor(Color.RED);

    fulardSimpleAmbRibet.fill(customization);
  }

  private void customSimpleAmbRibetDoble() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardColor(Color.BLUE);
    customization.setRibetIntern(Color.BLACK);
    customization.setRibetExtern(Color.parseColor("#cccc00"));

    fulardSimpleAmbRibetDoble.fill(customization);
  }

  private void customSimpleAmbRibetTriple() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardColor(Color.MAGENTA);
    customization.setRibetIntern(Color.BLACK);
    customization.setRibetMiddle(Color.GREEN);
    customization.setRibetExtern(Color.YELLOW);

    fulardSimpleAmbRibetTripe.fill(customization);
  }

  private void customSimpleAmbRibetCuatre() {
    FulardCustomization customization = new FulardCustomization();
    customization.setFulardColor(Color.MAGENTA);
    customization.setRibetIntern(Color.BLACK);
    customization.setRibetMiddle1(Color.parseColor("#cccc00"));
    customization.setRibetMiddle2(Color.GREEN);
    customization.setRibetExtern(Color.YELLOW);

    fulardSimpleAmbRibetCuatre.fill(customization);
  }
}
