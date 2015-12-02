package myapplication.jipx.example.com.ksoapdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import myapplication.jipx.example.com.ksoapdemo.ConvertTemperatureTask;
import myapplication.jipx.example.com.ksoapdemo.ConstantString;


public class MainActivity extends AppCompatActivity {

    private TextView textConverted;
    private View btnFToC;
    private View btnCToF;
    private EditText input;
    private int convertStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCToF = (View) findViewById(R.id.btn_c_to_f);
        btnFToC = (View) findViewById(R.id.btn_f_to_c);
        textConverted = (TextView) findViewById(R.id.txt_converted);
        input = (EditText) findViewById(R.id.txt_temp);

        // set event listeners
        btnCToF.setOnClickListener(onCToFClick());
        btnFToC.setOnClickListener(onFtoCClick());
    }

    private OnClickListener onCToFClick() {
        return new OnClickListener() {

            @Override
            public void onClick(View v) {
                invokeAsyncTask("Celsius", ConstantString.C_TO_F_SOAP_ACTION,
                        ConstantString.C_TO_F_METHOD_NAME);
                convertStyle = 0;
            }
        };
    }

    //change Fahrenheit to Celsius degree
    private OnClickListener onFtoCClick() {
        return new OnClickListener() {

            @Override
            public void onClick(View v) {
                invokeAsyncTask("Fahrenheit", ConstantString.F_TO_C_SOAP_ACTION,
                        ConstantString.F_TO_C_METHOD_NAME);
                convertStyle = 1;
            }
        };
    }

    //create and execute asynctask to get response from W3school server
    private void invokeAsyncTask(String convertParams, String soapAction, String methodName) {
        new ConvertTemperatureTask(this, soapAction, methodName, convertParams).execute(input.getText()
                .toString().trim());
    }

    //call back data from background thread and set to views
    public void callBackDataFromAsyncTask(String result) {
        Double resultTemperature = Double.parseDouble(result); //parse String to double

        if (convertStyle == 0) {// C degree to F degree
            textConverted.setText(input.getText().toString().trim() + " degree Celsius = "
                    + String.format("%.2f", resultTemperature) + " degree Fahrenhei2t");
        } else {// F degree to C degree
            textConverted.setText(input.getText().toString().trim() + " degree Fahrenheit = "
                    + String.format("%.2f", resultTemperature) + " degree Celsius");
        }

    }
}
