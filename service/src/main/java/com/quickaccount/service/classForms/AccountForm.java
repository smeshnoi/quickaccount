package com.quickaccount.service.classForms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountForm {
    private String searchText;
    private String typeAccount;
    int limitPage;
}
