package com.example.krisha.lostandfound;

public class ObjectDetails {

    public String name;
    public String contact;
    public String object;
    public String url;


    public ObjectDetails(String name, String contact, String object, String url){
      this.name = name;
      this.contact = contact;
      this.object = object;
      this.url = url;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getContact(){
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getObject(){
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ObjectDetails(){ }

   /* public String toString(){
        return "Lost Object: " + object + "\nName: " + this.name + "\nLO&T-FOUND Department: " + contact;
    } */
}
