package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.chen0914.databinding.ActivityMainBinding;
import data.MainViewModel;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;
    private MainViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        // variableBinding.textview.setText.model.editString;
        variableBinding.mybutton.setOnClickListener(click ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
            model.editString.observe(this, s -> {
                variableBinding.textview.setText("Your edit text has: " + s);
            });
        });

        model.isSelected.observe(this, selected ->{
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
            Toast.makeText(getApplicationContext(), "The value is now",Toast.LENGTH_SHORT).show();
        });

        variableBinding.checkBox.setOnCheckedChangeListener((btn,isChecked)->{
            variableBinding.checkBox.setChecked(isChecked);
            variableBinding.radioButton.setChecked(isChecked);
            variableBinding.switch1.setChecked(isChecked);
            Toast.makeText(getApplicationContext(), "The value is now " + isChecked,Toast.LENGTH_SHORT).show();
        });

        variableBinding.radioButton.setOnCheckedChangeListener((btn,isChecked)->{
            variableBinding.checkBox.setChecked(isChecked);
            variableBinding.radioButton.setChecked(isChecked);
            variableBinding.switch1.setChecked(isChecked);
        });

        variableBinding.switch1.setOnCheckedChangeListener((btn,isChecked)->{
            variableBinding.checkBox.setChecked(isChecked);
            variableBinding.radioButton.setChecked(isChecked);
            variableBinding.switch1.setChecked(isChecked);
        });

        variableBinding.myimagebutton.setOnClickListener(click ->{
            Toast.makeText(getApplicationContext(), "The width= " + variableBinding.myimagebutton.getWidth() + "and height = " + variableBinding.myimagebutton.getHeight(),Toast.LENGTH_SHORT).show();
        });











    }

    }

