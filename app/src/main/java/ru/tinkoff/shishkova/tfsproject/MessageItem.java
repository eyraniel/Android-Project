package ru.tinkoff.shishkova.tfsproject;

public class MessageItem {

    private String mes;
    private int author;

    public MessageItem(String mes, int author) {
        this.mes = mes;
        this.author = author;
    }

    public String getMes() {
        return mes;
    }

    public int getAuthor() {
        return author;
    }

}
