package ru.tinkoff.shishkova.tfsproject.firebase;

import java.util.List;

import ru.tinkoff.shishkova.tfsproject.ui.items.DialogItem;

public interface DialogItemValueListener {

    void onValue(List<DialogItem> items);
}
