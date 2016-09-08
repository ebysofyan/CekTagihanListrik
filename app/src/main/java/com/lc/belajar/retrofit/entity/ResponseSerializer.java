package com.lc.belajar.retrofit.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eby on 07/09/16.
 */
public class ResponseSerializer implements Parcelable {
    private String status;
    private QuerySerializer query;
    private DataSerializer data;

    public ResponseSerializer() {
    }

    protected ResponseSerializer(Parcel in) {
        status = in.readString();
        query = in.readParcelable(QuerySerializer.class.getClassLoader());
        data = in.readParcelable(DataSerializer.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeParcelable(query, flags);
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseSerializer> CREATOR = new Creator<ResponseSerializer>() {
        @Override
        public ResponseSerializer createFromParcel(Parcel in) {
            return new ResponseSerializer(in);
        }

        @Override
        public ResponseSerializer[] newArray(int size) {
            return new ResponseSerializer[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public QuerySerializer getQuery() {
        return query;
    }

    public DataSerializer getData() {
        return data;
    }
}
