package com.alorma.foulards.data.usecase;

import android.support.annotation.NonNull;
import com.alorma.foulards.AgrupamentItemViewModel;
import com.alorma.foulards.data.Agrupament;
import com.alorma.foulards.data.FulardSearch;
import com.alorma.foulards.data.datasource.AgrupamentsListDataSource;
import com.alorma.foulards.data.datasource.FulardConfigurationsListDataSource;
import com.alorma.foulards.view.FulardCustomization;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;

public class LoadAgrupamentsUseCase {
  private AgrupamentsListDataSource agrupamentsListDataSource;
  private FulardConfigurationsListDataSource configurationsListDataSource;

  public LoadAgrupamentsUseCase(AgrupamentsListDataSource agrupamentsListDataSource,
      FulardConfigurationsListDataSource configurationsListDataSource) {
    this.agrupamentsListDataSource = agrupamentsListDataSource;
    this.configurationsListDataSource = configurationsListDataSource;
  }

  public Flowable<AgrupamentItemViewModel> getAgrupaments(FulardSearch search) {
    return Single.zip(agrupamentsListDataSource.getAgrupaments(),
        configurationsListDataSource.getConfigurations(search),
        this::zip)
        .subscribeOn(Schedulers.computation())
        .map(Map::entrySet)
        .toFlowable()
        .flatMap(Flowable::fromIterable)
        .map(Map.Entry::getValue)
        .filter(entry -> entry.getName() != null)
        .filter(entry -> entry.getFulardCustomization() != null);
  }

  @NonNull
  private Map<String, AgrupamentItemViewModel> zip(Map<String, Agrupament> agrupamentsEntries,
      Map<String, FulardCustomization> configurationEntries) {
    Map<String, AgrupamentItemViewModel> map = new HashMap<>();

    for (Map.Entry<String, Agrupament> entry : agrupamentsEntries.entrySet()) {
      if (map.get(entry.getKey()) == null) {
        map.put(entry.getKey(), new AgrupamentItemViewModel());
      }
      map.get(entry.getKey()).setName(entry.getValue().getName());
      map.get(entry.getKey()).setVerified(entry.getValue().isVerified());
    }

    for (Map.Entry<String, FulardCustomization> entry : configurationEntries.entrySet()) {
      if (map.get(entry.getKey()) == null) {
        map.put(entry.getKey(), new AgrupamentItemViewModel());
      }
      map.get(entry.getKey()).setFulardCustomization(entry.getValue());
    }
    return map;
  }
}
