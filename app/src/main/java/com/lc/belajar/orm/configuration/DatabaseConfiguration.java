package com.lc.belajar.orm.configuration;

import android.content.Context;
import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by eby on 30/05/16.
 */
public class DatabaseConfiguration {

    @NonNull
    public static RealmConfiguration configure(Context context) {
        RealmConfiguration rc = new RealmConfiguration.Builder(context)
                .name("fllaj.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(rc);
        return rc;
    }

}