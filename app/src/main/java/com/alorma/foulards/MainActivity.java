package com.alorma.foulards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.activity.AddFulardActivity;
import com.alorma.foulards.activity.FulardSearchBuilderActivity;
import com.alorma.foulards.activity.SearchFulardsActivity;
import com.alorma.foulards.data.FulardSearch;
import com.alorma.foulards.view.FulardCustomization;

public class MainActivity extends AppCompatActivity {

  private static final int REQUEST_CODE_FULARD = 21;

  @BindView(R.id.search) View buttonSearch;
  @BindView(R.id.add) View buttonAdd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    buttonSearch.setOnClickListener(v -> onSearchFulard());
    buttonAdd.setOnClickListener(v -> onAddFulard());
  }

  private void onSearchFulard() {
    Intent intent = new Intent(this, FulardSearchBuilderActivity.class);
    startActivityForResult(intent, REQUEST_CODE_FULARD);
  }

  private void onAddFulard() {
    Intent intent = new Intent(this, AddFulardActivity.class);
    startActivity(intent);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {
      if (requestCode == REQUEST_CODE_FULARD) {
        FulardCustomization customization =
            (FulardCustomization) data.getExtras().getSerializable(FulardSearchBuilderActivity.Extras.EXTRA_CUSTOMIZATION);
        FulardSearch search = (FulardSearch) data.getExtras().getSerializable(FulardSearchBuilderActivity.Extras.EXTRA_SEARCH);
        onSearchLoaded(customization, search);
      }
    }
  }

  private void onSearchLoaded(FulardCustomization customization, FulardSearch search) {
    Intent intent = SearchFulardsActivity.createIntent(this, customization, search);
    startActivity(intent);
  }
}
