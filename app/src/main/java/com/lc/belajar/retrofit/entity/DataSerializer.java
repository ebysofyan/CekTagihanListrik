package com.lc.belajar.retrofit.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eby on 07/09/16.
 */
public class DataSerializer implements Parcelable{
    private String diskon;
    private String angsuran;
    private String thblrek;
    private String lwbp;
    private String beban;
    private String ketlunas;
    private String fakmkvam;
    private String bpju;
    private String ptl;
    private String idpel;
    private String sahlwbp;
    private String tglbacalalu;
    private String slawbp;
    private String nama;
    private String namaupi;
    private String daya;
    private String fjn;
    private String jamnyala;
    private String slalwbp;
    private String pemkwh;
    private double tagihan;
    private String namathblrek;
    private String terbilang;
    private String wbp;
    private String alamat;
    private String tglbacaakhir;
    private String tarif;

    public DataSerializer() {
    }

    protected DataSerializer(Parcel in) {
        diskon = in.readString();
        angsuran = in.readString();
        thblrek = in.readString();
        lwbp = in.readString();
        beban = in.readString();
        ketlunas = in.readString();
        fakmkvam = in.readString();
        bpju = in.readString();
        ptl = in.readString();
        idpel = in.readString();
        sahlwbp = in.readString();
        tglbacalalu = in.readString();
        slawbp = in.readString();
        nama = in.readString();
        namaupi = in.readString();
        daya = in.readString();
        fjn = in.readString();
        jamnyala = in.readString();
        slalwbp = in.readString();
        pemkwh = in.readString();
        tagihan = in.readDouble();
        namathblrek = in.readString();
        terbilang = in.readString();
        wbp = in.readString();
        alamat = in.readString();
        tglbacaakhir = in.readString();
        tarif = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diskon);
        dest.writeString(angsuran);
        dest.writeString(thblrek);
        dest.writeString(lwbp);
        dest.writeString(beban);
        dest.writeString(ketlunas);
        dest.writeString(fakmkvam);
        dest.writeString(bpju);
        dest.writeString(ptl);
        dest.writeString(idpel);
        dest.writeString(sahlwbp);
        dest.writeString(tglbacalalu);
        dest.writeString(slawbp);
        dest.writeString(nama);
        dest.writeString(namaupi);
        dest.writeString(daya);
        dest.writeString(fjn);
        dest.writeString(jamnyala);
        dest.writeString(slalwbp);
        dest.writeString(pemkwh);
        dest.writeDouble(tagihan);
        dest.writeString(namathblrek);
        dest.writeString(terbilang);
        dest.writeString(wbp);
        dest.writeString(alamat);
        dest.writeString(tglbacaakhir);
        dest.writeString(tarif);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataSerializer> CREATOR = new Creator<DataSerializer>() {
        @Override
        public DataSerializer createFromParcel(Parcel in) {
            return new DataSerializer(in);
        }

        @Override
        public DataSerializer[] newArray(int size) {
            return new DataSerializer[size];
        }
    };

    public String getDiskon() {
        return diskon;
    }

    public String getAngsuran() {
        return angsuran;
    }

    public String getThblrek() {
        return thblrek;
    }

    public String getLwbp() {
        return lwbp;
    }

    public String getBeban() {
        return beban;
    }

    public String getKetlunas() {
        return ketlunas;
    }

    public String getFakmkvam() {
        return fakmkvam;
    }

    public String getBpju() {
        return bpju;
    }

    public String getPtl() {
        return ptl;
    }

    public String getIdpel() {
        return idpel;
    }

    public String getSahlwbp() {
        return sahlwbp;
    }

    public String getTglbacalalu() {
        return tglbacalalu;
    }

    public String getSlawbp() {
        return slawbp;
    }

    public String getNama() {
        return nama;
    }

    public String getNamaupi() {
        return namaupi;
    }

    public String getDaya() {
        return daya;
    }

    public String getFjn() {
        return fjn;
    }

    public String getJamnyala() {
        return jamnyala;
    }

    public String getSlalwbp() {
        return slalwbp;
    }

    public String getPemkwh() {
        return pemkwh;
    }

    public double getTagihan() {
        return tagihan;
    }

    public String getNamathblrek() {
        return namathblrek;
    }

    public String getTerbilang() {
        return terbilang;
    }

    public String getWbp() {
        return wbp;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTglbacaakhir() {
        return tglbacaakhir;
    }

    public String getTarif() {
        return tarif;
    }
}
