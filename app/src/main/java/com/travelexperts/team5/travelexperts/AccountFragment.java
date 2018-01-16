/*
    author: Al Polinar
*/

package com.travelexperts.team5.travelexperts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import util.JSONParserUtil;


public class AccountFragment extends Fragment {
    private final String SERVER_ADDRESS = "http://35.182.145.56";

    private EditText name;
    private EditText address;
    private EditText hPhone;
    private EditText email;
    private Button update;
    private int customerId;

    private final JSONParserUtil jParser = new JSONParserUtil();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_account_fragment, container, false);
        name = view.findViewById(R.id.txtName);
        address = view.findViewById(R.id.txtAddress);
        hPhone = view.findViewById(R.id.txtHPHone);
        email = view.findViewById(R.id.txtEmail);
        update = view.findViewById(R.id.btnUpdate);
        disableFields();
        if(this.getArguments() != null){
            customerId = this.getArguments().getInt("CustomerId");
            name.setText(this.getArguments().getString("Name"));
            address.setText(this.getArguments().getString("Address"));
            if(!this.getArguments().getString("HomePhone").equals("")){
                hPhone.setText(this.getArguments().getString("HomePhone"));
            }else{
                hPhone.setText("No home phone number was added");
            }
            email.setText(this.getArguments().getString("Email"));
        }else {
            Log.d("AccountFragment", "EMPTY");
        }

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(update.getText().toString().equals("Update Info")){
                    enableFields();
                    update.setText("Save");
                }else if(update.getText().toString().equals("Save")){
                    if(!name.getText().equals("") && !address.getText().equals("") && !hPhone.getText().equals("") && !email.getText().equals("")){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String[] nameResult = formatName(name.getText().toString());
                                String[] addressResult  = formatAddress(address.getText().toString());
                                Log.d("results ", SERVER_ADDRESS+"/customer/update/details/"+customerId+"/"+formatString(nameResult[0])+"/"+formatString(nameResult[1])+"/"+formatString(addressResult[0])+"/"+
                                        formatString(addressResult[1])+"/"+formatString(addressResult[2])+"/"+formatString(addressResult[3])+"/"+formatString(addressResult[4])+"/"+formatString(hPhone.getText().toString())+"/"+
                                        formatString("none")+"/"+formatString(email.getText().toString())+"/1");
                                jParser.executeQuery(SERVER_ADDRESS+"/customer/update/details/"+customerId+"/"+formatString(nameResult[0])+"/"+formatString(nameResult[1])+"/"+formatString(addressResult[0])+"/"+
                                        formatString(addressResult[1])+"/"+formatString(addressResult[2])+"/"+formatString(addressResult[3])+"/"+formatString(addressResult[4])+"/"+formatString(hPhone.getText().toString())+"/"+
                                        formatString("none")+"/"+formatString(email.getText().toString())+"/1");
                            }
                        }).start();
                    }
                    update.setText("Update Info");
                    Toast.makeText(getActivity(),"Update Successful", Toast.LENGTH_LONG).show();
                    disableFields();
                }
            }
        });

        return view;
    }

    private String formatString(String str){
        return str = str.replace(" ","%20");
    }

    private String[] formatName(String name){
        String[] str = name.split(" ");
        return str;
    }

    private String[] formatAddress(String address){
        String[] str = address.split(",");
        return str;
    }

    private void enableFields(){
        name.setEnabled(true);
        address.setEnabled(true);
        hPhone.setEnabled(true);
        email.setEnabled(true);
    }

    private void disableFields(){
        name.setEnabled(false);
        address.setEnabled(false);
        hPhone.setEnabled(false);
        email.setEnabled(false);
    }
}
