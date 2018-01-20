package com.quickaccount;

public class MessageService {
    private static MessageService INSTANCE = null;

    public MessageService() {
    }

    public static MessageService getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (MessageService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MessageService();
                }
            }
        }
        return INSTANCE;
    }

    public String generateMessage() {
        String string;
        string = MessageDao.getINSTANCE().getMessage();
        //System.out.println(string);
        return string;
    }
}
