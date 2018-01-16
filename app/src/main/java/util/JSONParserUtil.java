package util;

import android.util.Log;

import DataHandler.JSONArray;
import DataHandler.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import model.Agent;
import model.Booking;
import model.Customer;
import model.UserCustomer;

public class JSONParserUtil {

    private final FormatUtil format = new FormatUtil();

    public String getJSONData(String target, String table){
        String data = null;
        try{
            URL url = new URL(target);
            URLConnection connect = url.openConnection();
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                data = "{"+table+":"+reader.readLine()+"}";
                Log.i("JSONParser", data);
            }finally {}
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return data;
    }

    public void executeQuery(String target){
        String results;
        try{
            URL url = new URL(target);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                results = reader.readLine();
                System.out.println(results);
            }catch(java.net.ConnectException ex){
                System.out.println("Connection failed: " + ex);
            }
        }catch(IOException ex){
            System.out.println("ERROR: " + ex);
        }
    }

    public ArrayList<Customer> getCustomerDetails(String data, String table){
        JSONObject obj = new JSONObject(data);
        ArrayList<Customer> customer = new ArrayList<Customer>();
        JSONArray array = obj.getJSONArray(table);
        for(int i=0;i<array.length();i++){
            customer.add(new Customer(array.getJSONObject(i).optInt("CustomerId"),array.getJSONObject(i).optString("CustFirstName"),array.getJSONObject(i).optString("CustLastName"),
                                      array.getJSONObject(i).optString("CustAddress"),array.getJSONObject(i).optString("CustCity"),array.getJSONObject(i).optString("CustProv"),
                                      array.getJSONObject(i).optString("CustPostal"),array.getJSONObject(i).optString("CustCountry"),array.getJSONObject(i).optString("CustHomePhone"),
                                      array.getJSONObject(i).optString("CustBusPhone"),array.getJSONObject(i).optString("CustEmail")));
        }
        return  customer;
    }

    public ArrayList<UserCustomer> getUserCustomer(String data, String table){
        JSONObject obj = new JSONObject(data);
        ArrayList<UserCustomer> user = new ArrayList<UserCustomer>();
        JSONArray array = obj.getJSONArray(table);
        for(int i=0;i<array.length();i++){
            user.add(new UserCustomer(array.getJSONObject(i).optInt("user_id"),array.getJSONObject(i).optInt("CustomerId"),
                                      array.getJSONObject(i).optString("user_email"),array.getJSONObject(i).optString("user_password")));
        }
        return user;
    }

    public ArrayList<Agent> loadAgentData(String data, String table){
        JSONObject obj = new JSONObject(data);
        ArrayList<Agent> agents = new ArrayList<Agent>();
        JSONArray array = obj.getJSONArray(table);
        //if(array != null)
        for(int i=0;i<array.length();i++){
            agents.add(new Agent(array.getJSONObject(i).optInt("AgentId"),array.getJSONObject(i).optString("AgtFirstName"), array.getJSONObject(i).optString("AgtMiddleInitial"),
                    array.getJSONObject(i).optString("AgtLastName"), array.getJSONObject(i).optString("AgtBusPhone"),array.getJSONObject(i).optString("AgtEmail"),
                    array.getJSONObject(i).optString("AgtPosition"), array.getJSONObject(i).optInt("AgencyId")));
        }
        return agents;
    }

    public ArrayList<Booking> getCustomerBooking(String data, String table){
        JSONObject obj = new JSONObject(data);
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        JSONArray array = obj.getJSONArray(table);
        for(int i=0;i<array.length();i++){
            bookings.add(new Booking(array.getJSONObject(i).optString("PkgName"),format.formatDate(array.getJSONObject(i).optString("BookingDate")),array.getJSONObject(i).optString("BookingNo")));
        }
        return bookings;
    }
}
