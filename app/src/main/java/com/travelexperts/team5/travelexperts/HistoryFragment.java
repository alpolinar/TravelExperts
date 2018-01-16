package com.travelexperts.team5.travelexperts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import model.Booking;
import model.BookingListAdapter;

public class HistoryFragment extends Fragment {

    private static ArrayList<Booking> bookings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_history_fragment, container, false);

        ListView bookingList = (ListView)view.findViewById(R.id.lvBookings);
        BookingListAdapter adapter = new BookingListAdapter(getActivity(), R.layout.adapter_view_layout, bookings);
        bookingList.setAdapter(adapter);
        Log.d("TEST", bookings.get(0).getPackageName());
        return view;
    }

    public static ArrayList<Booking> getBookings() {
        return bookings;
    }

    public static void setBookings(ArrayList<Booking> bookings) {
        HistoryFragment.bookings = bookings;
    }
}
