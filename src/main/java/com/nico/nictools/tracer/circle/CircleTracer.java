package com.nico.nictools.tracer.circle;

import com.nico.nictools.tracer.AbstractTracer;
import com.nico.nictools.tracer.TraceConfig;

import java.awt.*;

/**
 * Created by nico on 11/03/18.
 */
public class CircleTracer extends AbstractTracer<CircleConfig> {

    boolean fill;

    public CircleTracer( ) {
        this( true );
    }

    public CircleTracer( boolean fill ) {
        this.fill = fill;
    }

    @Override
    public void trace( Graphics image, TraceConfig<CircleConfig> config ) {
        CircleConfig circleConfig = config.getConfig();
        int replacement = circleConfig.getDiameter() / 2;
        if( fill ) {
            image.fillOval(
                    circleConfig.getPosX() - replacement,
                    circleConfig.getPosY() - replacement,
                    circleConfig.getDiameter(),
                    circleConfig.getDiameter()
            );
            image.setColor( Color.BLACK );
            image.drawOval(
                    circleConfig.getPosX() -1,
                    circleConfig.getPosY() -1,
                    1,
                    1
            );
            image.drawOval(
                    circleConfig.getPosX() ,
                    circleConfig.getPosY() -1,
                    1,
                    1
            );
            image.setColor( Color.WHITE );
        }
        else {
            image.drawOval(
                    circleConfig.getPosX() - replacement,
                    circleConfig.getPosY() - replacement,
                    circleConfig.getDiameter(),
                    circleConfig.getDiameter()
            );
        }
    }

}

