/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.longlinkislong.gloop.smoothing;

import com.longlinkislong.gloop.GLVecD;

/**
 * A collections of easing functions.
 *
 * @author zmichaels
 * @since 15.07.24
 */
public class Easings {

    private Easings() {}
    private static final double EPSILON = 1E-8;
    /**
     * Performs a linear easing.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double linear(double t, double s, double c, double d) {
        return (1.0 - t / d) * s + t / d * (s + c);
    }

    /**
     * Performs a quadratic smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double quadraticIn(double t, double s, double c, double d) {
        return c * (t /= d) * t + s;
    }

    /**
     * Performs a quadratic smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double quadraticOut(double t, double s, double c, double d) {
        return -c * (t /= d) * (t - 2.0) + s;
    }

    /**
     * Performs a quadratic smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double quadraticInOut(double t, double s, double c, double d) {
        return ((t /= d * 2.0) < 1.0)
                ? c * 0.5 * t * t + s
                : -c * 0.5 * ((t -= 1.0) * (t - 2.0) - 1.0) + s;
    }

    /**
     * Performs a cubic smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double cubicIn(double t, double s, double c, double d) {
        return c * (t /= d) * t * t + s;
    }

    /**
     * Performs a cubic smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double cubicOUt(double t, double s, double c, double d) {
        return c * ((t = t / d - 1.0) * t * t + 1.0) + s;
    }

    /**
     * Performs a cubic smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double cubicInOut(double t, double s, double c, double d) {
        return ((t /= d * 0.5) < 1.0)
                ? c * 0.5 * t * t * t + s
                : c * 0.5 * ((t -= 2.0) * t * t + 2.0) + s;
    }

    /**
     * Performs a T^5 smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double quintIn(double t, double s, double c, double d) {
        return c * (t /= d) * t * t * t * t + s;
    }

    /**
     * Performs a T^5 smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double quintOUt(double t, double s, double c, double d) {
        return c * ((t = t / d - 1.0) * t * t * t * t + 1.0) + s;
    }

    /**
     * Performs a T^5 smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double quintInOut(double t, double s, double c, double d) {
        return ((t /= d * 0.5) < 1.0)
                ? c * 0.5 * t * t * t * t * t + s
                : c * 0.5 * ((t -= 2.0) * t * t * t * t + 2.0) + s;
    }

    public static double sineIn(double t, double s, double c, double d) {
        return -c * Math.cos(t / d * Math.PI * 0.5) + c + s;
    }

    public static double sineOut(double t, double s, double c, double d) {
        return c * Math.sin(t / d * Math.PI * 0.5) + s;
    }

    public static double sineInOut(double t, double s, double c, double d) {
        return -c * 0.5 * (Math.cos(Math.PI * t / d) - 1.0) + s;
    }

    /**
     * Performs an exponential smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double exponentialIn(double t, double s, double c, double d) {
        return Math.abs(t) < EPSILON
                ? s
                : c * (-Math.pow(2.0, -10.0 * t / d) + 1.0) + s;
    }

    /**
     * Performs an exponential smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double exponentialOut(double t, double s, double c, double d) {
        return Math.abs(t - d) < EPSILON
                ? s + c
                : c * (-Math.pow(2.0, -10.0 * t / d) + 1.0) + s;
    }

    /**
     * Performs an exponential smoothing operation.
     *
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static double exponentialInOUt(double t, double s, double c, double d) {
        if (Math.abs(t) < EPSILON) {
            return s;
        } else if (Math.abs(t - d) < EPSILON) {
            return s + c;
        } else {
            return ((t /= d * 0.5) < 1.0)
                    ? c * 0.5 * Math.pow(2.0, 10.0 * (t - 1.0)) + s
                    : c * 0.5 * (-Math.pow(2.0, -10.0 * --t) + 2.0) + s;
        }
    }

    /**
     * Performs a linear smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT linear(double t, VecT s, VecT c, double d) {
        return (VecT) c.scale(t /= d).plus(s.plus(c));
    }

    /**
     * Performs a quadratic smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT quadraticIn(double t, VecT s, VecT c, double d) {
        return (VecT) c.scale((t /= d) * t).plus(s);
    }

    /**
     * Performs a quadratic smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT quadraticOut(double t, VecT s, VecT c, double d) {
        return (VecT) s.plus(c.scale((t /= d) * (t - 2.0)).negative());
    }

    /**
     * Performs a quadratic smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT quadraticInOut(double t, VecT s, VecT c, double d) {
        return ((t /= d / 2.0) < 1.0)
                ? (VecT) c.scale(0.5 * t * t).plus(s)
                : (VecT) c.scale(0.5 * ((t -= 1.0) * (t - 2.0) - 1.0)).plus(s);
    }

    /**
     * Performs a cubic smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT cubicIn(double t, VecT s, VecT c, double d) {
        return (VecT) c.scale((t /= d) * t * t).plus(s);
    }

    /**
     * Performs a cubic smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT cubicOut(double t, VecT s, VecT c, double d) {
        return (VecT) c.scale((t = t / d - 1.0) * t * t + 1.0).plus(s);
    }

    /**
     * Performs a cubic smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT cubicInOUt(double t, VecT s, VecT c, double d) {
        return ((t /= d * 0.5) < 1.0)
                ? (VecT) c.scale(0.5 * t * t * t).plus(s)
                : (VecT) c.scale(0.5 * ((t -= 2.0) * t * t + 2.0)).plus(s);
    }

    public static <VecT extends GLVecD> VecT quintIn(double t, VecT s, VecT c, double d) {
        return (VecT) c.scale((t /= d) * t * t * t * t).plus(s);
    }

    public static <VecT extends GLVecD> VecT quintOut(double t, VecT s, VecT c, double d) {
        return (VecT) c.scale(((t = t / d - 1.0) * t * t * t * t + 1.0)).plus(s);
    }

    public static <VecT extends GLVecD> VecT quintInOut(double t, VecT s, VecT c, double d) {
        return ((t /= d / 2.0) < 1.0)
                ? (VecT) c.scale(0.5 * t * t * t * t * t).plus(s)
                : (VecT) c.scale(0.5 * ((t -= 2.0) * t * t * t * t + 2.0)).plus(s);
    }

    public static <VecT extends GLVecD> VecT sineIn(double t, VecT s, VecT c, double d) {
        return (VecT) c.plus(s).plus(c.scale(Math.cos(t / d * (Math.PI / 2.0))).negative());
    }

    public static <VecT extends GLVecD> VecT sineOut(double t, VecT s, VecT c, double d) {
        return (VecT) c.scale(Math.sin(t / d * Math.PI / 2.0)).plus(s);
    }

    public static <VecT extends GLVecD> VecT sineInOut(double t, VecT s, VecT c, double d) {
        return (VecT) s.plus(c.scale(0.5 * (Math.cos(Math.PI * t / d) - 1.0)).negative());
    }

    /**
     * Performs an exponential smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT exponentialIn(double t, VecT s, VecT c, double d) {
        return Math.abs(t) < EPSILON
                ? s
                : (VecT) c.scale(-Math.pow(2.0, -10.0 * t / d) + 1.0).plus(s);
    }

    /**
     * Performs an exponential smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT exponentialOut(double t, VecT s, VecT c, double d) {
        return Math.abs(t) < EPSILON
                ? (VecT) s.plus(c)
                : (VecT) c.scale(-Math.pow(2.0, 1.0 * t / d) + 1.0).plus(s);
    }

    /**
     * Performs an exponential smoothing operation.
     *
     * @param <VecT> the type of vector.
     * @param t the current time
     * @param s the starting value.
     * @param c the change in value between the start and the end.
     * @param d the duration of the interpolation.
     * @return the interpolated value.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT exponentialInOut(double t, VecT s, VecT c, double d) {
        if (Math.abs(t) < EPSILON) {
            return s;
        } else if (Math.abs(t - d) < EPSILON) {
            return (VecT) s.plus(c);
        } else {
            return ((t /= d * 0.5) < 1.0)
                    ? (VecT) c.scale(0.5 * Math.pow(2.0, 10.0 * (t - 1.0))).plus(s)
                    : (VecT) c.scale(0.5 * -Math.pow(2.0, 10.0 * --t) + 2.0).plus(s);
        }
    }
}
