package com.lc.belajar.layout.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lc.belajar.layout.main.MainActivity;
import com.lc.belajar.layout.main.MainMvpView;
import com.lc.belajar.orm.entity.Customer;

import java.util.List;

import belajar.lc.com.cektagihanlistrik.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eby on 07/09/16.
 */
public class IdpelAdapter extends ArrayAdapter<Customer> {
    Context context;
    List<Customer> customerList;
    MainMvpView.DataManipulation mData;
    MainMvpView.ButtonListener mListener;

    @BindView(R.id.lis_txt_idpel)
    TextView txtIdpel;
    @BindView(R.id.lis_txt_nama)
    TextView txtNama;
    @BindView(R.id.lis_delete)
    ImageView imgDelete;

    public IdpelAdapter(Context context, int resource, List<Customer> objects) {
        super(context, resource, objects);

        this.context = context;
        this.customerList = objects;
    }

    public void attachData(MainMvpView.DataManipulation mData, MainMvpView.ButtonListener mListener) {
        this.mData = mData;
        this.mListener = mListener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_idpel_style, parent, false);
        ButterKnife.bind(this, view);

        final Customer cs = customerList.get(position);
        txtIdpel.setText(cs.getIdpel());
        txtNama.setText(cs.getNama());

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.buttonClickListener(position, cs);
            }
        });

        return view;
    }
}
