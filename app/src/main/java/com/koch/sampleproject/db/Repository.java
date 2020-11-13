package com.koch.sampleproject.db;

public interface Repository {
    boolean insert(Object obj);

    boolean update(Object obj);

    Object get(Object id);

    boolean delete(Object obj);
}
