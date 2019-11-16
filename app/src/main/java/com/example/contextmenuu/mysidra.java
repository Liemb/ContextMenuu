package com.example.contextmenuu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class mysidra extends AppCompatActivity implements View.OnCreateContextMenuListener, AdapterView.OnItemLongClickListener {

    /**
     * d is the array that includes the series numbers
     * stm is an array with the series as string
     * first is the first number
     * num - difference or multiplier
     * place is the place of a nmber in the array
     */
    double[] d=new double[20];
    String[] stm=new String[20];
    double first,num;
    boolean sug;
    ListView lv;
    TextView tv;
    String st="";
    int place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysidra);

        lv = (ListView) findViewById(R.id.lv);
        tv = (TextView) findViewById(R.id.tv);

        Intent mi = getIntent();
        num = mi.getDoubleExtra("hukiut", -2000000000);
        first = mi.getDoubleExtra("first", -2000000000);
        sug = mi.getBooleanExtra("sug", false);

        d[0] = first;
        if (!sug) {
            for (int i = 1; i <= 19; i++) {
                d[i] = d[i - 1] * num;
            }
        } else {
            for (int l = 1; l <= 19; l++) {
                d[l] = d[l - 1] + num;
            }

            for (int m = 0; m <= 19; m++) {
                stm[m] = "" + d[m];
            }

            ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, stm);
            lv.setAdapter(adp);
            lv.setOnCreateContextMenuListener(this);
            lv.setOnItemLongClickListener(this);
        }
    }

    /**
     * creating the context menu
     */
    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.setHeaderTitle("Options");
        menu.add("item place");
        menu.add("sum");

    }


    /**
     * when clicking "item place" it will show the user the place of a number in the series
     * when clicking "sum" this will sm up all the numbers in the series
     */
    @Override
    public boolean onContextItemSelected(MenuItem item){
        String oper=item.getTitle().toString();
        if (oper.equals("item place")) {
            tv.setText("place: "+(place+1));
        }
        if (oper.equals("sum")) {
            double sum=0;
            for(int i=0;i<=place;i++) {
                sum+=d[i];
            }
            tv.setText("sum= "+sum);
        }
        return true;
    }


    /**
     * returning to the first activity
     */
    public void bclick(View view) {
        finish();
    }


    /**
     * starting the credits activity
     */
    public void crdclick(View view) {
        Intent mi=new Intent(this,credits.class);
        startActivity(mi);
    }


    /**
     * getting the place in the series
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        place=position;
        return false;
    }
}
