package com.example.practical_exam_hend_alasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {

    public static TabFragment newInstance(String name) {

        TabFragment fragment = new TabFragment();

        Bundle bundle = new Bundle();
        bundle.putString("name", name);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_tab,
                container,
                false
        );

        TextView text = view.findViewById(R.id.tabText);

        String name = getArguments().getString("name");

        text.setText(name);

        return view;
    }
}
