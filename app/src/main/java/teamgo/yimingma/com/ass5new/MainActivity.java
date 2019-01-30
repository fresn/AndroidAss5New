package teamgo.yimingma.com.ass5new;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button btnAdd, btnDivide, btnFactorial, btnMultiply, btnNext, btnSubtract;
    private EditText num1, num2;
    private EditText age, name, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnDivide = findViewById(R.id.btnDivide);
        btnFactorial = findViewById(R.id.btnFactorial);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnNext = findViewById(R.id.btnNext);
        btnSubtract = findViewById(R.id.btnSubtract);
        num1 = (EditText) findViewById(R.id.etNum1);
        num2 = (EditText) findViewById(R.id.etNum2);
        name = (EditText) findViewById(R.id.etSecName);
        age = (EditText) findViewById(R.id.etAge);
        result = (EditText) findViewById(R.id.etResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(num1.getText().toString(), num2.getText().toString());
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract(num1.getText().toString(), num2.getText().toString());
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                divide(num1.getText().toString(), num2.getText().toString());
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiply(num1.getText().toString(), num2.getText().toString());
            }
        });
        btnFactorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factorial(num1.getText().toString());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    public void add(String num1, String num2) {
        try {
            result.setText(String.valueOf(Integer.valueOf(num1) + Integer.valueOf(num2)));
        } catch (Exception e) {
            toast(e.getMessage());
        }
        ;

    }

    public void divide(String num1, String num2) {
        try {
            result.setText(String.valueOf(Integer.valueOf(num1) / Integer.valueOf(num2)));
        } catch (Exception e) {
            toast(e.getMessage());
        }
        ;
    }

    public void factorial(String num1) {

        try {
            int ans = Integer.valueOf(num1);

            for (int i = ans - 1; i >= 1; i = i - 1) {
                ans = ans * i;
                Log.d(TAG, "factorial: " + ans);
            }
            result.setText(String.valueOf(ans));
        } catch (Exception e) {
            toast(e.getMessage());
        }
        ;
    }

    public void multiply(String num1, String num2) {
        try {
            result.setText(String.valueOf(Integer.valueOf(num1) * Integer.valueOf(num2)));
        } catch (Exception e) {
            toast(e.getMessage());
        }
        ;
    }

    public void next() {
        Intent intent = new Intent(this, SecActivity.class);
        startActivityForResult(intent, 2);
    }

    public void subtract(String num1, String num2) {
        try {
            result.setText(String.valueOf(Integer.valueOf(num1) - Integer.valueOf(num2)));
        } catch (Exception e) {
            toast(e.getMessage());
        }
        ;
    }


    public void toast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {



        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            int bdYear=data.getIntExtra("year",0);
            String name=data.getStringExtra("name");
            Log.d(TAG, "onActivityResult: ");
            if(bdYear!=0){
                this.age.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) -bdYear));
            }
            if(!name.equals("")){
                this.name.setText(name);
            }
        }else{
            Toast.makeText(getApplicationContext(),"bad result",Toast.LENGTH_LONG).show();
        }


//        name.setText(data.getStringExtra("name"));
//        Calendar today = Calendar.getInstance();
//        Calendar birthday = Calendar.getInstance();
//        birthday.setTimeInMillis(data.getLongExtra("age", 0));
//        age.setText(String.valueOf(today.get(Calendar.YEAR)-birthday.get(Calendar.YEAR)));

    }
}
