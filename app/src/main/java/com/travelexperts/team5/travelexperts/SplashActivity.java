package com.travelexperts.team5.travelexperts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Agent;
import model.Booking;
import model.Customer;
import util.JSONParserUtil;

public class SplashActivity extends AppCompatActivity {
    int customerId = 0;
    private String jData = null;
    private final String SERVER_ADDRESS = "http://35.182.145.56";
    private final String USER_TABLE = "Customer";
    private final JSONParserUtil jParser = new JSONParserUtil();
    private static ArrayList<Customer> customer;
    private static ArrayList<Agent> agentData;
    private static ArrayList<Booking> bookings;
    CustomerProfileActivity profile = new CustomerProfileActivity();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            customerId = extra.getInt("CustomerId");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    jData = jParser.getJSONData(SERVER_ADDRESS+"/customer/load/details/"+customerId, USER_TABLE);
                    customer = jParser.getCustomerDetails(jData, USER_TABLE);
                    Log.i("jData", jData);
                    Intent intent = new Intent(SplashActivity.this, CustomerProfileActivity.class);
                    intent.putExtra("CustomerId",customerId);
                    intent.putExtra("Name", customer.get(0).getFirstName() + " " + customer.get(0).getLastName());
                    intent.putExtra("Address", customer.get(0).getAddress()+","+customer.get(0).getCity()+","+customer.get(0).getProvince()+","+
                            customer.get(0).getPostal()+","+customer.get(0).getCountry());
                    intent.putExtra("HomePhone", customer.get(0).gethPhone());
                    intent.putExtra("BusPhone", customer.get(0).getbPhone());
                    intent.putExtra("Email", customer.get(0).getEmail());
                    Log.i("customer id ", customerId + "");

                    jData = jParser.getJSONData(SERVER_ADDRESS+"/users/agents/details/name","Agents");
                    agentData = jParser.loadAgentData(jData,"Agents");
                    profile.setAgents(agentData);

                    jData = jParser.getJSONData(SERVER_ADDRESS+"/customer/load/bookings/"+customerId, "Bookings");
                    bookings = jParser.getCustomerBooking(jData, "Bookings");
                    profile.setBookings(bookings);

                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
