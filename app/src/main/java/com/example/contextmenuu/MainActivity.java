package com.example.contextmenuu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * @author Liem Buchuk
 * @since 16/11/19
 * @version alpha
 *
 * in this activity the user will choose the series type, the difference or the multiplation and will enter the first number of the series
 */

public class MainActivity extends AppCompatActivity {

    /**
     * first- first number of the series
     * num - difference/multiplier
     * sw- arithmetic or geometric
     */

    String st, str;
    double first, num;
    boolean sug;
    Switch sw;
    EditText et1, et2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw=(Switch)findViewById(R.id.sw);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
    }


    /**
     * choosing the type
     */
    public void swclick(View view) {
        if (sw.isChecked()) sug =true;
        else sug= false;
    }


    /**
     * if the user entered a number, the next activity will start.
     * if not, the user will be told to enter a number.
     */
    public void nxtClick(View view) {
        st=et1.getText().toString();
        str=et2.getText().toString();
        if ((st.length()==0) && (str.length()==0)){
            Toast.makeText(this,"enter numbers",Toast.LENGTH_SHORT).show();
        }
        else{
            first=Double.parseDouble(st);
            num=Double.parseDouble(str);
            Intent si = new Intent(this,mysidra.class);
            si.putExtra("hukiut",num);
            si.putExtra("first",first);
            si.putExtra("sug",sug);
            startActivity(si);

        }
    }


    /**
     * starting the credits activity
     */
    public void credClick(View view) {
        Intent gi=new Intent(this,credits.class);
        startActivity(gi);
    }
}
