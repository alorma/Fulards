package com.alorma.foulards.data.datasource;

import com.alorma.foulards.data.Agrupament;
import io.reactivex.Single;
import java.util.Map;

public interface AgrupamentsListDataSource {
  Single<Map<String, Agrupament>> getAgrupaments();
}
