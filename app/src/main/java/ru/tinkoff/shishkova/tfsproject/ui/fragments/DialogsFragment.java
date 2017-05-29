package ru.tinkoff.shishkova.tfsproject.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.tinkoff.shishkova.tfsproject.OnItemClickListener;
import ru.tinkoff.shishkova.tfsproject.R;
import ru.tinkoff.shishkova.tfsproject.ui.activities.AddDialogActivity;
import ru.tinkoff.shishkova.tfsproject.ui.activities.DialogActivity;
import ru.tinkoff.shishkova.tfsproject.ui.adapters.DialogsAdapter;
import ru.tinkoff.shishkova.tfsproject.ui.items.DialogItem;

public class DialogsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

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
        initRecyclerView(view);

        Button buttonAddDialog = (Button)view.findViewById(R.id.btn_add_dialog);
        buttonAddDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });

        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_dialogs);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DialogsAdapter(createDataset(), new OnItemClickListener() {
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

    private List<DialogItem> createDataset() {
        List<DialogItem> list = new ArrayList<>();
        list.add(new DialogItem("title", "desc"));
        list.add(new DialogItem("title", "desc"));
        list.add(new DialogItem("title", "desc"));
        list.add(new DialogItem("title", "desc"));
        list.add(new DialogItem("title", "desc"));
        list.add(new DialogItem("title", "desc"));
        return list;
    }

    private void openDialog() {
        Intent intent = new Intent(getActivity(), DialogActivity.class);
        startActivity(intent);
    }

    private void createDialog() {
        Intent intent = new Intent(getActivity(), AddDialogActivity.class);
        startActivity(intent);
    }
}
