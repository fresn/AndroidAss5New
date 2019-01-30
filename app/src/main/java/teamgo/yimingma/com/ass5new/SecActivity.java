package teamgo.yimingma.com.ass5new;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class SecActivity extends AppCompatActivity {
    DatePicker datePicker;
    private TextView tvBirthday;
    private Button btnSave, btnLoad;
    private EditText etName;
    private static final String TAG = "SecActivity";
    private int bdYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        datePicker = findViewById(R.id.datePicker);
        tvBirthday = ((TextView) findViewById(R.id.tvSecBirthday));
        btnLoad = ((Button) findViewById(R.id.btnSecLoad));
        btnSave = ((Button) findViewById(R.id.btnSecSave));
        etName = ((EditText) findViewById(R.id.etSecName));
        bdYear = 0;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "btnSave onClick: ");
                if (bdYear != 0 && etName.getText().length() > 0 && Calendar.getInstance().get(Calendar.YEAR) > bdYear) {
                    sendResult();
                    putData(etName.getText().toString(), bdYear);
                }else {
                    Toast.makeText(getApplicationContext(),"something wrong",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Set<String> names = getNames();
                Log.d(TAG, "onClick: "+ (names==null));
                if (names==null) {
                    Toast.makeText(getApplicationContext(),"Nothing",Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(SecActivity.this);
                    builderSingle.setIcon(R.drawable.icon_man_back);
                    builderSingle.setTitle("Select One Name:-");

                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SecActivity.this, android.R.layout.select_dialog_singlechoice);
                    arrayAdapter.addAll(names);

                    builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String strName = arrayAdapter.getItem(which);
                            etName.setText(strName);
                            bdYear=getAge(strName);
                            tvBirthday.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-bdYear));

                            Toast.makeText(getApplicationContext(),"loaded",Toast.LENGTH_LONG).show();
                        }
                    });
                    builderSingle.show();
                }
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                bdYear = year;
                tvBirthday.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - bdYear));
            }
        });
    }

    public void sendResult() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("name", etName.getText().toString());
        returnIntent.putExtra("year", bdYear);
        Log.d(TAG, "onPause: " + String.valueOf(bdYear) + etName.getText().toString());
        setResult(1, returnIntent);
    }
    public void badResult(){
        setResult(0);
    }

    public void putData(String name, int year) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> names = preferences.getStringSet("name", null);
        if (names == null) {
            names = new HashSet<String>();
        }
        names.add(name);
        editor.putStringSet("name", names);
        editor.putInt(name, year);
        editor.apply();

        Toast.makeText(getApplicationContext(),name+" "+String.valueOf(year)+" saved",Toast.LENGTH_LONG).show();
    }

    public Set<String> getNames() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> strings = preferences.getStringSet("name", null);
        return strings;

    }

    public int getAge(String name) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt(name, 0);

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        if (bdYear != 0 && etName.getText().length() > 0 && Calendar.getInstance().get(Calendar.YEAR) > bdYear) {
            sendResult();
        }else{

        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onPostCreate: ");
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
