package com.quickaccount;

import com.quickaccount.entity.TypeAccount;
import org.springframework.stereotype.Repository;

@Repository
public class TypeAccountDaoImpl extends BaseDaoImpl<TypeAccount> implements TypeAccountDao {

    public TypeAccountDaoImpl() {
        super(TypeAccount.class);
    }
}
