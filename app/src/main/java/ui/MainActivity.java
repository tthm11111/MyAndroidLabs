package ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import algonquin.cst2335.chen0914.R;


/**
 * @author cty
 * @version  1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     *  This holds the text at the center of the screen
     */
    TextView tv =null;
    /**
     *  This hold the editText
     */
    EditText et = null;
    /**
     * This holds the button
     */
    Button btn =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       tv = findViewById(R.id.textViewLab5);
       et = findViewById(R.id.editTextLab5);
       btn = findViewById(R.id.buttonLab5);



        btn.setOnClickListener( clk -> {
            String password = et.getText().toString();
            boolean isPasswordValid = checkPasswordComplexity(password);

            if (isPasswordValid) {
                tv.setText("Your password meets the requirements");
            } else {
                tv.setText("You shall not pass!");
            }
        });
    }


    /** This function ...
     *
     * @param pw The String object that we are checking
     * @return Returns true if ....
     */
    boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;

        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);

            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else {
                foundSpecial = true;
            }
        }

        if (!foundUpperCase)
        {
            Toast.makeText(this, "missing an upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!foundLowerCase)
        {
            Toast.makeText(this, "missing a lower case letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!foundNumber)
        {
            Toast.makeText(this, "missing number", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!foundSpecial)
        {
            Toast.makeText(this, "missing special", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true; // only get here if they're all true
        }
    }


        boolean isSpecialCHaracter(char c){
        switch (c)
        {
            case '#':
            case '?':
            case '*':
                return true;
            default:
                return false;
        }
    }
}

















