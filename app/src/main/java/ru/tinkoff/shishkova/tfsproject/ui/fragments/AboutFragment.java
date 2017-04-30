package ru.tinkoff.shishkova.tfsproject.ui.fragments;

import android.os.Bundle;
import android.support.transition.ChangeBounds;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import ru.tinkoff.shishkova.tfsproject.R;


public class AboutFragment extends Fragment {

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);
        final ViewGroup sceneRoot = (ViewGroup) view.findViewById(R.id.scene_root);

        Button buttonAbout = (Button)view.findViewById(R.id.btn_about);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Scene scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, getActivity());
                Transition transition = new ChangeBounds();
                transition.setInterpolator(new AccelerateInterpolator());
                transition.setDuration(800);
                TransitionManager.go(scene2, transition);
            }
        });

        return view;
    }
}
