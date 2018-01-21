package com.quickaccount;

public class MessageDao {
    private static MessageDao instance = null;

    public MessageDao() {

    }

    public static MessageDao getINSTANCE() {
        if (instance == null) {
            synchronized (MessageDao.class) {
                if (instance == null) {
                    instance = new MessageDao();
                }
            }
        }
        return instance;
    }

    public String getMessage() {
        String string = "This page generate dao layer";
        return string;
    }
}
