//package com.example.anmlvoicesapp;
//
//import android.content.Intent;
//import android.content.res.ColorStateList;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class MainActivityTMP extends AppCompatActivity {
//
//    private EditText editTextText1 ;
//    private EditText editTextText2 ;
//    private TextView resultView;
//    private Button button;
//    private Button button_2;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        resultView = findViewById(R.id.textView);
//        editTextText1 = findViewById(R.id.editTextText1);
//        editTextText2 = findViewById(R.id.editTextText2);
//        button = findViewById(R.id.button);
//
//        button.setOnClickListener(v -> {
//            float num1 = Float.parseFloat(editTextText1.getText().toString());
//            float num2 = Float.parseFloat(editTextText2.getText().toString());
//
//            float res = num1 + num2;
//            resultView.setText(String.valueOf(res));
//        });
//
//        button_2 = findViewById(R.id.button_2);
//
//        button_2.setOnClickListener(v -> {
//            showText(resultView.getText().toString());
//            button_2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//            button_2.setText("Правильно !!!");
//
//            showAlert("Correct answer !!! - " + resultView.getText(), button_2);
//        });
//
//    }
//
//    public void btnClick(View v){
//        showText(((Button)v).getText().toString());
//        ((Button)v).setText("Не вірно !!!");
//        v.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
//    }
//
//    private void showText(String text){
//        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//    }
//
//    private void showAlert(String text, Button btn){
//        new AlertDialog.Builder(MainActivityTMP.this).setTitle("Awesome !!!")
//                .setMessage(text)
//                .setPositiveButton("Close", (dialog, which) -> {
//                    finish();
//                })
//                .setNegativeButton("Move on", (dialog, which) -> {
//                    dialog.cancel();
//                    btn.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
//                    btn.setText("Давай ще раз");
//                })
//                .create().show();
//    }
//
//    public void openDogPage(View v){
//        Intent intent = new Intent(this, DogActivity.class);
//
//        startActivity(intent);
//    }
//
//    public void openCatPage(View v){
//        Intent intent = new Intent(this, CatActivity.class);
//
//        startActivity(intent);
//    }
//
//
//}