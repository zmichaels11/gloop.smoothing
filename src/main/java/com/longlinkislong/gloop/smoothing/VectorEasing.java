/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.longlinkislong.gloop.smoothing;

import com.longlinkislong.gloop.GLVec;

/**
 * A functional interface that represents an easing function from one vector to
 * another.
 *
 * @author zmichaels
 * @param <T> the type of vector.
 * @since 15.07.24
 */
@FunctionalInterface
public interface VectorEasing<T extends GLVec> {

    /**
     * Eases from start to (start+delta) in the specified timeframe.
     *
     * @param time the current time.
     * @param start the starting value.
     * @param delta the change in value between the start and the end.
     * @param duration the time frame.
     * @return the interpolated value.
     * @since 15.07.24
     */
    T ease(double time, T start, T delta, double duration);
}
