package com.quickaccount;

import com.quickaccount.connection.ConnectionManager;
import com.quickaccount.entity.BaseIdEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class BaseDao<T extends BaseIdEntity> {

    private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(Long id) {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        T result = session.get(entityClass, id);

        transaction.commit();
        session.close();
        return result;
    }

    public List<T> findAll() {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<T> list = session.createQuery("select t from " + entityClass.getName() +" t", entityClass)
                .list();

        transaction.commit();
        session.close();

        return list;
    }


    public Long save(T objectToSave) {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(objectToSave);
        transaction.commit();
        session.close();
        return objectToSave.getId();
    }


    public Long update(T objectToUpdate) {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(objectToUpdate);

        transaction.commit();
        session.close();
        return objectToUpdate.getId();
    }

    public Long delete(T objectToDelete) {
        Session session = ConnectionManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(objectToDelete);

        transaction.commit();
        session.close();
        return objectToDelete.getId();
    }

}
