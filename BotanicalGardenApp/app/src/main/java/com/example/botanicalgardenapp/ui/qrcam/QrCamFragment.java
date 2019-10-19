package com.example.botanicalgardenapp.ui.qrcam;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.botanicalgardenapp.R;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class QrCamFragment extends Fragment {

    private Button scan;
    private TextView textViewResult;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_qrcam, container, false);
        scan = (Button) root.findViewById(R.id.buttonQr);
        textViewResult = getActivity().findViewById(R.id.textView);
        scan.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.setPackage("com.google.zxing.client.android");
                intent.putExtra("SCAN_MODE","QR_CODE_MODE");
                getActivity().startActivityForResult(intent,0);

            }
        });


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 0){
            if (resultCode == RESULT_OK){
                String scanContents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_EXTRA");
                textViewResult.setText(scanContents);
            } else if (resultCode == RESULT_CANCELED){

            }
        }
    }

}