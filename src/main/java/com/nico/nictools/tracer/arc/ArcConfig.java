package com.nico.nictools.tracer.arc;

import com.nico.nictools.tracer.TraceConfig;
import com.nico.nictools.tracer.circle.CircleConfig;

/**
 * Created by nico on 11/03/18.
 */
public class ArcConfig implements TraceConfig<ArcConfig> {
    int posX;
    int posY;
    int diameter;
    double startAngle;
    int angle;



    public double getStartAngle( ) {
        return startAngle;
    }

    public int getAngle( ) {
        return angle;
    }

    public ArcConfig withStartAngle( double startAngle ) {
        this.startAngle = startAngle;
        return this;
    }

    public ArcConfig withAngle( int angle ) {
        this.angle = angle;
        return this;
    }

    public ArcConfig withPosX( int posX ) {
        this.posX = posX;
        return this;
    }

    public ArcConfig withPosY( int posY ) {
        this.posY = posY;
        return this;
    }

    public ArcConfig withDiameter( int diameter ) {
        this.diameter = diameter;
        return this;
    }

    public int getDiameter( ) {
        return diameter;
    }

    public int getPosY( ) {
        return posY;
    }

    public int getPosX( ) {
        return posX;
    }

    public TraceConfig<ArcConfig> copy( ) {
        return new ArcConfig().withAngle( this.angle ).withDiameter( this.diameter )
                .withPosX( this.posX ).withPosY( this.posY ).withStartAngle( this.startAngle );
    }
}
