package com.cst2335.finalproject.models;

public class CovidData {

String province,caseNumber,date,country,dataBaseId,Citycode, Status, Lon, City, CountryCode
       ,Lat;

public CovidData(String province, String caseNumber, String dataBaseId, String date, String country, String CityCode,
String Status, String Lon, String City, String CountryCode, String Lat)
{
    this.province=province;
    this.caseNumber=caseNumber;
    this.dataBaseId=dataBaseId;
    this.date=date;
    this.country=country;
    this.Citycode=CityCode;
    this.Status = Status;
    this.Lon = Lon;
    this.City = City;
    this.CountryCode=CountryCode;
    this.Lat = Lat;
}

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
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

    public String getStatus() { return Status; }

    public String getLon() { return Lon; }

    public String getCity() { return City; }

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
}
