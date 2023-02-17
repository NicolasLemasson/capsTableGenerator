package com.nico.nictools.tracer.drawer;

import com.nico.nictools.tracer.arc.ArcConfig;
import com.nico.nictools.tracer.circle.CircleConfig;
import com.nico.nictools.tracer.circle.CircleTracer;
import com.nico.nictools.tracer.math.Position;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by nico on 12/03/18.
 */
public class FigureRepartition {
    private CircleTracer circleTracer = new CircleTracer();

    public void fillArc( Graphics graphics, ArcConfig config, CircleConfig circleConfig, int nb ) {
        java.util.List<Position> centers = getCenters( config, nb );
        for( Position center : centers ) {
            CircleConfig subConfig = circleConfig.withPosX( center.getX() ).withPosY( center.getY() );
            circleTracer.trace( graphics, subConfig );
        }
    }

    public void fillSymArc( Graphics graphics, ArcConfig config, CircleConfig circleConfig, int nb, Position symCenter ) {
        java.util.List<Position> centers = getCenters( config, nb );
        for( Position center : centers ) {
            CircleConfig subConfig = circleConfig.withPosX( center.getX() ).withPosY( center.getY() );
            circleTracer.trace( graphics, subConfig );
            CircleConfig symConfig = new CircleConfig( symCenter.getX() + ( symCenter.getX() - center.getX() ), center.getY(), circleConfig.getDiameter() );
            circleTracer.trace( graphics, symConfig );

        }
    }

    public void fillSymArc( Graphics graphics, ArcConfig config, CircleConfig circleConfig, int nb, boolean sens ) {
        int decale = 3;
        java.util.List<Position> centers = getCenters( config, nb );
        int firstCenterX;
        if( sens )
            firstCenterX = centers.get( 0 ).getX();
        else
            firstCenterX = centers.get( centers.size() - 1 ).getX();
        for( Position center : centers ) {
            CircleConfig subConfig = circleConfig.withPosX( center.getX()  ).withPosY( center.getY() + decale );
            CircleConfig symConfig = new CircleConfig( firstCenterX + ( firstCenterX - center.getX() ), center.getY() + decale, circleConfig.getDiameter() );
            circleTracer.trace( graphics, subConfig );
            circleTracer.trace( graphics, symConfig );
        }
    }

    private java.util.List<Position> getCenters( ArcConfig config, int nb ) {
        java.util.List<Position> positions = new ArrayList();
        double angleIncrement = config.getAngle() / Double.valueOf( nb );
        for( int i = 0; i < nb; i++ ) {
            double angle = config.getStartAngle() + angleIncrement * i; //+ angleIncrement/2;
            positions.add( new Position(
                    (int) ( ( config.getPosX() + ( Math.cos( Math.toRadians( Double.valueOf( angle ) ) ) * ( config.getDiameter() / 2.0 ) ) ) + config.getDiameter() / 2 ),
                    (int) ( ( config.getPosY() - ( Math.sin( Math.toRadians( Double.valueOf( angle ) ) ) * ( config.getDiameter() / 2.0 ) ) ) + config.getDiameter() / 2 )
            ) );
        }
        return positions;
    }

}
