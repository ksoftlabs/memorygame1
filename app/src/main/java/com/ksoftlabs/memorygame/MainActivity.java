package com.ksoftlabs.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvLevel, tvNumber;
    EditText etNumber;
    Button btnCheck,btnStart;
    int currentLevel;
    Random r;
    Integer number,input_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLevel=findViewById(R.id.tvLevel);
        tvNumber=findViewById(R.id.tvNumber);
        etNumber=findViewById(R.id.etNumber);
        btnCheck=findViewById(R.id.btnCheck);
        btnStart=findViewById(R.id.btnStart);

        r=new Random();
        currentLevel=1;

        tvNumber.setVisibility(View.GONE);
        etNumber.setVisibility(View.GONE);
        btnCheck.setVisibility(View.GONE);


        tvLevel.setText("Level "+currentLevel);

        number=generateNumber(Math.min(10,currentLevel));

        tvNumber.setText(number.toString());


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayState("Show Number");

                new Handler().postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        setPlayState("Hide Number");


                    }

                },1000);
            }
        });





        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_number=Integer.parseInt(etNumber.getText().toString());
                if(number.equals(input_number)){
                    currentLevel++;
                    tvLevel.setText("Level "+currentLevel);

                    number=generateNumber(Math.min(10,currentLevel));

                    tvNumber.setText(number.toString());


                    setPlayState("Show Number");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setPlayState("Hide Number");
                        }
                    },1000);

                    Context context = getApplicationContext();
                    CharSequence text = "Good Job!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                }else{
                    currentLevel=1;
                    tvLevel.setText("Level "+currentLevel);

                    number=generateNumber(Math.min(10,currentLevel));

                    tvNumber.setText(number.toString());


                    setPlayState("Restart");


                    Context context = getApplicationContext();
                    CharSequence text = "Wrong Answer.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });
    }

    private int generateNumber(int no_of_digits){
        String output="";

        for(int i=0;i<no_of_digits;i++){
            int randomDigit=r.nextInt(10);
            output+=randomDigit;
        }
        return Integer.parseInt(output);
    }

    private void setPlayState(String state){
        if(state.equals("Show Number")){
            tvNumber.setVisibility(View.VISIBLE);
            etNumber.setVisibility(View.GONE);
            btnCheck.setVisibility(View.GONE);
            btnCheck.setEnabled(false);
            btnStart.setVisibility(View.GONE);
            btnStart.setEnabled(false);
        }else if(state.equals("Hide Number")){
            tvNumber.setVisibility(View.GONE);
            etNumber.setVisibility(View.VISIBLE);
            etNumber.requestFocus();
            btnCheck.setVisibility(View.VISIBLE);
            btnCheck.setEnabled(true);
        }else if(state.equals("Restart")){
            etNumber.setVisibility(View.GONE);
            btnCheck.setVisibility(View.GONE);
            btnCheck.setEnabled(false);
            btnStart.setVisibility(View.VISIBLE);
            btnStart.setEnabled(true);
        }
    }
}