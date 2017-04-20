package com.alorma.foulards.data.datasource;

import com.alorma.foulards.data.FulardConfiguration;
import com.alorma.foulards.data.FulardSearch;
import io.reactivex.Single;
import java.util.Map;

public interface FulardConfigurationsListDataSource {
  Single<Map<String, FulardConfiguration>> getConfigurations(FulardSearch search);
}
