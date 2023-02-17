package com.nico.nictools.tracer;

import com.nico.nictools.tracer.arc.ArcConfig;
import com.nico.nictools.tracer.circle.CircleConfig;

/**
 * Created by nico on 11/03/18.
 */
public interface TraceConfig<T> {

    default T getConfig( ) {
        return (T) this;
    }

}
