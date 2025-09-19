package com.example.listycitylab3;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        AddCityFragment f = new AddCityFragment();
        f.setArguments(args);
        return f;
    }

    static AddCityFragment newInstance() {
        return new AddCityFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_city, null);
        EditText cityEdit = v.findViewById(R.id.edit_text_city_text);
        EditText provEdit = v.findViewById(R.id.edit_text_province_text);

        City passed = null;
        if (getArguments() != null) {
            Object o = getArguments().getSerializable("city");
            if (o instanceof City) passed = (City) o;
        }
        boolean isEdit = (passed != null);
        if (isEdit) {
            cityEdit.setText(passed.getName());
            provEdit.setText(passed.getProvince());
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(isEdit ? "Edit city" : "Add a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(isEdit ? "Save" : "Add", (d, w) -> {
                    String n = cityEdit.getText().toString();
                    String p = provEdit.getText().toString();
                    MainActivity act = (MainActivity) getActivity();
                    if (act == null) return;
                    if (isEdit) {
                        passed.setName(n);
                        passed.setProvince(p);
                        act.refresh();
                    } else {
                        act.addCity(new City(n, p));
                    }
                })
                .create();
    }
}
