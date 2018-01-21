package com.quickaccount;

public class MessageService {
    private static MessageService instance = null;

    public MessageService() {
    }

    public static MessageService getINSTANCE() {
        if (instance == null) {
            synchronized (MessageService.class) {
                if (instance == null) {
                    instance = new MessageService();
                }
            }
        }
        return instance;
    }

    public String generateMessage() {
        String string;
        string = MessageDao.getINSTANCE().getMessage();
        return string;
    }
}
