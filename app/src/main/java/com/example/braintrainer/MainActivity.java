package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.example.braintrainer.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ActivityMainBinding binding;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAns;
    int score = 0;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGoMainActivity.setOnClickListener(v -> {
            binding.btnGoMainActivity.setVisibility(View.INVISIBLE);
            binding.gameLayout.setVisibility(View.VISIBLE);
            playAgain();
        });

        binding.btnPlayAgain.setOnClickListener(v -> {
            playAgain();
        });



    }

    public void onButtonClick(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))) {
            binding.tvCorrectMainActivity.setText(R.string.correct);
            score++;
        } else {
            binding.tvCorrectMainActivity.setText(R.string.wrong);
        }
        numberOfQuestions++;
        binding.btnScore.setText(String.valueOf(score).concat("/").concat(String.valueOf(numberOfQuestions)));
        newQuestion();
    }

    public void newQuestion() {
        answers.clear();
        Random random = new Random();
        int number1 = random.nextInt(21);
        int number2 = random.nextInt(21);
        binding.tvQuestionMainActivity.setText(String.valueOf(number1).concat(" + ").concat(String.valueOf(number2)));

        locationOfCorrectAns = random.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAns) {
                answers.add(number1 + number2);
            } else {
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == number1 + number2) {
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        binding.button00.setText(String.valueOf(answers.get(0)));
        binding.button01.setText(String.valueOf(answers.get(1)));
        binding.button10.setText(String.valueOf(answers.get(2)));
        binding.button11.setText(String.valueOf(answers.get(3)));

    }

    void playAgain() {
        binding.btnPlayAgain.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;
        binding.btnScore.setText(String.valueOf(score).concat("/").concat(String.valueOf(numberOfQuestions)));
        binding.btnTimer.setText(R.string._30s);
        binding.button00.setEnabled(true);
        binding.button01.setEnabled(true);
        binding.button10.setEnabled(true);
        binding.button11.setEnabled(true);
        binding.tvCorrectMainActivity.setText(" ");
        newQuestion();
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.btnTimer.setText(String.valueOf(millisUntilFinished / 1000).concat("s"));
            }
            @Override
            public void onFinish() {
                binding.tvCorrectMainActivity.setText(R.string.done);
                binding.btnPlayAgain.setVisibility(View.VISIBLE);
                binding.button00.setEnabled(false);
                binding.button01.setEnabled(false);
                binding.button10.setEnabled(false);
                binding.button11.setEnabled(false);
            }
        }.start();

    }
}