package com.nico.nictools.tracer;

import com.nico.nictools.tracer.arc.ArcConfig;
import com.nico.nictools.tracer.arc.ArcTracer;
import com.nico.nictools.tracer.circle.CircleConfig;
import com.nico.nictools.tracer.circle.CircleTracer;
import com.nico.nictools.tracer.circle.FilledCircleTracer;
import com.nico.nictools.tracer.complex.ButterflyTracer;
import com.nico.nictools.tracer.complex.EndTracer;
import com.nico.nictools.tracer.complex.RoseTracer;
import com.nico.nictools.tracer.complex.SixPlayersTracer;
import com.nico.nictools.tracer.complex.StartTracer;
import com.nico.nictools.tracer.drawer.FigureRepartition;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by nico on 11/03/18.
 */
public class TocTracer {
    public static int DIAMETER = 1200;
    static int RAYON = DIAMETER / 2;
    public static int TROU = 47;
    private CircleTracer circleTracer = new CircleTracer();
    private FilledCircleTracer filledCircleTracer = new FilledCircleTracer();
    private RoseTracer roseTracer = new RoseTracer();
    private ArcTracer arcTracer = new ArcTracer();
    private ButterflyTracer butterflyTracer = new ButterflyTracer();
    private EndTracer endTracer = new EndTracer();
    private FigureRepartition figureRepartition = new FigureRepartition();
    private StartTracer startTracer = new StartTracer();
    private SixPlayersTracer sixPlayersTracer = new SixPlayersTracer();

    public static void main( String[] args ) throws IOException {
        TocTracer tocTracer = new TocTracer();
//        tocTracer.traceButterFly();
//
        tocTracer.traceCandy();

    }

    private void traceCandy( ) throws IOException {
        BufferedImage bufferedImage = new BufferedImage( DIAMETER * 2, DIAMETER, BufferedImage.TYPE_INT_ARGB );
        Graphics graphics = initialize( bufferedImage );
        CircleConfig initialCircle = new CircleConfig().withPosX( RAYON ).withPosY( RAYON ).withDiameter( DIAMETER );
        sixPlayersTracer.trace( graphics, initialCircle );

        CircleConfig circleConfig = new CircleConfig( RAYON, RAYON, (int) ( DIAMETER / Math.PI ) + 1 );
        filledCircleTracer.trace(
                graphics,
                circleConfig
        );
        circleConfig = circleConfig.withPosX( circleConfig.getPosX() + 1200 );
        circleConfig = circleConfig.withPosY( circleConfig.getPosY() + 1200 );
        endTracer.trace( graphics, new CircleConfig().withDiameter( TROU ) );
        startTracer.trace( graphics, new CircleConfig().withDiameter( TROU ) );

        File outputFile = new File( "toc-6j.png" );
        ImageIO.write( bufferedImage, "png", outputFile );

    }

    private void traceButterFly( ) throws IOException {
        BufferedImage bufferedImage = new BufferedImage( DIAMETER, DIAMETER, BufferedImage.TYPE_INT_ARGB );
        Graphics graphics = initialize( bufferedImage );

        CircleConfig initialCircle = new CircleConfig().withPosX( RAYON ).withPosY( RAYON ).withDiameter( DIAMETER );
//        circleTracer.trace( graphics, initialCircle );
        butterflyTracer.trace( graphics, initialCircle );

        filledCircleTracer.trace( graphics, new CircleConfig( RAYON, RAYON, (int) ( DIAMETER / Math.PI ) ) );

        endTracer.trace( graphics, new CircleConfig().withDiameter( TROU ) );

        startTracer.trace( graphics, new CircleConfig().withDiameter( TROU ) );
//        figureRepartition.fillArc(
//                graphics,
//                arc,
//                new CircleConfig().withDiameter( 50 ),
//                7
//        );

        File outputFile = new File( "toc.png" );
        ImageIO.write( bufferedImage, "png", outputFile );
    }

    public void traceRosace( ) throws IOException {
        // trace circle
        BufferedImage bufferedImage = new BufferedImage( DIAMETER, DIAMETER, BufferedImage.TYPE_INT_ARGB );
        Graphics graphics = initialize( bufferedImage );

        CircleConfig initialCircle = new CircleConfig().withPosX( RAYON ).withPosY( RAYON ).withDiameter( DIAMETER );
        // trace initial circle
        circleTracer.trace( graphics, initialCircle );
        // trace rosace in it
        roseTracer.trace( graphics, initialCircle );
        // fill center
        filledCircleTracer.trace(
                graphics,
                new CircleConfig( RAYON, RAYON, (int) ( DIAMETER / Math.PI ) )
        );

        File outputfile = new File( "toc.png" );
        ImageIO.write( bufferedImage, "png", outputfile );
    }

    private Graphics initialize( BufferedImage bufferedImage ) {
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor( Color.BLACK );
        graphics.fillRect( 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight() );
        graphics.setColor( Color.WHITE );
        return graphics;
    }

}
