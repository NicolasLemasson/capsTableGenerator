package com.nico.nictools.tracer.complex;

import com.nico.nictools.tracer.AbstractTracer;
import com.nico.nictools.tracer.TocTracer;
import com.nico.nictools.tracer.TraceConfig;
import com.nico.nictools.tracer.circle.CircleConfig;
import com.nico.nictools.tracer.circle.CircleTracer;

import java.awt.*;

/**
 * Created by nico on 15/03/18.
 */
public class StartTracer extends AbstractTracer<CircleConfig> {

    @Override
    public void trace( Graphics image, TraceConfig<CircleConfig> config ) {
        int posX = TocTracer.DIAMETER / 2;
        int posY = 175;
        int MARIGN = (int) ( TocTracer.TROU / 1.618 )+1;
        int DECALE = MARIGN;
        CircleTracer circleTracer = new CircleTracer();
        circleTracer.trace( image, config.getConfig().withPosX( posX - MARIGN    + 3* DECALE ).withPosY( posY - MARIGN - DECALE) );
        circleTracer.trace( image, config.getConfig().withPosX( posX + MARIGN    + 3* DECALE ).withPosY( posY - MARIGN - DECALE) );
        circleTracer.trace( image, config.getConfig().withPosX( posX - MARIGN    + 3* DECALE ).withPosY( posY + MARIGN - DECALE) );
        circleTracer.trace( image, config.getConfig().withPosX( posX + 3* MARIGN + 3* DECALE).withPosY( posY - MARIGN  - DECALE) );

        posX = TocTracer.DIAMETER / 2;
        posY = TocTracer.DIAMETER - 175;
        circleTracer.trace( image, config.getConfig().withPosX( posX - 3* MARIGN - 3*DECALE).withPosY( posY + MARIGN + DECALE) );
        circleTracer.trace( image, config.getConfig().withPosX( posX + MARIGN    - 3*DECALE).withPosY( posY - MARIGN + DECALE) );
        circleTracer.trace( image, config.getConfig().withPosX( posX - MARIGN    - 3*DECALE).withPosY( posY + MARIGN + DECALE) );
        circleTracer.trace( image, config.getConfig().withPosX( posX + MARIGN    - 3*DECALE).withPosY( posY + MARIGN + DECALE) );

        posX = 175;
        posY = TocTracer.DIAMETER / 2;
        circleTracer.trace( image, config.getConfig().withPosX( posX - MARIGN - DECALE ).withPosY( posY - MARIGN  - 3 * DECALE  ) );
        circleTracer.trace( image, config.getConfig().withPosX( posX - MARIGN - DECALE ).withPosY( posY - 3*MARIGN- 3 * DECALE ) );
        circleTracer.trace( image, config.getConfig().withPosX( posX - MARIGN - DECALE ).withPosY( posY + MARIGN  - 3 * DECALE ) );
        circleTracer.trace( image, config.getConfig().withPosX( posX + MARIGN - DECALE ).withPosY( posY + MARIGN  - 3 * DECALE ) );

        posX = TocTracer.DIAMETER - 175;
        posY = TocTracer.DIAMETER / 2;
        circleTracer.trace( image, config.getConfig().withPosX( posX - MARIGN + DECALE).withPosY( 3*DECALE + posY - MARIGN ) );
        circleTracer.trace( image, config.getConfig().withPosX( posX + MARIGN + DECALE).withPosY( 3*DECALE + posY - MARIGN ) );
        circleTracer.trace( image, config.getConfig().withPosX( posX + MARIGN + DECALE).withPosY( 3*DECALE + posY + 3* MARIGN ) );
        circleTracer.trace( image, config.getConfig().withPosX( posX + MARIGN + DECALE).withPosY( 3*DECALE + posY + MARIGN ) );
    }
}
