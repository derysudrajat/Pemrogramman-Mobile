package com.derysudrajat.myrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class Hima implements Parcelable {
    public static final Parcelable.Creator<Hima> CREATOR = new Parcelable.Creator<Hima>() {
        @Override
        public Hima createFromParcel(Parcel source) {
            return new Hima(source);
        }

        @Override
        public Hima[] newArray(int size) {
            return new Hima[size];
        }
    };
    String urlImg;
    String namaHm;
    String descHm;

    public Hima() {
    }

    public Hima(String urlImg, String namaHm, String descHm) {
        this.urlImg = urlImg;
        this.namaHm = namaHm;
        this.descHm = descHm;
    }

    protected Hima(Parcel in) {
        this.urlImg = in.readString();
        this.namaHm = in.readString();
        this.descHm = in.readString();
    }

    public String getUrlImg() {
        return urlImg;
    }

    public String getNamaHm() {
        return namaHm;
    }

    public String getDescHm() {
        return descHm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.urlImg);
        dest.writeString(this.namaHm);
        dest.writeString(this.descHm);
    }
}
