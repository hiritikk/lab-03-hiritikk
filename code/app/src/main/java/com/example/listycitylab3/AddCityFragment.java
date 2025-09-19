package com.example.listycitylab3;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    // use fields so lambdas don't complain
    private City editingCity;
    private boolean isEdit;

    // for editing
    static AddCityFragment newInstance(City city) {
        Bundle b = new Bundle();
        b.putSerializable("city", city);
        AddCityFragment f = new AddCityFragment();
        f.setArguments(b);
        return f;
    }

    // for adding
    static AddCityFragment newInstance() {
        return new AddCityFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_city, null);
        EditText cityEdit = v.findViewById(R.id.edit_text_city_text);
        EditText provEdit = v.findViewById(R.id.edit_text_province_text);

        // pull arg into fields (not locals)
        if (getArguments() != null) {
            Object obj = getArguments().getSerializable("city");
            if (obj instanceof City) editingCity = (City) obj;
        }
        isEdit = (editingCity != null);

        if (isEdit) {
            cityEdit.setText(editingCity.getName());
            provEdit.setText(editingCity.getProvince());
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
                        editingCity.setName(n);
                        editingCity.setProvince(p);
                        act.refresh();
                    } else {
                        act.addCity(new City(n, p));
                    }
                })
                .create();
    }
}
