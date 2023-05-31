package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.chen0914.R;
import algonquin.cst2335.chen0914.databinding.ActivityMainBinding;
import data.MainViewModel;


public class MainActivity extends AppCompatActivity {

    //private ActivityMainBinding variableBinding;
    //private MainViewModel model;

    ImageView imageView;
    Switch sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.flagview);
        sw = findViewById(R.id.switch1);

        sw.setOnCheckedChangeListener( (btn,isChecked)-> {
            if (isChecked){
                RotateAnimation rotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotate.setDuration(5000);
                rotate.setRepeatCount(Animation.INFINITE);
                rotate.setInterpolator(new LinearInterpolator());

                imageView.startAnimation(rotate);
            }
                else {
                    imageView.clearAnimation();
            }
        });






        //model = new ViewModelProvider(this).get(MainViewModel.class);

        //variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(variableBinding.getRoot());













    }

    }

