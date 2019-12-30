package com.derysudrajat.mycalass;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Crew implements Parcelable {
    public static final Parcelable.Creator<Crew> CREATOR = new Parcelable.Creator<Crew>() {
        @Override
        public Crew createFromParcel(Parcel source) {
            return new Crew(source);
        }

        @Override
        public Crew[] newArray(int size) {
            return new Crew[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nomor")
    int nomor;
    @ColumnInfo(name = "nama")
    String nama;
    @ColumnInfo(name = "date")
    String date;
    @ColumnInfo(name = "jk")
    String jk;
    @ColumnInfo(name = "alamat")
    String alamat;

    public Crew() {
    }

    protected Crew(Parcel in) {
        this.id = in.readInt();
        this.nomor = in.readInt();
        this.nama = in.readString();
        this.date = in.readString();
        this.jk = in.readString();
        this.alamat = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.nomor);
        dest.writeString(this.nama);
        dest.writeString(this.date);
        dest.writeString(this.jk);
        dest.writeString(this.alamat);
    }
}
