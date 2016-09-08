package com.lc.belajar.layout.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lc.belajar.layout.main.MainActivity;

import java.util.List;

import belajar.lc.com.cektagihanlistrik.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eby on 07/09/16.
 */
public class BulanAdapter extends ArrayAdapter<String> {
    Context context;
    List<String> bulanList;

    @BindView(R.id.lbs_txt_cat)
    TextView txtBulan;

    public BulanAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

        this.context = context;
        this.bulanList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_bulan_style, parent, false);
        ButterKnife.bind(this, view);

        String bulan = bulanList.get(position);
        txtBulan.setText(bulan);

        return view;
    }
}
