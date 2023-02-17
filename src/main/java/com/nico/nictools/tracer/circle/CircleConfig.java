package com.nico.nictools.tracer.circle;

import com.nico.nictools.tracer.TraceConfig;

/**
 * Created by nico on 11/03/18.
 */
public class CircleConfig implements TraceConfig<CircleConfig> {

    protected int posX;
    protected int posY;
    protected int diameter;

    public CircleConfig( ) {
    }

    public CircleConfig( int posX, int posY, int diameter ) {
        this.posX = posX;
        this.posY = posY;
        this.diameter = diameter;
    }

    public int getPosX( ) {
        return posX;
    }

    public int getPosY( ) {
        return posY;
    }

    public int getDiameter( ) {
        return diameter;
    }

    public CircleConfig withPosX( int posX ) {
        this.posX = posX;
        return this;
    }

    public CircleConfig withPosY( int posY ) {
        this.posY = posY;
        return this;
    }

    public CircleConfig withDiameter( int diameter ) {
        this.diameter = diameter;
        return this;
    }
}
