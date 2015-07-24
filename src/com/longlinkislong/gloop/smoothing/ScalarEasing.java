/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.longlinkislong.gloop.smoothing;

/**
 * A functional interface that represents an easing function between one double
 * and another.
 *
 * @author zmichaels
 * @since 15.07.24
 */
@FunctionalInterface
public interface ScalarEasing {

    /**
     * Eases from the starting value to (start + delta) within the time frame.
     *
     * @param time the current time value.
     * @param start the starting value.
     * @param delta the change in value between the start and the end.
     * @param duration the time the animation occurs over.
     * @return the interpolated value.
     * @since 15.07.24
     */
    double ease(double time, double start, double delta, double duration);
}
