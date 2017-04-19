package com.alorma.foulards.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.R;
import com.alorma.foulards.data.Agrupament;
import com.alorma.foulards.data.FulardSearch;
import com.alorma.foulards.view.Fulard;
import com.alorma.foulards.view.FulardCustomization;
import com.alorma.foulards.view.FulardFactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import org.reactivestreams.Subscriber;

public class SearchFulardsActivity extends AppCompatActivity {

  @BindView(R.id.fulardLayout) ViewGroup fulardLayout;
  private Fulard fulard;
  private FirebaseDatabase database;

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
        FulardType fulardType = search.getFulardType();
        onFulardTypeSelected(fulardType, customization);

        downloadFirbeaseDB(search);
      }
    }
  }

  private void downloadFirbeaseDB(FulardSearch search) {
    database = FirebaseDatabase.getInstance();

    if (search.getFulardType().getBase() == FulardType.Base.simple) {
      if (search.getFulardColor() != null) {
        readFirebaseFulardSimpleColor(database, search, search.getFulardColor());
      } else {
        readFirebaseFulardSimpleNoColor(database, search);
      }
    }
  }

  private void readFirebaseFulardSimpleNoColor(FirebaseDatabase database, FulardSearch search) {
    DatabaseReference fulardTypeRef = database.getReference("fulard_" + search.getFulardType().getBase().name());
    fulardTypeRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        List<String> agrupamentsId = new ArrayList<>();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
          for (DataSnapshot item : snapshot.getChildren()) {
            agrupamentsId.add(item.getValue(String.class));
          }
        }
        onFulardTypesLoaded(agrupamentsId);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  private void readFirebaseFulardSimpleColor(FirebaseDatabase database, FulardSearch search, FulardColor color) {
    DatabaseReference fulardTypeRef =
        database.getReference("fulard_" + search.getFulardType().getBase().name() + "/" + color.name());
    fulardTypeRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        List<String> agrupamentsId = new ArrayList<>();
        for (DataSnapshot item : dataSnapshot.getChildren()) {
          agrupamentsId.add(item.getValue(String.class));
        }
        onFulardTypesLoaded(agrupamentsId);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  private void onFulardTypesLoaded(List<String> agrupamentsId) {
    loadAgrupaments(agrupamentsId);
  }

  private void loadAgrupaments(List<String> agrupamentsId) {
    Flowable<AbstractMap.SimpleEntry<String, Agrupament>> agrupamentsMap = Flowable.fromPublisher((Subscriber<? super DataSnapshot> s) -> {
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

  private void onAgrupamentsFilterError(Throwable throwable) {

  }

  private void onFulardTypeSelected(FulardType fulardType, FulardCustomization customization) {
    fulard = new FulardFactory().get(this, fulardType);

    int width = getResources().getDimensionPixelOffset(R.dimen.fulard_search_size);
    int height = getResources().getDimensionPixelOffset(R.dimen.fulard_search_size);

    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
    params.gravity = Gravity.CENTER | Gravity.START;

    fulardLayout.removeAllViews();
    fulardLayout.addView(fulard, params);

    fulard.fill(customization);
  }

  private class Extras {
    public static final String EXTRA_CUSTOM = "EXTRA_CUSTOM";
    public static final String EXTRA_SEARCH = "EXTRA_SEARCH";
  }
}
