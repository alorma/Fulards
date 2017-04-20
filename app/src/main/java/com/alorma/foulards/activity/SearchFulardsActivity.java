package com.alorma.foulards.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.AgrupamentItemViewModel;
import com.alorma.foulards.R;
import com.alorma.foulards.adapter.AgrupamentsAdapter;
import com.alorma.foulards.data.FulardSearch;
import com.alorma.foulards.data.datasource.AgrupamentsListDataSource;
import com.alorma.foulards.data.datasource.FirebaseAgrupamentsListDataSource;
import com.alorma.foulards.data.datasource.FirebaseFulardConfigurationsListDataSource;
import com.alorma.foulards.data.datasource.FulardConfigurationsListDataSource;
import com.alorma.foulards.data.usecase.LoadAgrupamentsUseCase;
import com.alorma.foulards.view.FulardCustomization;
import com.google.firebase.database.FirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SearchFulardsActivity extends AppCompatActivity {

  @BindView(R.id.recyclerView) RecyclerView recyclerView;
  @BindView(R.id.fabAddAgrupament) FloatingActionButton fabAddAgrupament;

  private FirebaseDatabase database;
  private CompositeDisposable disposable;
  private AgrupamentsAdapter agrupamentsAdapter;

  public static Intent createIntent(Context context, FulardCustomization customization, FulardSearch search) {
    Intent intent = new Intent(context, SearchFulardsActivity.class);

    intent.putExtra(Extras.EXTRA_CUSTOM, customization);
    intent.putExtra(Extras.EXTRA_SEARCH, search);

    return intent;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);
    ButterKnife.bind(this);

    disposable = new CompositeDisposable();

    if (getIntent() != null && getIntent().getExtras() != null) {
      Bundle extras = getIntent().getExtras();
      FulardCustomization customization = (FulardCustomization) extras.getSerializable(Extras.EXTRA_CUSTOM);
      FulardSearch search = (FulardSearch) extras.getSerializable(Extras.EXTRA_SEARCH);

      if (customization != null && search != null) {
        downloadFirbeaseDB(search);
      }

      agrupamentsAdapter = new AgrupamentsAdapter(LayoutInflater.from(this));
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setAdapter(agrupamentsAdapter);
    }
  }

  private void downloadFirbeaseDB(FulardSearch search) {
    database = FirebaseDatabase.getInstance();

    AgrupamentsListDataSource agrupamentsListDataSource = new FirebaseAgrupamentsListDataSource(database);
    FulardConfigurationsListDataSource configurationsListDataSource = new FirebaseFulardConfigurationsListDataSource(database);
    LoadAgrupamentsUseCase useCase = new LoadAgrupamentsUseCase(agrupamentsListDataSource, configurationsListDataSource);

    Disposable subscribe = useCase.getAgrupaments(search)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::showAgrupament, Throwable::printStackTrace,
            this::onCompleteLoading);

    disposable.add(subscribe);
  }

  private void showAgrupament(AgrupamentItemViewModel agrupament) {
    agrupamentsAdapter.add(agrupament);
  }

  private void onCompleteLoading() {
    fabAddAgrupament.setVisibility(View.VISIBLE);
    fabAddAgrupament.show();
  }

  @Override
  protected void onStop() {
    if (disposable != null) {
      disposable.clear();
    }
    super.onStop();
  }

  private class Extras {
    public static final String EXTRA_CUSTOM = "EXTRA_CUSTOM";
    public static final String EXTRA_SEARCH = "EXTRA_SEARCH";
  }
}
