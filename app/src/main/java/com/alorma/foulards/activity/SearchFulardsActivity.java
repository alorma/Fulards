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
import com.alorma.foulards.view.Fulard;
import com.alorma.foulards.view.FulardCustomization;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.AsyncSubscription;
import io.reactivex.schedulers.Schedulers;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import org.reactivestreams.Subscriber;

public class SearchFulardsActivity extends AppCompatActivity {

  @BindView(R.id.logger) TextView logger;

  private Fulard fulard;
  private FirebaseDatabase database;
  private Disposable disposableConfigurations;

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
    readFirebaseFulardsConfigurations(database, search);
  }

  private void readFirebaseFulardsConfigurations(FirebaseDatabase database, FulardSearch search) {
    disposableConfigurations = Flowable.fromPublisher((Subscriber<? super Map.Entry<String, FulardConfiguration>> subscriber) -> {
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
        .toList()
        .subscribe(o -> {
        }, Throwable::printStackTrace);
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
    logger.append("---");
    logger.append("\n");
  }

  private Flowable<AbstractMap.SimpleEntry<String, Agrupament>> loadAgrupaments(List<String> agrupamentsId) {
    return Flowable.fromPublisher((Subscriber<? super DataSnapshot> s) -> {
      DatabaseReference agrupaments = database.getReference("agrupaments");
      agrupaments.keepSynced(true);
      agrupaments.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          s.onNext(dataSnapshot);
          s.onComplete();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          s.onError(databaseError.toException());
        }
      });
    }).subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .map(DataSnapshot::getChildren)
        .flatMap(Flowable::fromIterable)
        .filter(dataSnapshot -> agrupamentsId.contains(dataSnapshot.getKey()))
        .map(dataSnapshot -> {
          String key = dataSnapshot.getKey();
          Agrupament agrupament = dataSnapshot.getValue(Agrupament.class);
          return new AbstractMap.SimpleEntry<>(key, agrupament);
        });
  }

  @Override
  protected void onStop() {
    if (disposableConfigurations != null) {
      disposableConfigurations.dispose();
    }
    super.onStop();
  }

  private class Extras {
    public static final String EXTRA_CUSTOM = "EXTRA_CUSTOM";
    public static final String EXTRA_SEARCH = "EXTRA_SEARCH";
  }
}
