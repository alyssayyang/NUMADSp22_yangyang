package edu.neu.myapplication;

public class Link {

    private String linkname;
    private String linkurl;

    public Link(String name, String url){
        this.linkname = name;
        this.linkurl = url;
    }

    public String getLinkname(){
        return linkname;
    }

    public void setLinkname(String name){
        this.linkname= name;
    }

    public String getLinkurl(){
        return linkurl;
    }

    public void setLinkurl(String url){
        this.linkurl = url;
    }

}
