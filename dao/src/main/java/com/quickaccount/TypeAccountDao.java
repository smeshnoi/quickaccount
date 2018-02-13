package com.quickaccount;

import com.quickaccount.entity.TypeAccount;

public class TypeAccountDao extends BaseDao<TypeAccount> {
    private static TypeAccountDao instance = null;

    public static TypeAccountDao getInstance() {
        if (instance == null) {
            synchronized (TypeAccountDao.class) {
                if (instance == null) {
                    instance = new TypeAccountDao();
                }
            }
        }
        return instance;
    }

    public TypeAccountDao() {
        super(TypeAccount.class);
    }
}
