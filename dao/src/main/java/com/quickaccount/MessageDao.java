package com.quickaccount;

public class MessageDao {
    private static MessageDao INSTANCE = null;

    public MessageDao() {

    }

    public static MessageDao getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (MessageDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MessageDao();
                }
            }
        }
        return INSTANCE;
    }

    public String getMessage() {
        String string = "Unknown page";
        return string;
    }
}
