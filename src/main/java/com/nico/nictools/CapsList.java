package com.nico.nictools;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;

/**
 * Created by nico on 05/02/18.
 */
public class CapsList {
    public ArrayList<PosCaps> posCaps;


    public CapsList( ) {
        posCaps = new ArrayList<>();
    }



    public ArrayList<PosCaps> getPosCaps( ) {
        return posCaps;
    }

    public void setPosCaps( ArrayList<PosCaps> posCaps ) {
        this.posCaps = posCaps;
    }
}
