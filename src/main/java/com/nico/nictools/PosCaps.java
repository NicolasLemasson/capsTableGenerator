package com.nico.nictools;

/**
 * Created by nico on 04/02/18.
 */
public class PosCaps {

    int x;
    int y;
    int index;

    public PosCaps( int x, int y, int index ) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public PosCaps( ) {
    }

    public void setX( int x ) {
        this.x = x;
    }

    public void setY( int y ) {
        this.y = y;
    }

    public void setIndex( int index ) {
        this.index = index;
    }

    public int getX( ) {
        return x;
    }

    public int getY( ) {
        return y;
    }

    public int getIndex( ) {
        return index;
    }
}
