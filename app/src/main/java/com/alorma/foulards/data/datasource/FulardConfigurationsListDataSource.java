package com.alorma.foulards.data.datasource;

import com.alorma.foulards.data.FulardSearch;
import com.alorma.foulards.view.FulardCustomization;
import io.reactivex.Single;
import java.util.Map;

public interface FulardConfigurationsListDataSource {
  Single<Map<String, FulardCustomization>> getConfigurations(FulardSearch search);
}
