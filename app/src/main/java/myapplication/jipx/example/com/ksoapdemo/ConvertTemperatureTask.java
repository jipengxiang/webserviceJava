package myapplication.jipx.example.com.ksoapdemo;

import android.os.AsyncTask;
import android.util.Log;
import org.ksoap2.serialization.SoapObject;

import android.os.AsyncTask;
import android.util.Log;

import myapplication.jipx.example.com.ksoapdemo.MainActivity;
import myapplication.jipx.example.com.ksoapdemo.ConstantString;
import myapplication.jipx.example.com.ksoapdemo.WebServiceCall;
/**
 * Created by s29917 on 6/10/2015.
 */
public class ConvertTemperatureTask extends AsyncTask<String, Void, String> {

    private MainActivity activity;
    private String soapAction;
    private String methodName;
    private String paramsName;

    private final static String TAG = ConvertTemperatureTask.class.getSimpleName();

    public ConvertTemperatureTask(MainActivity activity, String soapAction, String methodName,
                                  String paramsName) {
        this.activity = activity;
        this.methodName = methodName;
        this.soapAction = soapAction;
        this.paramsName = paramsName;
    }

    @Override
    protected String doInBackground(String... params) {
        //create a new soap request object
        SoapObject request = new SoapObject(ConstantString.NAME_SPACE, methodName);
        //add properties for soap object
        request.addProperty(paramsName, params[0]);

        //request to server and get Soap Primitive response
        return WebServiceCall.callWSThreadSoapPrimitive(ConstantString.URL, soapAction, request);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result == null) {
            Log.i(TAG, "cannot get result");
        } else {
            //invoke call back method of Activity
            activity.callBackDataFromAsyncTask(result);
        }
    }

}
