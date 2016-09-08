package com.lc.belajar.layout.main;

import android.content.Context;
import android.os.Parcelable;
import android.widget.Toast;

import com.lc.belajar.orm.configuration.DatabaseConfiguration;
import com.lc.belajar.orm.entity.Customer;
import com.lc.belajar.retrofit.entity.ResponseSerializer;
import com.lc.belajar.retrofit.services.service.DataService;
import com.lc.belajar.retrofit.services.utils.ServiceGenerator;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eby on 07/09/16.
 */
public class MainPresenter implements MainMvpView.SetupDatabase, MainMvpView.DataManipulation {
    private Realm realm;
    MainMvpView.LoadData mLoadData;
    ResponseSerializer mResponseSerializer;
    Subscription mSubscription;
    Context mContext;

    public MainPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void attchView(MainMvpView.LoadData mLoadData) {
        this.mLoadData = mLoadData;
    }

    public void detachView() {
        this.mLoadData = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    @Override
    public void activateDatabase() {
        RealmConfiguration mRealmConfiguration = DatabaseConfiguration.configure(mContext);
        realm = Realm.getInstance(mRealmConfiguration);
    }

    @Override
    public void deActivateDatabase() {
        if (!realm.isClosed()) {
            realm.close();
        }
    }


    @Override
    public Realm getRealm() {
        return realm;
    }

    @Override
    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void save(Customer customer) {
        realm.copyToRealm(customer);
        realm.commitTransaction();
    }

    @Override
    public void update(Customer customer) {
        realm.copyToRealmOrUpdate(customer);
        realm.commitTransaction();
    }

    @Override
    public void delete(Customer customer) {
        RealmResults<Customer> results = realm.where(Customer.class).equalTo("idpel", customer.getIdpel()).findAll();
        if (results != null) {
            results.deleteFromRealm(0);
            realm.commitTransaction();
        }
    }

    @Override
    public Customer findBy(String idpel) {
        return realm.where(Customer.class).equalTo("idpel", idpel).findFirst();
    }

    @Override
    public RealmResults<Customer> getAll() {
        return realm.where(Customer.class).findAll();
    }

    @Override
    public void getResult(String idpel, String thn, String bln) {
        mLoadData.showProgress();

        DataService service = ServiceGenerator.createService(DataService.class);
        mSubscription = service.getData(idpel, thn, bln)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseSerializer>() {
                    @Override
                    public void onCompleted() {
                        mLoadData.showResult(mResponseSerializer);
                        mLoadData.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "Koneksi bermasalah, Periksa koneksi dan coba lagi", Toast.LENGTH_SHORT).show();
                        mLoadData.hideProgress();
                    }

                    @Override
                    public void onNext(ResponseSerializer responseSerializer) {
                        mResponseSerializer = responseSerializer;
                    }
                });

    }
}
