package com.travelexperts.team5.travelexperts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import model.Agent;

public class ContactFragment extends Fragment {

    private static ArrayList<Agent> agents;
    private ArrayAdapter<Agent> agentAdapter;
    private ListView agentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contact_fragment, container, false);
        Log.d("TEST", agents.get(0).getAgtFirstName());
        agentList = view.findViewById(R.id.lvAgents);
        agentAdapter = new ArrayAdapter<Agent>(getActivity(),  android.R.layout.simple_list_item_1, getAgents());
        agentList.setAdapter(agentAdapter);
        agentList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), AgentDetails.class);
                intent.putExtra("Name", agents.get(i).getAgtFirstName()+" "+agents.get(i).getAgtMiddleInitial()+" "+agents.get(i).getAgtLastName());
                intent.putExtra("Position", agents.get(i).getAgtPosition());
                intent.putExtra("BPhone", agents.get(i).getAgtBusPhone());
                intent.putExtra("Email", agents.get(i).getAgtEmail());
                startActivity(intent);
            }
        });
        return view;
    }

    public static ArrayList<Agent> getAgents() {
        return agents;
    }

    public static void setAgents(ArrayList<Agent> agents) {
        ContactFragment.agents = agents;
    }
}
