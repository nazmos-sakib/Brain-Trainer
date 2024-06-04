package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.braintrainer.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
String TAG = "MainActivity";
    ActivityMainBinding binding;
    ArrayList<Integer> answers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGoMainActivity.setOnClickListener(v -> {
            binding.btnGoMainActivity.setVisibility(View.INVISIBLE);
        });


        Random random = new Random();
        int number1 = random.nextInt(21);
        int number2 = random.nextInt(21);
        binding.tvQuestionMainActivity.setText(String.valueOf(number1).concat(" + ").concat(String.valueOf(number2)));

        for (int i = 0; i < 4; i++) {
            int answer = number1 + number2;
            answers.add(answer);
        }

    }

    public void onButtonClick(View view) {
    }
}