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
import com.alorma.foulards.FulardType;
import com.alorma.foulards.R;
import com.alorma.foulards.data.Agrupament;
import com.alorma.foulards.data.FulardConfiguration;
import com.alorma.foulards.data.FulardSearch;
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
import io.reactivex.internal.subscriptions.AsyncSubscription;
import io.reactivex.schedulers.Schedulers;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
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
    Single<List<AbstractMap.SimpleEntry<String, Agrupament>>> agrupaments = loadAgrupaments();
    Single<List<Map.Entry<String, FulardConfiguration>>> configurations = readFirebaseFulardsConfigurations(database, search);

    Disposable subscribe = Single.zip(agrupaments, configurations, (agrupamentsEntries, configurationEntries) -> {
      Map<String, AgrupamentItemViewModel> map = new HashMap<>();

      for (Map.Entry<String, Agrupament> entry : agrupamentsEntries) {
        if (map.get(entry.getKey()) == null) {
          map.put(entry.getKey(), new AgrupamentItemViewModel());
        }
        map.get(entry.getKey()).setName(entry.getValue().getName());
        map.get(entry.getKey()).setVerified(entry.getValue().isVerified());
      }

      for (Map.Entry<String, FulardConfiguration> entry : configurationEntries) {
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

    disposable.add(disposable);
  }

  private Single<List<Map.Entry<String, FulardConfiguration>>> readFirebaseFulardsConfigurations(FirebaseDatabase database,
      FulardSearch search) {
    return Flowable.fromPublisher((Subscriber<? super Map.Entry<String, FulardConfiguration>> subscriber) -> {
      AsyncSubscription subscription = new AsyncSubscription();
      subscriber.onSubscribe(subscription);
      if (!subscription.isDisposed()) {
        loadFirebaseConfigurations(database, subscriber, subscription);
      } else {
        subscriber.onComplete();
      }
    }).subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .filter(entry -> search.getFulardType().equals(entry.getValue().getFulardType()))
        .filter(entry -> filterFulardColor(search, entry.getValue()))
        .filter(entry -> filterRibetColor(search, entry.getValue()))
        .toList();
  }

  private boolean filterFulardColor(FulardSearch search, FulardConfiguration value) {
    if (FulardType.Base.simple == search.getFulardType().getBase() && search.getFulardColor() != null) {
      return value.getFulardColor() != null && value.getFulardColor().equals(search.getFulardColor());
    } else if (FulardType.Base.doble == search.getFulardType().getBase()) {
      if (search.getFulardColorEsquerra() != null && search.getFulardColorDreta() != null) {
        return value.getFulardEsquerraColor() != null &&
            value.getFulardDretaColor() != null &&
            value.getFulardEsquerraColor().equals(search.getFulardColorEsquerra()) &&
            value.getFulardDretaColor().equals(search.getFulardColorDreta());
      } else if (search.getFulardColorEsquerra() != null) {
        return value.getFulardEsquerraColor() != null &&
            value.getFulardEsquerraColor().equals(search.getFulardColorEsquerra());
      } else if (search.getFulardColorDreta() != null) {
        return value.getFulardDretaColor() != null &&
            value.getFulardDretaColor().equals(search.getFulardColorDreta());
      }
    }
    return true;
  }

  private boolean filterRibetColor(FulardSearch search, FulardConfiguration value) {
    if (FulardType.Ribet.none == search.getFulardType().getRibet()) {
      return true;
    } else if (FulardType.Ribet.un == search.getFulardType().getRibet() && search.getRibetColor() != null) {
      return value.getRibetColor() != null && value.getRibetColor().equals(search.getRibetColor());
    } else if (FulardType.Ribet.dos_colors == search.getFulardType().getRibet()) {
      if (search.getRibetDretaColor() != null && search.getRibetEsquerraColor() != null) {
        return value.getRibetEsquerraColor() != null &&
            value.getRibetDretaColor() != null &&
            value.getRibetEsquerraColor().equals(search.getRibetEsquerraColor()) &&
            value.getRibetDretaColor().equals(search.getRibetDretaColor());
      } else if (search.getRibetEsquerraColor() != null) {
        return value.getRibetEsquerraColor() != null &&
            value.getRibetEsquerraColor().equals(search.getRibetEsquerraColor());
      } else if (search.getRibetDretaColor() != null) {
        return value.getRibetDretaColor() != null &&
            value.getRibetDretaColor().equals(search.getRibetDretaColor());
      }
    } else if (FulardType.Ribet.dos == search.getFulardType().getRibet() &&
        search.getRibetExtern() != null &&
        search.getRibetIntern() != null) {
      return value.getRibetExtern() != null &&
          value.getRibetIntern() != null &&
          value.getRibetExtern().equals(search.getRibetExtern()) &&
          value.getRibetIntern().equals(search.getRibetIntern());
    } else if (FulardType.Ribet.tres == search.getFulardType().getRibet() &&
        search.getRibetExtern() != null &&
        search.getRibetMiddle() != null &&
        search.getRibetIntern() != null) {
      return value.getRibetExtern() != null &&
          value.getRibetIntern() != null &&
          value.getRibetMiddle() != null &&
          value.getRibetExtern().equals(search.getRibetExtern()) &&
          value.getRibetMiddle().equals(search.getRibetMiddle()) &&
          value.getRibetIntern().equals(search.getRibetIntern());
    } else if (FulardType.Ribet.cuatre == search.getFulardType().getRibet() &&
        search.getRibetExtern() != null &&
        search.getRibetMiddleIntern() != null &&
        search.getRibetMiddleExtern() != null &&
        search.getRibetIntern() != null) {
      return value.getRibetExtern() != null &&
          value.getRibetIntern() != null &&
          value.getRibetMiddle() != null &&
          value.getRibetMiddleExtern() != null &&
          value.getRibetExtern().equals(search.getRibetExtern()) &&
          value.getRibetMiddleIntern().equals(search.getRibetMiddleIntern()) &&
          value.getRibetMiddleExtern().equals(search.getRibetMiddleExtern()) &&
          value.getRibetIntern().equals(search.getRibetIntern());
    } else if (FulardType.Ribet.senyera == search.getFulardType().getRibet() && search.isSenyera()) {
      return value.isSenyera();
    } else if (FulardType.Ribet.senyera_extern == search.getFulardType().getRibet()
        && search.isSenyera()
        && search.getRibetExtern() != null) {
      return value.isSenyera() && value.getRibetExtern() != null && value.getRibetExtern().equals(search.getRibetExtern());
    }

    return true;
  }

  private void loadFirebaseConfigurations(FirebaseDatabase database, Subscriber<? super Map.Entry<String, FulardConfiguration>> subscriber,
      Disposable disposable) {
    DatabaseReference fulardTypeRef = database.getReference("customization");
    fulardTypeRef.keepSynced(true);
    fulardTypeRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        if (!disposable.isDisposed()) {
          for (DataSnapshot item : dataSnapshot.getChildren()) {
            if (!disposable.isDisposed()) {
              String key = item.getKey();
              FulardConfiguration value = item.getValue(FulardConfiguration.class);
              Map.Entry<String, FulardConfiguration> entry = new AbstractMap.SimpleEntry<>(key, value);
              subscriber.onNext(entry);
            }
          }
          subscriber.onComplete();
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        subscriber.onError(databaseError.toException());
      }
    });
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

  private Single<List<AbstractMap.SimpleEntry<String, Agrupament>>> loadAgrupaments() {
    return Flowable.fromPublisher((Subscriber<? super DataSnapshot> s) -> {
      AsyncSubscription subscription = new AsyncSubscription();
      s.onSubscribe(subscription);
      loadAgrupamentsFromFirebase(s, subscription);
    }).subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .map(DataSnapshot::getChildren)
        .flatMap(Flowable::fromIterable)
        .map(dataSnapshot -> {
          String key = dataSnapshot.getKey();
          Agrupament agrupament = dataSnapshot.getValue(Agrupament.class);
          return new AbstractMap.SimpleEntry<>(key, agrupament);
        }).toList();
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
