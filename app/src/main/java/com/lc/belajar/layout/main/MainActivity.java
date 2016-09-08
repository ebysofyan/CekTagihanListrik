package com.lc.belajar.layout.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lc.belajar.helper.AlertHelper;
import com.lc.belajar.helper.DateUtils;
import com.lc.belajar.helper.NumberFormatter;
import com.lc.belajar.helper.ProgressDialogHelper;
import com.lc.belajar.layout.main.adapter.BulanAdapter;
import com.lc.belajar.layout.main.adapter.IdpelAdapter;
import com.lc.belajar.orm.entity.Customer;
import com.lc.belajar.retrofit.entity.ResponseSerializer;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import belajar.lc.com.cektagihanlistrik.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainMvpView.LoadData, MainMvpView.ButtonListener {

    @BindView(R.id.ci_txt_idpel)
    EditText txtIdpel;
    @BindView(R.id.ci_txt_tahun)
    EditText txtTahun;
    @BindView(R.id.ci_txt_bulan)
    TextView txtBulan;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<String> listBulan;
    ListView listView;
    ProgressDialog mProgressDialog;
    MainPresenter mPresenter;
    IdpelAdapter idpelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        initPresenter();

        setupToolbar();
        txtTahun.setText(DateUtils.getYear(new Date()));
    }

    void initPresenter() {
        mPresenter = new MainPresenter(MainActivity.this);
        mPresenter.attchView(this);
        mPresenter.activateDatabase();
    }

    void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    void setupBulan() {
        listBulan = new ArrayList<String>();

        listBulan.add("01 (Januari)");
        listBulan.add("02 (Februari)");
        listBulan.add("03 (Maret)");
        listBulan.add("04 (April)");
        listBulan.add("05 (Mei)");
        listBulan.add("06 (Juni)");
        listBulan.add("07 (Juli)");
        listBulan.add("08 (Agustus)");
        listBulan.add("09 (September)");
        listBulan.add("10 (Oktober)");
        listBulan.add("11 (November)");
        listBulan.add("12 (Desember)");
    }

    @OnClick(R.id.ci_txt_bulan)
    void bulanDialog() {
        setupBulan();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.list_bulan_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        initListView(dialogView, alertDialog);

        alertDialog.show();
    }

    private void initListView(View view, final AlertDialog alertDialog) {

        listView = (ListView) view.findViewById(R.id.cld_list_bulan);

        BulanAdapter adapter = new BulanAdapter(view.getContext(), R.layout.list_bulan_dialog, listBulan);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bulan = listBulan.get(position);

                txtBulan.setText(bulan);
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(MainActivity.this);
        }
        ProgressDialogHelper.showDialog(mProgressDialog, "Loading . . . ");
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void setIdpelError() {
        txtIdpel.setError("ID Pelanggan tidak boleh kosong");
    }

    @Override
    public void setTahunError() {
        txtTahun.setError("Tahun tidak boleh kosong");
    }

    @Override
    public void setBlnError() {
        txtBulan.setError("Bulan tidak boleh kosong");
    }

    private void initResultView(View mView, final AlertDialog mDialog, final ResponseSerializer mResponseSerializer) {
        TextView txtIdpel = (TextView) mView.findViewById(R.id.rb_lbl_idpel);
        TextView txtNama = (TextView) mView.findViewById(R.id.rb_lbl_nama);
        TextView txtAlamat = (TextView) mView.findViewById(R.id.rb_lbl_alamat);
        TextView txtBlTh = (TextView) mView.findViewById(R.id.rb_lbl_blth);
        TextView txtDaya = (TextView) mView.findViewById(R.id.rb_lbl_daya);
        TextView txtPemakaian = (TextView) mView.findViewById(R.id.rb_lbl_pemkwh);
        TextView txtTagihan = (TextView) mView.findViewById(R.id.rb_lbl_tagihan);
        TextView txtTerbilang = (TextView) mView.findViewById(R.id.rb_lbl_terbilang);
        TextView txtTarif = (TextView) mView.findViewById(R.id.rb_lbl_tarif);
        TextView txtStatus = (TextView) mView.findViewById(R.id.rb_lbl_status);
        Button btnSimpan = (Button) mView.findViewById(R.id.rb_btn_simpan);
        Button btnKeluar = (Button) mView.findViewById(R.id.rb_btn_keluar);

        if (mResponseSerializer.getStatus().equals("success")) {
            txtIdpel.setText(mResponseSerializer.getData().getIdpel());
            txtNama.setText(mResponseSerializer.getData().getNama());
            txtAlamat.setText(mResponseSerializer.getData().getAlamat());
            txtBlTh.setText(mResponseSerializer.getData().getNamathblrek());
            txtDaya.setText(mResponseSerializer.getData().getDaya());
            txtPemakaian.setText(mResponseSerializer.getData().getPemkwh());
            txtTagihan.setText("Rp. " + NumberFormatter.currencyFormat(mResponseSerializer.getData().getTagihan()));
            txtTerbilang.setText(mResponseSerializer.getData().getTerbilang());
            txtTarif.setText(mResponseSerializer.getData().getTarif());

            if (!mResponseSerializer.getData().getKetlunas().isEmpty()) {
                txtStatus.setText("Sudah dibayar");
            } else {
                txtStatus.setText("Belum dibayar");
            }

            btnKeluar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    reset();
                }
            });
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = mPresenter.findBy(mResponseSerializer.getData().getIdpel());
                if (customer == null) {
                    customer = new Customer();
                    customer.setIdpel(mResponseSerializer.getData().getIdpel());
                    customer.setNama(mResponseSerializer.getData().getNama());
                    mPresenter.getRealm().beginTransaction();
                    mPresenter.save(customer);
                    mDialog.dismiss();
                } else {
                    mPresenter.getRealm().beginTransaction();
                    mPresenter.update(customer);
                    mDialog.dismiss();
                }
                reset();
            }
        });
    }

    @Override
    public void showResult(ResponseSerializer result) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

        View view = inflater.inflate(R.layout.result_dialog, null);
        dialogBuilder.setView(view);
        final AlertDialog alertDialog = dialogBuilder.create();

        initResultView(view, alertDialog, result);

        if (result.getStatus().equals("success")) {
            alertDialog.show();
        } else if (result.getStatus().equals("error")) {
            AlertHelper.alertInfo(MainActivity.this, "Data tidak tersedia");
        }
    }

    @Override
    public void reset() {
        txtIdpel.getText().clear();
        txtBulan.setText("Tekan untuk memilih bulan");
    }

    @OnClick(R.id.ci_btn_cek)
    void showResult() {

        String idpel = txtIdpel.getText().toString();
        String th = txtTahun.getText().toString();
        String bln = "";
        if (!txtBulan.getText().toString().isEmpty()) {
            bln = txtBulan.getText().toString().substring(0, 2);
        }

        mPresenter.getResult(idpel, th, bln);
    }

    @OnClick(R.id.ci_img_search)
    void idpelDialog() {
        setupBulan();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.list_idpel_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        initIdpelListView(dialogView, alertDialog);

        alertDialog.show();
    }

    private void initIdpelListView(View view, final AlertDialog alertDialog) {

        listView = (ListView) view.findViewById(R.id.lid_list_idpel);
        ImageView imgClose = (ImageView) view.findViewById(R.id.lid_close);

        idpelAdapter = new IdpelAdapter(view.getContext(), R.layout.list_idpel_dialog, mPresenter.getAll());
        idpelAdapter.attachData(mPresenter, MainActivity.this);
        listView.setAdapter(idpelAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer cs = mPresenter.getAll().get(position);

                txtIdpel.setText(cs.getIdpel());
                alertDialog.dismiss();

                if (view.getId() == R.id.lis_delete) {
                    mPresenter.getRealm().beginTransaction();
                    mPresenter.delete(cs);
                    idpelAdapter.notifyDataSetChanged();
                }
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.deActivateDatabase();
    }

    @Override
    public void buttonClickListener(int position, Customer customer) {
        mPresenter.getRealm().beginTransaction();
        mPresenter.delete(customer);
        idpelAdapter.notifyDataSetChanged();
    }
}
