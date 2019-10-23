package com.example.botanicalgardenapp.ui.home;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.botanicalgardenapp.R;

public class HomeFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor light;
    private TextView lightlabel;
    private ImageView place;
    private TextView placelabel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        place = root.findViewById(R.id.imageLight);
        placelabel = root.findViewById(R.id.textPlace);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        lightlabel = root.findViewById(R.id.sensorLuz);

        return root;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (light != null) {
            lightlabel.setText(String.valueOf(sensorEvent.values[0]));
            if (sensorEvent.values[0] > 70) {
                place.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.airelibre));
                placelabel.setText("¡Parece que te encuentras\nal aire libre!");
            } else {
                place.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.invernadero));
                placelabel.setText("¡Parece que te encuentras\nen el interior de un invernadero!");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (light != null) {
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (light != null) {
            sensorManager.unregisterListener(this);
        }
    }

}