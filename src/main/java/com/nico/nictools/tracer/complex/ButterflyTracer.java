package com.nico.nictools.tracer.complex;

import com.nico.nictools.tracer.TocTracer;
import com.nico.nictools.tracer.TraceConfig;
import com.nico.nictools.tracer.arc.ArcConfig;
import com.nico.nictools.tracer.arc.ArcTracer;
import com.nico.nictools.tracer.circle.CircleConfig;
import com.nico.nictools.tracer.circle.CircleTracer;
import com.nico.nictools.tracer.drawer.FigureRepartition;
import com.nico.nictools.tracer.math.Position;

import java.awt.*;

/**
 * Created by nico on 12/03/18.
 */
public class ButterflyTracer extends CircleTracer {

    private ArcTracer arcTracer = new ArcTracer();

    @Override
    public void trace( Graphics image, TraceConfig<CircleConfig> config ) {

        int RAYON = config.getConfig().getDiameter() / 2;
        Position[] positions = new Position[]{
                new Position(
                        (int) ( Math.cos( Math.toRadians( 45 ) ) * RAYON ) + RAYON,
                        (int) ( Math.sin( Math.toRadians( 45 ) ) * RAYON ) + RAYON
                ),
                new Position(
                        (int) ( Math.cos( Math.toRadians( 135 ) ) * RAYON ) + RAYON,
                        (int) ( Math.sin( Math.toRadians( 135 ) ) * RAYON ) + RAYON
                ),
                new Position(
                        (int) ( Math.cos( Math.toRadians( 225 ) ) * RAYON ) + RAYON,
                        (int) ( Math.sin( Math.toRadians( 225 ) ) * RAYON ) + RAYON
                ),
                new Position(
                        (int) ( Math.cos( Math.toRadians( 315 ) ) * RAYON ) + RAYON,
                        (int) ( Math.sin( Math.toRadians( 315 ) ) * RAYON ) + RAYON
                )
        };
        Position[] circleCenters = new Position[]{
                new Position(
                        ( positions[ 0 ].getX() + positions[ 1 ].getX() ) / 2,
                        ( positions[ 0 ].getY() + positions[ 1 ].getY() ) / 2 ),
                new Position(
                        ( positions[ 1 ].getX() + positions[ 2 ].getX() ) / 2,
                        ( positions[ 1 ].getY() + positions[ 2 ].getY() ) / 2 ),
                new Position(
                        ( positions[ 2 ].getX() + positions[ 3 ].getX() ) / 2,
                        ( positions[ 2 ].getY() + positions[ 3 ].getY() ) / 2 ),
                new Position(
                        ( positions[ 3 ].getX() + positions[ 0 ].getX() ) / 2,
                        ( positions[ 3 ].getY() + positions[ 0 ].getY() ) / 2 ),
        };

        Position circleCenter = new Position(
                ( positions[ 0 ].getX() + positions[ 1 ].getX() ) / 2,
                ( positions[ 0 ].getY() + positions[ 1 ].getY() ) / 2 );
        Position b = new Position( RAYON, RAYON );
        int diametreIntraCercle = (int) Math.sqrt( Math.pow( b.getX() - circleCenter.getX(), 2 ) + Math.pow( b.getY() - circleCenter.getY(), 2 ) ) * 2;
        int[] startAngles = new int[]{ 0, 270, 180, 90 };
        for( int i = 0; i < 4; i++ ) {
            Position position = circleCenters[ i ];
            int startAngle = startAngles[ i ];
            ArcConfig subConfig = new ArcConfig()
                    .withDiameter( diametreIntraCercle )
                    .withPosX( position.getX() )
                    .withPosY( position.getY() )
                    .withStartAngle( startAngle )
                    .withAngle( 180 );
//            arcTracer.trace(
//                    image,
//                    subConfig
//            );
            new FigureRepartition().fillArc( image, subConfig.withAngle( 67 ), new CircleConfig().withDiameter( TocTracer.TROU ), 8 );
            new FigureRepartition().fillArc( image, subConfig.withAngle( (int) (67 - 121.0/16) ).withStartAngle( startAngle+121 ), new CircleConfig().withDiameter( TocTracer.TROU ), 7 );
        }
    }
}
