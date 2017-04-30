package ru.tinkoff.shishkova.tfsproject.ui.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import ru.tinkoff.shishkova.tfsproject.model.db.ChatDbHelper;
import ru.tinkoff.shishkova.tfsproject.ui.items.DialogItem;
import ru.tinkoff.shishkova.tfsproject.ui.adapters.DialogsAdapter;
import ru.tinkoff.shishkova.tfsproject.OnItemClickListener;
import ru.tinkoff.shishkova.tfsproject.R;
import ru.tinkoff.shishkova.tfsproject.ui.activities.DialogActivity;
import ru.tinkoff.shishkova.tfsproject.model.db.DbContract;
import ru.tinkoff.shishkova.tfsproject.App;

public class DialogsFragment extends Fragment {

    private RecyclerView recyclerView;
    private DialogsAdapter adapter;
    private SQLiteDatabase writableDatabase;

    public static DialogsFragment newInstance() {
        DialogsFragment fragment = new DialogsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialogs, container, false);
        ChatDbHelper dbHelper = new ChatDbHelper(getActivity());
        writableDatabase = dbHelper.getWritableDatabase();
        initRecyclerView(view);
        ArrayList<DialogItem> dialogItems = getPreviousDialogItems();
        adapter.setItems(dialogItems);
        Button addDialog = (Button)view.findViewById(R.id.add_dialog);
        addDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogItem();
            }
        });
        return view;
    }

    private void addDialogItem() {
        int itemCount = adapter.getItemCount();
        DialogItem dialogItem = new DialogItem("Title is " + itemCount, "Description is " + itemCount, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.DialogEntry.COLUMN_TITLE, dialogItem.getTitle());
        contentValues.put(DbContract.DialogEntry.COLUMN_DESCRIPTION, dialogItem.getTitle());
        contentValues.put(DbContract.DialogEntry.COLUMN_TIMESTAMP, new Date().getTime());
        contentValues.put(DbContract.DialogEntry.COLUMN_TYPE, dialogItem.getType());
        writableDatabase.insert(DbContract.DialogEntry.TABLE_NAME, null, contentValues);
        adapter.addDialog(dialogItem);
    }

    @NonNull
    private ArrayList<DialogItem> getPreviousDialogItems() {
        Cursor cursor = writableDatabase.query(DbContract.DialogEntry.TABLE_NAME,
                new String[]{
                        DbContract.DialogEntry.COLUMN_TITLE,
                        DbContract.DialogEntry.COLUMN_DESCRIPTION
                },
                null,
                null,
                null,
                null,
                null);
        ArrayList<DialogItem> dialogItems = new ArrayList<>();
        while (cursor.moveToNext()) {
            int titleIndex = cursor.getColumnIndex(DbContract.DialogEntry.COLUMN_TITLE);
            int descriptionIndex = cursor.getColumnIndex(DbContract.DialogEntry.COLUMN_DESCRIPTION);
            String title = cursor.getString(titleIndex);
            String description = cursor.getString(descriptionIndex);
            dialogItems.add(new DialogItem(title, description, 0));
        }
        cursor.close();
        return dialogItems;
    }

    private void initRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_dialogs);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //adapter = new DialogsAdapter(createDataset(), new OnItemClickListener() {
        adapter = new DialogsAdapter(new ArrayList<DialogItem>(), new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                openDialog();
            }
        });
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    /*private ArrayList<DialogItem> createDataset() {
        ArrayList<DialogItem> list = new ArrayList<>();
        list.add(new DialogItem("title", "desc", 1));
        list.add(new DialogItem("title", "desc", 0));
        list.add(new DialogItem("title", "desc", 1));
        list.add(new DialogItem("title", "desc", 1));
        list.add(new DialogItem("title", "desc", 0));
        list.add(new DialogItem("title", "desc", 1));
        return list;
    }*/

    private void openDialog() {
        Intent intent = new Intent(getActivity(), DialogActivity.class);
        startActivity(intent);
    }
}
