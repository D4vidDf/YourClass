package com.d4viddf.Factory;

import java.sql.Connection;
import java.util.List;

public interface Dao<T> {
    T get(Connection con,int id);

    List<T> getAll(Connection conn);
}
