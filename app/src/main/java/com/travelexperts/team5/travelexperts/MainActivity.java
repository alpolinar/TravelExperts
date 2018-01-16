package com.travelexperts.team5.travelexperts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.util.ArrayList;

import model.UserCustomer;
import util.JSONParserUtil;

public class MainActivity extends AppCompatActivity {

    private final String SERVER_ADDRESS = "http://35.182.145.56";
    private final String USER_TABLE = "Customer";

    private String jData = null;
    private Button login;
    private TextView username, password;
    private TextView invalid;

    private final JSONParserUtil jParser = new JSONParserUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        invalid = (TextView)findViewById(R.id.lblInvalid);
        invalid.setVisibility(View.INVISIBLE);
        username = (TextView) findViewById(R.id.txtUsername);
        password = (TextView) findViewById(R.id.txtPassword);
        login = (Button) findViewById(R.id.btnLogin);

        if(checkSession()){
            SharedPreferences pref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            int id = pref.getInt("CustomerId", 1);
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            intent.putExtra("CustomerId", id);
            startActivity(intent);
            MainActivity.this.finish();
        }else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!username.getText().toString().equals("") && !password.getText().toString().equals("")){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                jData = jParser.getJSONData(SERVER_ADDRESS + "/login/customers/" + username.getText() + "/" + md5(password.getText().toString()), USER_TABLE);
                                ArrayList<UserCustomer> user = jParser.getUserCustomer(jData, USER_TABLE);
                                if (!user.isEmpty()) {
                                    Log.i("EMPTY: ", "FALSE");
                                    loginSession(user.get(0).getCustomerId(), user.get(0).getUsername(), user.get(0).getPassword());
                                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                                    intent.putExtra("CustomerId", user.get(0).getCustomerId());
                                    startActivity(intent);
                                    MainActivity.this.finish();
                                } else {
                                    findViewById(R.id.lblInvalid).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            invalid.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    Log.i("EMPTY: ", "TRUE");
                                }
                            }
                        }).start();
                        Log.d("TEST", md5(password.getText().toString()));

                    }else{
                        invalid.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
    }

    private void loginSession(int customerId, String username, String password){
        SharedPreferences pref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("CustomerId", customerId);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private boolean checkSession(){
        SharedPreferences pref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int id = pref.getInt("CustomerId", 1);
        String user = pref.getString("username","");
        String pass = pref.getString("password","");
        if(!user.equals("") && !pass.equals("")){
            return true;
        }else {
            return false;
        }
    }

    public static final String md5(final String toEncrypt) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(toEncrypt.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception exc) {
            return "";
        }
    }
}
