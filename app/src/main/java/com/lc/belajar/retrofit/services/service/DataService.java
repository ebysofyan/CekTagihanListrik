package com.lc.belajar.retrofit.services.service;

import com.lc.belajar.retrofit.entity.ResponseSerializer;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by eby on 07/09/16.
 */
public interface DataService {
    @GET("api/tagihan-pln?idp=&thn=&bln=")
    Observable<ResponseSerializer> getData(@Query("idp") String idpel, @Query("thn") String tahun, @Query("bln") String bulan);
}
