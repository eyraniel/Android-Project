package ru.tinkoff.shishkova.tfsproject;

public class DialogItem {

    private String title;
    private String desc;
    private int type; // 0 = dialog, 1 = chat

    public DialogItem(String title, String desc, int type) {
        this.title = title;
        this.desc = desc;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }
}
