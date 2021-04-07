package com.kiscode.recylerview.sample.model;

/**
 * Description: 汽车品牌型号
 * Author: keno
 * Date : 2021/4/6 17:54
 **/
public class Automobile {

    /**
     * id : 220
     * name : A3
     * fullname : 奥迪A3
     * logo : http://api.jisuapi.com/car/static/images/logo/300/220.jpg
     * salestate : 在销
     * depth : 3
     */

    private String id;
    private String name;
    private String fullname;
    private String logo;
    private String salestate;
    private int depth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSalestate() {
        return salestate;
    }

    public void setSalestate(String salestate) {
        this.salestate = salestate;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}