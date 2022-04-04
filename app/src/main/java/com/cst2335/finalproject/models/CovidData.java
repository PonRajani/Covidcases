package com.cst2335.finalproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CovidData {

    String province;
    int caseNumber;
    String date;
    String country;
    String dataBaseId;
    String citycode;
    String status;
    String lon;
    String city;
    String countryCode;
    String lat;
    int setcases;
    int getCases;

    /*public CovidData(String province, int caseNumber,  String date, String country, String cityCode, String status, String lon, String city, String countryCode, String lat) {
        this.Citycode = cityCode;
        this.Status = status;
        this.country = country;
        this.Lon = lon;
        this.City = city;
        this.CountryCode = countryCode;
        this.province = province;
        this.Lat = lat;
        this.caseNumber = caseNumber;
        this.date = date;
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
    };*/

    public CovidData(String province, int caseNumber, String dataBaseId, String date, String country,
                     String citycode, String status, String lon, String countycode, String lat, String city)
    {
        this.province = province;
        this.caseNumber = caseNumber;
        this.dataBaseId = dataBaseId;
        this.date = date;
        this.country = country;
        this.citycode = citycode;
        this.status = status;
        this.lon = lon;
        this.countryCode = countycode;
        this.lat = lat;
        this.city = city;
    }
/*
    public CovidData() {
    }

    public CovidData(Parcel in) {
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

    public void setCityCode(String citycode) {
        Citycode = citycode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status){
        Status = status;
    }
    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        Lon = lon;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public int getCases() {
        return getCases;
    }

    public void setCases(int setcases){
        this.setcases=setcases;


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
    *//*@Override
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
        }*/

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

    public String getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(String dataBaseId) {
        this.dataBaseId = dataBaseId;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getSetcases() {
        return setcases;
    }

    public void setSetcases(int setcases) {
        this.setcases = setcases;
    }

    public int getGetCases() {
        return getCases;
    }

    public void setGetCases(int getCases) {
        this.getCases = getCases;
    }
}












