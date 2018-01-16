package com.travelexperts.team5.travelexperts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AgentDetails extends AppCompatActivity{
    private TextView name, position, bphone, email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);

        name = (TextView)findViewById(R.id.txtAgentName);
        position = (TextView)findViewById(R.id.txtAgentPosition);
        bphone = (TextView)findViewById(R.id.txtAgentBusPhone);
        email = (TextView)findViewById(R.id.txtAgentEmail);

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            name.setText(extra.getString("Name"));
            position.setText(extra.getString("Position"));
            bphone.setText(extra.getString("BPhone"));
            email.setText(extra.getString("Email"));
        }
    }
}
