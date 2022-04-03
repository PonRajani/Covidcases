package com.cst2335.finalproject.models;

public class CovidData {

String province,caseNumber,date,country,dataBaseId;

public CovidData(String province, String caseNumber, String dataBaseId, String date, String country)
{
    this.province=province;
    this.caseNumber=caseNumber;
    this.dataBaseId=dataBaseId;
    this.date=date;
    this.country=country;
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

    public String getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(String dataBaseId) {
        this.dataBaseId = dataBaseId;
    }
}
