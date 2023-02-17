package com.nico.nictools.tracer.complex;

import com.nico.nictools.tracer.AbstractTracer;
import com.nico.nictools.tracer.TocTracer;
import com.nico.nictools.tracer.TraceConfig;
import com.nico.nictools.tracer.circle.CircleConfig;
import com.nico.nictools.tracer.circle.CircleTracer;

import java.awt.*;

import static com.nico.nictools.tracer.TocTracer.DIAMETER;

/**
 * Created by nico on 15/03/18.
 */
public class EndTracer extends AbstractTracer<CircleConfig> {
    private int MARGIN = 295;

    @Override
    public void trace( Graphics graphics, TraceConfig<CircleConfig> config ) {
        int posY = MARGIN;
        int posX = MARGIN;
        CircleTracer circleTracer = new CircleTracer();
        for( int i = 0; i < 4; i++ ) {
            circleTracer.trace( graphics, config.getConfig().withPosX( posX ).withPosY( posY ) );
            posX += TocTracer.TROU;
            posY += TocTracer.TROU;
        }

        posX = DIAMETER - MARGIN;
        posY = MARGIN;
        for( int i = 0; i < 4; i++ ) {
            circleTracer.trace( graphics, config.getConfig().withPosX( posX ).withPosY( posY ) );
            posX -= TocTracer.TROU;
            posY += TocTracer.TROU;
        }

        posX = DIAMETER - MARGIN;
        posY = DIAMETER - MARGIN;
        for( int i = 0; i < 4; i++ ) {
            circleTracer.trace( graphics, config.getConfig().withPosX( posX ).withPosY( posY ) );
            posX -= TocTracer.TROU;
            posY -= TocTracer.TROU;
        }

        posX = MARGIN;
        posY = DIAMETER - MARGIN;
        for( int i = 0; i < 4; i++ ) {
            circleTracer.trace( graphics, config.getConfig().withPosX( posX ).withPosY( posY ) );
            posX += TocTracer.TROU;
            posY -= TocTracer.TROU;
        }
    }
}
