package com.quickaccount;

import com.quickaccount.entity.BaseIdEntity;

import java.util.List;

public interface BaseDao<T extends BaseIdEntity> {
    T findById(Long id);
    List<T> findAll();
    Long save(T objectToSave);
    Long update(T objectToUpdate);
    Long delete(T objectToDelete);
}
