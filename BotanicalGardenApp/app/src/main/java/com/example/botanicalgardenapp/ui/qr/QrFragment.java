package com.example.botanicalgardenapp.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.botanicalgardenapp.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrFragment extends Fragment {

    private Button scan;
    private TextView textViewResult;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_qrcam, container, false);
        scan = (Button) root.findViewById(R.id.buttonQr);
        textViewResult = root.findViewById(R.id.textView);

        scan.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity()){
                    @Override
                    protected void startActivityForResult(Intent intent, int code) {
                        QrFragment.this.startActivityForResult(intent, code); // REQUEST_CODE override
                    }
                };
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            String scanContents = data.getStringExtra("SCAN_RESULT");
            String format = data.getStringExtra("SCAN_RESULT_EXTRA");
            textViewResult.setText(scanContents + "");
        }
    }

}