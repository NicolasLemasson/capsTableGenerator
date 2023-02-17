package com.nico.nictools.tracer.math;

import com.nico.nictools.tracer.circle.CircleConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nico on 11/03/18.
 */
public class MathHelper {

    public List<Position> getDividedCirclePositions( CircleConfig circleConfig, int parts ) {
        List<Position> positions = new ArrayList<>( parts );
        int angle = 360 / parts;
        for( int i = 0; i < parts; i++ ) {
            int startAngle = i * angle;
            positions.add( new Position(
                    (int) ( Math.cos( Math.toRadians( startAngle ) ) * circleConfig.getDiameter()/2 ),
                    (int) ( Math.sin( Math.toRadians( startAngle ) ) * -circleConfig.getDiameter()/2  )
            ) );
        }
        return positions;
    }

}
