package ru.tinkoff.shishkova.tfsproject.ui.items;

public class MessageItem {

    private String mes;
    private int type; // 0 = from me, 1 = from smb in dialog, 2 = from smb in chat
    private String nick;

    public MessageItem(String mes) {
        this.mes = mes;
        this.type = 0;
    }

    public MessageItem(String mes, String nick, int type) {
        this.mes = mes;
        this.nick = nick;
        this.type = type;
    }

    public String getMes() {
        return mes;
    }

    public int getType() {
        return type;
    }

    public String getNick() {
        return nick;
    }
}
