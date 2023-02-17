package com.nico.nictools.low;

/**
 * Created by nico on 22/03/19.
 */
public class Personne {

    double taille;
    String name;

    public Personne( double taille, String name ) {
        this.taille = taille;
        this.name = name;
    }

    public void direBonjour( Personne personne ){
        System.out.println( "Bonjour " + personne.name );
    }
}
