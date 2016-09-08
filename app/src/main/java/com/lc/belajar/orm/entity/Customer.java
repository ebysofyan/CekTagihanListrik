package com.lc.belajar.orm.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by eby on 07/09/16.
 */
public class Customer extends RealmObject {
    @PrimaryKey
    private String idpel;
    private String nama;

    public String getIdpel() {
        return idpel;
    }

    public void setIdpel(String idpel) {
        this.idpel = idpel;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
