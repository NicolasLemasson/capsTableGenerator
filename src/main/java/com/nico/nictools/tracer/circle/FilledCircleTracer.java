package com.nico.nictools.tracer.circle;

import com.nico.nictools.tracer.TocTracer;
import com.nico.nictools.tracer.TraceConfig;
import com.nico.nictools.tracer.arc.ArcConfig;
import com.nico.nictools.tracer.drawer.FigureRepartition;
import com.nico.nictools.tracer.math.Position;

import java.awt.*;

/**
 * Created by nico on 11/03/18.
 */
public class FilledCircleTracer extends CircleTracer {

    @Override
    public void trace( Graphics image, TraceConfig<CircleConfig> config ) {
        CircleConfig circleConfig = config.getConfig();
        int replacement = circleConfig.getDiameter() / 2;
              /*  image.setColor( Color.BLACK );
        image.fillOval(
                circleConfig.getPosX() - replacement,
                circleConfig.getPosY() - replacement,
                circleConfig.getDiameter(),
                circleConfig.getDiameter()
        );
        image.setColor( Color.WHITE );
        CircleConfig subConfig = new CircleConfig( circleConfig.getPosX(), circleConfig.getPosY(), (int) ( circleConfig.getDiameter() * 0.66 ) );
        new CircleTracer( false ).trace( image, subConfig );

        int subDiameter = subConfig.getDiameter() / 2;
        double decalageX = Math.cos( Math.toRadians( 45 ) ) * subConfig.getDiameter() / 4;
        double decalageY = Math.sin( Math.toRadians( 45 ) ) * subConfig.getDiameter() / 4;

        super.trace(
                image,
                new CircleConfig(
                        (int) ( ( subConfig.getPosX() ) - decalageX ),
//                        (int) ( ( subConfig.getPosY() + subConfig.getDiameter() / 4 )   ),
                        (int) ( ( subConfig.getPosY() ) + decalageY ),
                        (int) subDiameter
                )
//                circleConfig
        );

        new CircleTracer( false ).trace(
                image,
                new CircleConfig(
                        (int) ( subConfig.getPosX() + decalageX ),
                        (int) ( subConfig.getPosY() - decalageY ),
                        (int) ( subConfig.getDiameter() / 2 )
                )
        );

        super.trace(
                image,
                new CircleConfig(
                        (int) ( subConfig.getPosX() + decalageX ),
                        (int) ( subConfig.getPosY() - decalageY ),
                        (int) ( subConfig.getDiameter() / 2 / Math.PI )
                )
        );

        image.setColor( Color.BLACK );
        super.trace(
                image,
                new CircleConfig(
                        (int) (subConfig.getPosX() - decalageX),
                        (int) (subConfig.getPosY() + decalageY),
                        (int) ( subConfig.getDiameter() / 2 / Math.PI )
                )
        );
        image.setColor( Color.WHITE );
*/
        new FigureRepartition().fillSymArc(
                image,
                new ArcConfig()
                        .withPosY( circleConfig.posY - circleConfig.getDiameter() / 2 )
                        .withPosX( circleConfig.posX - circleConfig.getDiameter() / 2 )
                        .withDiameter( circleConfig.getDiameter() )
                        .withAngle( 270 )
                        .withStartAngle( 90 ),
                new CircleConfig()
                        .withDiameter( TocTracer.TROU ),
                3,
                new Position( 1200, 0 )
        );


    }
}
