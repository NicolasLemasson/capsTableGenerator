package com.nico.nictools.tracer.complex;

import com.nico.nictools.tracer.TraceConfig;

/**
 * Created by nico on 11/03/18.
 */
public class RoseConfig implements TraceConfig {

    protected int posX;
    protected int posY;
    protected int diameter;

    public int getDiameter( ) {
        return diameter;
    }

    public RoseConfig withPosX( int posX ) {
        this.posX = posX;
        return this;
    }

    public RoseConfig withPosY( int posY ) {
        this.posY = posY;
        return this;
    }

    public RoseConfig withDiameter( int diameter ) {
        this.diameter = diameter;
        return this;
    }
}
