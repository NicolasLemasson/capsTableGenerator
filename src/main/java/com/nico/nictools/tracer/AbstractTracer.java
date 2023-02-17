package com.nico.nictools.tracer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by nico on 11/03/18.
 */
public abstract class AbstractTracer<T> {

    public abstract void trace( Graphics image, TraceConfig<T> config );
}
