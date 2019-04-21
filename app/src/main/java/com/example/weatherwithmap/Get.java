package com.example.weatherwithmap;

import java.util.ArrayList;

public class Get {
    Coord coord;
    ArrayList< Object > weather = new ArrayList < Object > ();
    private String base;
    Main main;
    private float visibility;
    Wind WindObject;
    Clouds CloudsObject;
    private float dt;
    Sys SysObject;
    private float id;
    private String name;
    private float cod;

// Getter Methods

    public Coord getCoordObject() {
        return coord;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public float getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return WindObject;
    }

    public Clouds getClouds() {
        return CloudsObject;
    }

    public float getDt() {
        return dt;
    }

    public Sys getSys() {
        return SysObject;
    }

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getCod() {
        return cod;
    }

}
