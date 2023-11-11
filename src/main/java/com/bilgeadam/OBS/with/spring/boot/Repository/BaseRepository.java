package com.bilgeadam.OBS.with.spring.boot.Repository;

import java.util.List;

public interface BaseRepository<T> {
    List<T> getAll();

    T findById(long id);

    boolean deleteById(long id);

    boolean save(T t);
}
