package com.quickaccount;

import com.quickaccount.entity.BaseIdEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class BaseDao<T extends BaseIdEntity> {
    private static final SessionFactory SESSION_FACTORY
            = new Configuration().configure().buildSessionFactory();

    private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(Long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        T result = session.get(entityClass, id);

        transaction.commit();
        session.close();
        return result;
    }

    public List<T> findAll() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        List<T> list = session.createQuery("select t from " + entityClass.getName() +" t", entityClass)
                .list();

        transaction.commit();
        session.close();

        return list;
    }


    public Long save(T objectToSave) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(objectToSave);
        transaction.commit();
        session.close();
        return objectToSave.getId();
    }

    public Long update(T objectToUpdate) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(objectToUpdate);

        session.close();
        return objectToUpdate.getId();
    }

    public Long delete(T objectToDelete) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(objectToDelete);

        session.close();
        return objectToDelete.getId();
    }

}
