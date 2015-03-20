package com.davidoladeji.restbox.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.List;

@XStreamAlias("distances")
public class Distances<E> {


    Distance distance;
    @XStreamOmitField
    private int count;
    @XStreamOmitField
    private List<Distance> distances;


    public Distances() {
    }

    public Distances(List<Distance> distances) {
        this.distances = distances;
        this.count = distances.size();
    }

    public Distances(Distance distance) {
        this.distance = distance;
    }

    public List<Distance> getDistances() {

        return distances;
    }

    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}

