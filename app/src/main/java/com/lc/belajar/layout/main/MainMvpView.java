package com.lc.belajar.layout.main;

import android.os.Parcelable;

import com.lc.belajar.orm.entity.Customer;
import com.lc.belajar.retrofit.entity.ResponseSerializer;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Subscription;

/**
 * Created by eby on 07/09/16.
 */
public interface MainMvpView {
    interface LoadData {
        void showProgress();

        void hideProgress();

        void setIdpelError();

        void setTahunError();

        void setBlnError();

        void showResult(ResponseSerializer result);

        void reset();
    }

    interface SetupDatabase {
        void activateDatabase();

        void deActivateDatabase();
    }

    interface DataManipulation {

        Realm getRealm();

        void setRealm(Realm realm);

        void save(Customer customer);

        void update(Customer customer);

        void delete(Customer customer);

        Customer findBy(String idpel);

        RealmResults<Customer> getAll();

        void getResult(String idpel, String tahn, String bln);
    }

    interface ButtonListener {
        void buttonClickListener(int position, Customer customer);
    }
}
