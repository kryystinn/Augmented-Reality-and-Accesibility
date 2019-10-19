package com.example.botanicalgardenapp.ui.location;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.NavigationUI;

import com.example.botanicalgardenapp.MainActivity;
import com.example.botanicalgardenapp.R;

public class LocationFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_location, container, false);
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);

        return root;
    }
}