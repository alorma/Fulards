package com.alorma.foulards.fragment;

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
import com.alorma.foulards.data.Agrupament;
import com.alorma.foulards.data.request.AgrupamentAddRequest;
import com.alorma.foulards.data.request.FulardRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;

public class AddFulardFragment extends Fragment {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

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

    addFulard.setOnClickListener(v -> onAddFulard());
  }

  private void onAddFulard() {
    UUID uuid = UUID.randomUUID();

    Agrupament agrupament = new Agrupament("Test", false);

    FulardRequest request = new FulardRequest();
    request.setFulardType(FulardType.fulard_11.name());
    request.setFulardColor(FulardColor.blau_cel.name());
    request.setRibetColor(FulardColor.grana.name());

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    database.getReference("agrupaments").child(uuid.toString()).setValue(agrupament);
    database.getReference("customization").child(uuid.toString()).setValue(request);
  }
}
