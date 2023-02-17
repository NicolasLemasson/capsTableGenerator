package com.nico.nictools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by nico on 11/01/18.
 * TODO Remplacement de la caps courante ? ( appuie sur R ?  + click sur le coté ?)
 */

public class ScrollImageTest extends JPanel {
    private static final long serialVersionUID = 1L;
    private JScrollPane sp;
    int nbImageCaps = 17;
    int currentCaps = 2;
    TableHandler tableHandler;

    public static void main( String[] args ) {
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run( ) {
                try {
                    JPanel p = null;
                    p = new ScrollImageTest();
                    JFrame f = new JFrame();
                    f.setContentPane( p );
                    f.setSize( 400, 300 );
                    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                    f.setVisible( true );
                } catch( Exception e ) {
                    e.printStackTrace();
                }
            }
        } );
    }

    public ScrollImageTest( ) throws IOException {
        JPanel canvas = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent( Graphics g ) {
                super.paintComponent( g );
                g.drawImage( tableHandler.getImage(), 0, 0, null );
            }
        };
        tableHandler = new TableHandler( canvas );
        tableHandler.loadCaps( new File( Thread.currentThread().getContextClassLoader().getResource( "1664.png" ).getPath() ).getParent() );

        try {
            testTableMultipleCircles();
        } catch( Exception e ) {
            e.printStackTrace();
        }


        canvas.setFocusable( true );
        canvas.requestFocusInWindow();

        canvas.addKeyListener( new KeyListener() {
            @Override
            public void keyTyped( KeyEvent keyEvent ) {

            }

            @Override
            public void keyPressed( KeyEvent keyEvent ) {
                tableHandler.keyPressed( keyEvent );
            }

            //  940, 174,
            @Override
            public void keyReleased( KeyEvent keyEvent ) {

            }
        } );

        canvas.addMouseListener( new MouseListener() {
            @Override
            public void mouseClicked( MouseEvent mouseEvent ) {
                int xA = mouseEvent.getX();
                int yA = mouseEvent.getY();
                tableHandler.clickAt( xA, yA );
            }

            @Override
            public void mousePressed( MouseEvent mouseEvent ) {

            }

            @Override
            public void mouseReleased( MouseEvent mouseEvent ) {

            }

            @Override
            public void mouseEntered( MouseEvent mouseEvent ) {

            }

            @Override
            public void mouseExited( MouseEvent mouseEvent ) {

            }
        } );

//        addCapsFromCenter( , , (Graphics2D) image.getGraphics(), caps2 );


//        canvas.add( new JButton( "Currently I do nothing" ) );
        canvas.setPreferredSize( new Dimension( tableHandler.background.getWidth( null ), tableHandler.background.getHeight( null ) ) );
        sp = new JScrollPane( canvas );
        setLayout( new BorderLayout() );
        add( sp, BorderLayout.CENTER );
        tableHandler.printCaps();
        tableHandler.printCurrentCaps();
        tableHandler.printAvailableCaps();
    }


    public void testTableMultipleCircles( ) throws Exception {
        int nbTotalCaps = 0;
        // 130px = 1/2 caps // 260 = une caps
        BufferedImage table = new BufferedImage( 5530, 5530, BufferedImage.TYPE_INT_ARGB );
        BufferedImage caps = ImageIO.read( Thread.currentThread().getContextClassLoader().getResource( "1664.png" ) );
        Graphics2D g = table.createGraphics();
        g.setColor( new Color( 236, 218, 135 ) );
        g.fillOval( 0, 0, 5530, 5530 );
        /// INIT VARS
        double circleDistanceFromCenter = 2765.0 - 130 + 260;
        addCapsFromCenter( 2765, 2765, g, caps );
        for( int c = 0; c < 11; c++ ) {
            circleDistanceFromCenter = circleDistanceFromCenter - 260;

            int x1 = 2765 + (int) circleDistanceFromCenter;
            int y1 = 2765;
//            if( c % 2 == 1 ) {
//                double perimeter = 2 * Math.PI * circleDistanceFromCenter;
//                double angle = ( 130 * ( 360.0 * Math.PI / 180.0 ) ) / perimeter;
//                double x1bis = ( x1 - 2765.0 ) * Math.cos( angle ) - ( y1 - 2765.0 ) * Math.sin( angle );
//                x1 = (int) ( x1bis + 2765 );
//                double y1bis = ( x1 - 2765.0 ) * Math.sin( angle ) + ( y1 - 2765.0 ) * Math.cos( angle );
//                y1 = (int) ( y1bis + 2765 );
//            }
//            addCapsFromCenter( x1, y1, g, caps );
            double circlePerimeter = 2 * Math.PI * circleDistanceFromCenter;
            int nbCaps = (int) ( circlePerimeter / 260.0 );
            if( nbCaps % 2 == 1 ) {
                nbCaps--;
            }
            nbTotalCaps += nbCaps;
            double reste = circlePerimeter - ( nbCaps * 260 );
            System.out.println( nbCaps + " reste " + reste );
            double distBetweenCaps = 260.0 + ( reste / nbCaps );
            double angle = ( distBetweenCaps * ( 360.0 * Math.PI / 180.0 ) ) / circlePerimeter;

            int x2 = x1;
            int y2 = y1;
            double adjustX = 0.0;
            double adjustY = 0.0;
            for( int i = 0; i < nbCaps; i++ ) {
                addCapsFromCenter( x2, y2, g, caps );
                double v1 = ( x1 - 2765.0 ) * Math.cos( angle ) - ( y1 - 2765.0 ) * Math.sin( angle );
                x2 = (int) ( v1 + 2765 );
                adjustX += v1 - ( new Double( x2 ).doubleValue() );
                if( adjustX > 1.0 ) {
                    x2 += (int) adjustX;
                    adjustX -= (int) adjustX;
                }
                double v2 = ( x1 - 2765.0 ) * Math.sin( angle ) + ( y1 - 2765.0 ) * Math.cos( angle );
                y2 = (int) ( v2 + 2765 );
                adjustY += v2 - ( new Double( y2 ).doubleValue() );
                if( adjustY > 1.0 ) {
                    y2 += (int) adjustY;
                    adjustY -= (int) adjustY;
                }

                x1 = x2;
                y1 = y2;
            }

            // 1st circle done
        }


        try {
            // retrieve image
            File outputfile = new File( "saved.png" );
            ImageIO.write( table, "png", outputfile );
        } catch( IOException e ) {
            e.printStackTrace();
        }
        System.out.println( "Total caps: " + nbTotalCaps );
        System.out.println( "running swing GUI" );

    }


    public void addCapsFromCenter( int centerX, int centerY, Graphics2D table, BufferedImage caps ) {
//        table.drawImage( caps, centerX - 130, centerY - 130, null );
        centerX = ( centerX - 130 ) / 8;
        centerY = ( centerY - 130 ) / 8;
        tableHandler.posCaps.add( new PosCaps( centerX, centerY, 0 ) );
        tableHandler.allPos.add( new PosCaps( centerX, centerY, 0 ) );
    }

}