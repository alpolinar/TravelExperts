package com.travelexperts.team5.travelexperts;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.Agent;
import model.Booking;


public class CustomerProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private TextView name;
    private int customerId;

    private SectionStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;

    private static ArrayList<Agent> agents;
    private static ArrayList<Booking> bookings;

    private static ContactFragment contact = new ContactFragment();
    private static HistoryFragment history = new HistoryFragment();

    private static Bundle accountBundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        NavigationView navView = (NavigationView)findViewById(R.id.nav_view) ;
        navView.setNavigationItemSelectedListener(this);
        View header = navView.inflateHeaderView(R.layout.nav_header);
        Bundle extra = getIntent().getExtras();

        name = (TextView)header.findViewById(R.id.lblName);
        if(extra != null){
            customerId = extra.getInt("CustomerId");
            accountBundle.putInt("CustomerId", customerId);
            accountBundle.putString("Name", extra.getString("Name"));
            accountBundle.putString("Address", extra.getString("Address"));
            accountBundle.putString("HomePhone", extra.getString("HomePhone"));
            accountBundle.putString("BusPhone", extra.getString("BusPhone"));
            accountBundle.putString("Email", extra.getString("Email"));
        }

        if(!agents.isEmpty()){
            contact.setAgents(agents);
        }

        if(!bookings.isEmpty()){
            history.setBookings(bookings);
        }

        mSectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);

        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private static AccountFragment loadAccountFragement(){
        AccountFragment account = new AccountFragment();
        account.setArguments(accountBundle);
        return account;
    }

    private void setupViewPager(ViewPager pager){
        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(loadAccountFragement(), "AccountFragment");
        adapter.addFragment(new HistoryFragment(), "HistoryFragment");
        adapter.addFragment(new ContactFragment(), "ContactFragment");
        pager.setAdapter(adapter);
    }

    public void setViewPager(int frag){
        mViewPager.setCurrentItem(frag);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_account:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.nav_history:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.nav_contact:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                logoutSession();
                Intent intent = new Intent(CustomerProfileActivity.this, MainActivity.class);
                startActivity(intent);
                CustomerProfileActivity.this.finish();
                break;
            default:
                break;
        }
        mDrawer.closeDrawer(Gravity.LEFT);
        return true;
    }

    public static ArrayList<Agent> getAgents() {
        return agents;
    }

    public static void setAgents(ArrayList<Agent> agents) {
        CustomerProfileActivity.agents = agents;
    }

    public static ArrayList<Booking> getBookings() {
        return bookings;
    }

    public static void setBookings(ArrayList<Booking> bookings) {
        CustomerProfileActivity.bookings = bookings;
    }

    private void logoutSession(){
        SharedPreferences pref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("CustomerId", 0);
        editor.putString("username", "");
        editor.putString("password", "");
        editor.apply();
    }
}
