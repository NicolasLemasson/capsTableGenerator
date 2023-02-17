package com.nico.nictools.tracer.complex;

import com.nico.nictools.tracer.AbstractTracer;
import com.nico.nictools.tracer.TraceConfig;
import com.nico.nictools.tracer.arc.ArcConfig;
import com.nico.nictools.tracer.arc.ArcTracer;
import com.nico.nictools.tracer.circle.CircleConfig;
import com.nico.nictools.tracer.math.MathHelper;
import com.nico.nictools.tracer.math.Position;

import java.awt.*;

/**
 * Created by nico on 11/03/18.
 */
public class RoseTracer extends AbstractTracer<CircleConfig> {
    private MathHelper mathHelper = new MathHelper();
    private ArcTracer arcTracer = new ArcTracer();

    @Override
    public void trace( Graphics image, TraceConfig<CircleConfig> config ) {
        int circleParts = 6;
        int angle = 360 / circleParts;
        int replacement = config.getConfig().getDiameter() / 2;
        java.util.List<Position> dividedCirclePositions = mathHelper.getDividedCirclePositions( config.getConfig(), circleParts );
        for( int i = 0; i < circleParts; i++ ) {
            Position arcPosition = dividedCirclePositions.get( i );
            arcTracer.trace(
                    image,
                    new ArcConfig()
                            .withPosX( arcPosition.getX() + replacement )
                            .withPosY( arcPosition.getY() + replacement )
                            .withDiameter( config.getConfig().getDiameter() )
                            .withStartAngle( i * angle + 2 * angle )
                            .withAngle( angle * 2 ) // 120
            );
        }
    }
}
