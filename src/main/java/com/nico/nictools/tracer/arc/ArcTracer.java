package com.nico.nictools.tracer.arc;

import com.nico.nictools.tracer.AbstractTracer;
import com.nico.nictools.tracer.TocTracer;
import com.nico.nictools.tracer.TraceConfig;

import java.awt.*;

/**
 * Created by nico on 11/03/18.
 */
public class ArcTracer extends AbstractTracer<ArcConfig> {

    @Override
    public void trace( Graphics image, TraceConfig<ArcConfig> config ) {
        ArcConfig arcConfig = config.getConfig();
        int replacement = arcConfig.getDiameter() / 2;
        arcConfig = arcConfig.withPosX( arcConfig.getPosX() - replacement );
        arcConfig = arcConfig.withPosY( arcConfig.getPosY() - replacement );
        image.drawArc(
                arcConfig.getPosX(),
                arcConfig.getPosY(),
                arcConfig.getDiameter(),
                arcConfig.getDiameter(),
                (int)arcConfig.getStartAngle(),
                arcConfig.getAngle()
        );
    }
}
