package com.quickaccount;

public class MessageService {
    private static MessageService instance = null;

    public MessageService() {
    }

    public static MessageService getInstance() {
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
        string = MessageDao.getInstance().getMessage();
        return string;
    }
}
