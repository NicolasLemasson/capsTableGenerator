package com.nico.nictools.low;

/**
 * Created by nico on 22/03/19.
 */
public class DemoLowLow {

    public static void main( String[] args ) {
        Personne p = new Personne( 2.0, "Toto" );
        Personne laura = new Laura();
        Personne nicolas = new Nicolas();

        nicolas.direBonjour( laura );
        laura.direBonjour( nicolas );

    }
}
