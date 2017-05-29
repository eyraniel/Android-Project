package ru.tinkoff.shishkova.tfsproject.firebase;

public interface OnTransactionComplete<T> {
    void onCommit(T result);

    void onAbort(Exception e);
}
