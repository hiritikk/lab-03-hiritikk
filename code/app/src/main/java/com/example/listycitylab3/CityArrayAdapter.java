package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = (convertView != null)
                ? convertView
                : LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);

        City city = getItem(position);
        if (city != null) {
            ((TextView) view.findViewById(R.id.city_text)).setText(city.getName());
            ((TextView) view.findViewById(R.id.province_text)).setText(city.getProvince());
        }
        return view;
    }
}
