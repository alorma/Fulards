package com.alorma.foulards.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.AgrupamentItemViewModel;
import com.alorma.foulards.R;
import com.alorma.foulards.data.Agrupament;
import com.alorma.foulards.data.FulardConfiguration;
import com.alorma.foulards.data.FulardSearch;
import com.alorma.foulards.data.datasource.FirebaseAgrupamentsListDataSource;
import com.alorma.foulards.data.datasource.FirebaseFulardConfigurationsListDataSource;
import com.alorma.foulards.view.FulardCustomization;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import org.reactivestreams.Subscriber;

public class SearchFulardsActivity extends AppCompatActivity {

  @BindView(R.id.logger) TextView logger;

  private FirebaseDatabase database;
  private CompositeDisposable disposable;

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
    }
  }

  private void downloadFirbeaseDB(FulardSearch search) {
    database = FirebaseDatabase.getInstance();
    Single<Map<String, Agrupament>> agrupaments = loadAgrupaments();
    Single<Map<String, FulardConfiguration>> configurations = readFirebaseFulardsConfigurations(database, search);

    Disposable subscribe = Single.zip(agrupaments, configurations, (agrupamentsEntries, configurationEntries) -> {
      Map<String, AgrupamentItemViewModel> map = new HashMap<>();

      for (Map.Entry<String, Agrupament> entry : agrupamentsEntries.entrySet()) {
        if (map.get(entry.getKey()) == null) {
          map.put(entry.getKey(), new AgrupamentItemViewModel());
        }
        map.get(entry.getKey()).setName(entry.getValue().getName());
        map.get(entry.getKey()).setVerified(entry.getValue().isVerified());
      }

      for (Map.Entry<String, FulardConfiguration> entry : configurationEntries.entrySet()) {
        if (map.get(entry.getKey()) == null) {
          map.put(entry.getKey(), new AgrupamentItemViewModel());
        }
        map.get(entry.getKey()).setFulardConfiguration(entry.getValue());
      }
      return map;
    })
        .subscribeOn(Schedulers.computation())
        .map(Map::entrySet)
        .toFlowable()
        .flatMap(Flowable::fromIterable)
        .map(Map.Entry::getValue)
        .filter(entry -> entry.getName() != null)
        .filter(entry -> entry.getFulardConfiguration() != null)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::showAgrupament, Throwable::printStackTrace);

    disposable.add(subscribe);
  }

  private Single<Map<String, FulardConfiguration>> readFirebaseFulardsConfigurations(FirebaseDatabase database,
      FulardSearch search) {
    return new FirebaseFulardConfigurationsListDataSource(database).getConfigurations(search);
  }

  private void showAgrupament(AgrupamentItemViewModel agrupament) {
    logger.append(agrupament.getName());
    logger.append("\n");
    logger.append(String.valueOf(agrupament.isVerified()));
    logger.append("\n");
    logger.append(String.valueOf(agrupament.getFulardConfiguration()));
    logger.append("\n");
    logger.append("---");
    logger.append("\n");
  }

  private Single<Map<String, Agrupament>> loadAgrupaments() {
    return new FirebaseAgrupamentsListDataSource(database).getAgrupaments();
  }

  private void loadAgrupamentsFromFirebase(Subscriber<? super DataSnapshot> s, Disposable disposable) {
    if (!disposable.isDisposed()) {
      DatabaseReference agrupaments = database.getReference("agrupaments");
      agrupaments.keepSynced(true);
      agrupaments.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          if (!disposable.isDisposed()) {
            s.onNext(dataSnapshot);
          }
          s.onComplete();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          s.onError(databaseError.toException());
        }
      });
    }
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
