package ru.tinkoff.shishkova.tfsproject.ui.items;

public class DialogItem {

    private String title;
    private String desc;
    private int type; // 0 = dialog, 1 = chat

    public DialogItem() {
        //for firebase
    }

    public DialogItem(String title, String desc, int type) {
        this.title = title;
        this.desc = desc;
        this.type = type;
    }

    public DialogItem(String title, String desc) {
        this.title = title;
        this.desc = desc;
        this.type = 0;
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
