package com.davidoladeji.restbox.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;


@XStreamAliasType(value = "Distance")
public class Distance {

    @XStreamAlias(value = "id")
    private Integer id;

    @XStreamAlias(value = "townone")
    private int townone;

    @XStreamAlias(value = "towntwo")
    private int towntwo;

    @XStreamAlias(value = "miles")
    private int miles;

    public Distance() {
    }

    public Distance(Integer id, int townone, int towntwo, int miles) {
        super();
        this.id = id;
        this.townone = townone;
        this.towntwo = towntwo;
        this.miles = miles;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getTownone() {
        return townone;
    }

    public void setTownone(int townone) {
        this.townone = townone;
    }

    public int getTowntwo() {
        return towntwo;
    }

    public void setTowntwo(int towntwo) {
        this.towntwo = towntwo;
    }
}

