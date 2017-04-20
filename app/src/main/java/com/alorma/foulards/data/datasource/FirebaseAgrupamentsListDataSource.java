package com.alorma.foulards.data.datasource;

import com.alorma.foulards.data.Agrupament;
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

public class FirebaseAgrupamentsListDataSource implements AgrupamentsListDataSource {

  private FirebaseDatabase database;

  public FirebaseAgrupamentsListDataSource(FirebaseDatabase database) {
    this.database = database;
  }

  @Override
  public Single<Map<String, Agrupament>> getAgrupaments() {
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
        }).reduce(new HashMap<>(), (map, entry) -> {
          map.put(entry.getKey(), entry.getValue());
          return map;
        });
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
}
