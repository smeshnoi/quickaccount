package com.quickaccount;

import com.quickaccount.entity.BaseIdEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class BaseDaoImpl<T extends BaseIdEntity> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityClass;

    public BaseDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
    }

    @Override
    public T findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        T result = session.get(entityClass, id);
        return result;
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<T> list = session.createQuery("select t from " + entityClass.getName() + " t", entityClass)
                .getResultList();
        return list;
    }

    @Override
    public Long save(T objectToSave) {
        Session session = sessionFactory.getCurrentSession();
        session.save(objectToSave);
        return objectToSave.getId();
    }

    @Override
    public Long update(T objectToUpdate) {
        Session session = sessionFactory.getCurrentSession();
        session.update(objectToUpdate);
        return objectToUpdate.getId();
    }

    @Override
    public Long delete(T objectToDelete) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(objectToDelete);
        return objectToDelete.getId();
    }
}
