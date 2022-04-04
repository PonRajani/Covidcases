package com.cst2335.finalproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CovidData {

    String province;
    int caseNumber;
    String date;
    String country;
    String dataBaseId;
    String Citycode;
    String Status;
    String Lon;
    String City;
    String CountryCode;
    String Lat;

    public CovidData(Parcel in) {
        Citycode = in.readString();
        Status = in.readString();
        country = in.readString();
        Lon = in.readString();
        City = in.readString();
        CountryCode = in.readString();
        province = in.readString();
        Lat = in.readString();
        caseNumber = in.readInt();
        date = in.readString();
    }

    public static final Parcelable.Creator<CovidData> CREATOR = new Parcelable.Creator<CovidData>() {
        @Override
        public CovidData createFromParcel(Parcel in) {
            return new CovidData(in);
        }

        @Override
        public CovidData[] newArray(int size) {
            return new CovidData[size];
        }
    };

    public CovidData(String province, String caseNumber, String valueOf, String date, String country) {
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCitycode() {
        return Citycode;
    }

    public String getStatus() {
        return Status;
    }

    public String getLon() {
        return Lon;
    }

    public String getCity() {
        return City;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getLat() {
        return Lat;
    }

    public String getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(String dataBaseId) {
        this.dataBaseId = dataBaseId;
    }

    @Override
    public String toString() {
        return
                "Data{" +
                        "cityCode = '" + Citycode + '\'' +
                        ",status = '" + Status + '\'' +
                        ",country = '" + country + '\'' +
                        ",lon = '" + Lon + '\'' +
                        ",city = '" + City + '\'' +
                        ",countryCode = '" + CountryCode + '\'' +
                        ",province = '" + province + '\'' +
                        ",lat = '" + Lat + '\'' +
                        ",cases = '" + caseNumber + '\'' +
                        ",date = '" + date + '\'' +
                        "}";
    }
        @Override
        public int describeContents() {
            return 0;
        }
        @Override
        public void writeToParcel (Parcel parcel,int i){
            parcel.writeString(Citycode);
            parcel.writeString(Status);
            parcel.writeString(country);
            parcel.writeString(Lon);
            parcel.writeString(City);
            parcel.writeString(CountryCode);
            parcel.writeString(province);
            parcel.writeString(Lat);
            parcel.writeInt(caseNumber);
            parcel.writeString(date);
        }
    }












