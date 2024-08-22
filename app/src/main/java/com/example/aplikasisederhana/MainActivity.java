package com.example.aplikasisederhana;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtWidth;
    private EditText edtHeight;
    private EditText edtLength;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        btnCalculate.setOnClickListener(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState != null){
            String result =savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }
    @Override
    public void onClick(View v){
        if (v.getId()==R.id.btn_calculate){
            String inputLength=edtLength.getText().toString().trim();
            String inputWidth=edtWidth.getText().toString().trim();
            String inputHeight=edtHeight.getText().toString().trim();

            boolean isEmptyFields=false;
            if (TextUtils.isEmpty(inputLength)){
                isEmptyFields=true;
                edtLength.setError("Nilai_Panjang_Tidak_Boleh_Kosong");
            }
            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields=true;
                edtWidth.setError("Nilai_Lebar_Tidak_Boleh_Kosong");
            }
            if (TextUtils.isEmpty(inputHeight)){
                isEmptyFields=true;
                edtHeight.setError("Nilai_Tinggi_Tidak_Boleh_Kosong");
            }
            if (!isEmptyFields){
                Double volume=Double.parseDouble(inputLength)*Double.parseDouble(inputWidth)*Double.parseDouble(inputHeight);
                tvResult.setText(String.valueOf(volume));
            }
        }
    }


//    @Override
//    public void onClick(View view) {
//
//    }
    private static final String STATE_RESULT="state_result";
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT,tvResult.getText().toString());
    }
}