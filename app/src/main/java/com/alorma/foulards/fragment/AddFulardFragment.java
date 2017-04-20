package com.alorma.foulards.fragment;

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
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.R;
import com.alorma.foulards.activity.FulardSearchBuilderActivity;
import com.alorma.foulards.activity.SearchFulardsActivity;
import com.alorma.foulards.data.Agrupament;
import com.alorma.foulards.data.FulardSearch;
import com.alorma.foulards.view.FulardCustomization;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;

public class AddFulardFragment extends Fragment {

  private int REQUEST_CODE_FULARD = 1212;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @BindView(R.id.selctorFulard) View selctorFulard;
  @BindView(R.id.addFulard) View addFulard;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    return inflater.inflate(R.layout.fragment_add_fulards, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    FulardType[] values = FulardType.values();
    for (FulardType value : values) {
      FulardCustomization customization = new FulardCustomization();
      customization.setType(value);
    }

    selctorFulard.setOnClickListener(v -> onSelectFulard());
    addFulard.setOnClickListener(v -> onAddFulard());
  }

  private void onSelectFulard() {
    Intent intent = new Intent(getContext(), FulardSearchBuilderActivity.class);
    startActivityForResult(intent, REQUEST_CODE_FULARD);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == REQUEST_CODE_FULARD) {
        FulardCustomization customization =
            (FulardCustomization) data.getExtras().getSerializable(FulardSearchBuilderActivity.Extras.EXTRA_CUSTOMIZATION);
        FulardSearch search = (FulardSearch) data.getExtras().getSerializable(FulardSearchBuilderActivity.Extras.EXTRA_SEARCH);
        onSearchLoaded(customization, search);
      }
    }
  }

  private void onSearchLoaded(FulardCustomization customization, FulardSearch search) {

  }

  private void onAddFulard() {
    UUID uuid = UUID.randomUUID();

    Agrupament agrupament = new Agrupament("Test", false);

    FulardSearch request = new FulardSearch();
    request.setFulardType(FulardType.fulard_11);
    request.setFulardColor(FulardColor.blau_cel);
    request.setRibetColor(FulardColor.grana);

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //database.getReference("agrupaments").child(uuid.toString()).setValue(agrupament);
    //database.getReference("customization").child(uuid.toString()).setValue(request);
  }
}
