package com.lc.belajar.retrofit.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eby on 07/09/16.
 */
public class QuerySerializer implements Parcelable{
    @SerializedName("id_pelanggan")
    private String idpel;
    private String tahun;
    private String bulan;

    public QuerySerializer() {
    }

    protected QuerySerializer(Parcel in) {
        idpel = in.readString();
        tahun = in.readString();
        bulan = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idpel);
        dest.writeString(tahun);
        dest.writeString(bulan);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuerySerializer> CREATOR = new Creator<QuerySerializer>() {
        @Override
        public QuerySerializer createFromParcel(Parcel in) {
            return new QuerySerializer(in);
        }

        @Override
        public QuerySerializer[] newArray(int size) {
            return new QuerySerializer[size];
        }
    };

    public String getIdpel() {
        return idpel;
    }

    public String getTahun() {
        return tahun;
    }

    public String getBulan() {
        return bulan;
    }
}
