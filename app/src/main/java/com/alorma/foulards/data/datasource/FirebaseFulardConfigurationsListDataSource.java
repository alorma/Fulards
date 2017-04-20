package com.alorma.foulards.data.datasource;

import com.alorma.foulards.FulardColor;
import com.alorma.foulards.FulardType;
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
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.AsyncSubscription;
import io.reactivex.schedulers.Schedulers;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import org.reactivestreams.Subscriber;

public class FirebaseFulardConfigurationsListDataSource implements FulardConfigurationsListDataSource {

  private FirebaseDatabase database;

  public FirebaseFulardConfigurationsListDataSource(FirebaseDatabase database) {
    this.database = database;
  }

  @Override
  public Single<Map<String, FulardCustomization>> getConfigurations(FulardSearch search) {
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
        .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), mapConfiguration(entry.getValue())))
        .reduce(new HashMap<>(), (map, entry) -> {
          map.put(entry.getKey(), entry.getValue());
          return map;
        });
  }

  private FulardCustomization mapConfiguration(FulardConfiguration value) {
    FulardCustomization customization = new FulardCustomization();

    if (value.getFulardType() != null) {
      customization.setType(value.getFulardType());
    }
    if (value.getFulardColor() != null) {
      customization.setFulardColor(value.getFulardColor().getColorInt());
    }
    if (value.getFulardDretaColor() != null) {
      customization.setFulardDretaColor(value.getFulardDretaColor().getColorInt());
    }
    if (value.getFulardEsquerraColor() != null) {
      customization.setFulardEsquerraColor(value.getFulardEsquerraColor().getColorInt());
    }
    if (value.getRibetColor() != null) {
      customization.setRibetColor(value.getRibetColor().getColorInt());
    }
    if (value.getRibetDretaColor() != null) {
      customization.setRibetDretaColor(value.getRibetDretaColor().getColorInt());
    }
    if (value.getRibetEsquerraColor() != null) {
      customization.setRibetEsquerraColor(value.getRibetEsquerraColor().getColorInt());
    }
    if (value.getRibetExtern() != null) {
      customization.setRibetExtern(value.getRibetExtern().getColorInt());
    }
    if (value.getRibetMiddle() != null) {
      customization.setRibetMiddle(value.getRibetMiddle().getColorInt());
    }
    if (value.getRibetIntern() != null) {
      customization.setRibetIntern(value.getRibetIntern().getColorInt());
    }
    if (value.getRibetMiddleIntern() != null) {
      customization.setRibetMiddleIntern(value.getRibetMiddleIntern().getColorInt());
    }
    if (value.getRibetMiddleExtern() != null) {
      customization.setRibetMiddleExtern(value.getRibetMiddleExtern().getColorInt());
    }

    return customization;
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
}
