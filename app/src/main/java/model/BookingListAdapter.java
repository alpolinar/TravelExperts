package model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.travelexperts.team5.travelexperts.R;

import java.util.ArrayList;
import java.util.List;

public class BookingListAdapter  extends ArrayAdapter<Booking>{

    private Context mContext;
    private int mResource;

    public BookingListAdapter(Context context, int resource,ArrayList<Booking> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String packageName = getItem(position).getPackageName();
        String bookingDate = getItem(position).getBookindDate();
        String bookingNo = getItem(position).getBookingNo();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView  = inflater.inflate(mResource, parent, false);
        TextView tvPackageName = (TextView)convertView.findViewById(R.id.lblPackageName);
        TextView tvBookingDate = (TextView)convertView.findViewById(R.id.lblBookingDate);
        TextView tvBookingNo = (TextView)convertView.findViewById(R.id.lblBookingNo);
        tvPackageName.setText(packageName);
        tvBookingDate.setText(bookingDate);
        tvBookingNo.setText(bookingNo);
        return convertView;
    }
}
