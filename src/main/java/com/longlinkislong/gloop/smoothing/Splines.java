/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.longlinkislong.gloop.smoothing;

import com.longlinkislong.gloop.GLVecD;
import com.longlinkislong.gloop.Vectors;
import static com.longlinkislong.gloop.smoothing.SmoothingUtils.N;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import java.util.List;

/**
 * A collection of implemented spline functions.
 *
 * @author zmichaels
 * @since 15.07.24
 */
public class Splines {

    private Splines() {
    }

    /**
     * Interpolates a point across the collection of points using a Bezier
     * curve.
     *
     * @param <VecT> the type of vector.
     * @param t the time step on range [0, 1)
     * @param points the points to interpolate across.
     * @return the interpolated point.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT bezier(final double t, final List<VecT> points) {
        final int size = points.size();
        final double[] m = new double[size];
        double mSum = 0.0;

        for (int i = 0; i < size; i++) {
            m[i] = SmoothingUtils.binomialCoeff(size, i) * pow((1.0 - t), size - i) * pow(t, i);
            mSum += m[i];
        }

        final int vSize = points.get(0).size();
        GLVecD<?> out = (GLVecD<?>) points.get(0).copyTo(Vectors.DEFAULT_FACTORY).zero();

        for (int i = 0; i < size; i++) {
            final VecT v = points.get(i);

            for (int j = 0; j < vSize; j++) {
                out = out.plus(v.scale(m[i]));
            }
        }

        out.scale(1.0 / mSum);

        return (VecT) out;
    }

    /**
     * Evaluates a Bessel-Overhauser spline. A Bessel-Overhauser spline
     * functions similarly to a Catmull-Roll spline in that it will pass through
     * all of the points. The Bessel-Overhauser spline may overshoot when points
     * are too close together.
     *
     *
     * @param <VecT> the type of vector.
     *
     * @param t The time step value on range [0, 1)
     * @param points the list of points.
     * @return the interpolated point.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> VecT besselOverhauser(final double t, final List<VecT> points) {
        final int size = points.size();
        final int[] index = new int[4];
        double iT = t * (size - 1.0);

        index[1] = (int) iT;
        index[0] = min(max(index[1] - 1, 0), size - 1);
        index[2] = min(index[1] + 1, size - 1);
        index[3] = min(index[1] + 2, size - 1);

        iT -= index[1];

        VecT v0 = points.get(index[0]);
        VecT v1 = points.get(index[1]);
        VecT v2 = points.get(index[2]);

        GLVecD<?> p1mp0 = v1.minus(v0);
        GLVecD<?> p2mp1 = v2.minus(v1);
        double dm = p1mp0.length();
        double dp = p2mp1.length();
        GLVecD<?> vp = p2mp1.scale(1.0 / dp);
        GLVecD<?> vm = p1mp0.scale(1.0 / dm);
        GLVecD<?> v = (vm.scale(dp).plus(vp.scale(dm))).scale(1.0 / (dp + dm));

        GLVecD<?> v3 = v1.minus(v.scale(dm / 3.0));
        GLVecD<?> v4 = v1;
        GLVecD<?> v5 = v1.plus(v.scale(dp / 3.0));

        v0 = points.get(index[1]);
        v1 = points.get(index[2]);
        v2 = points.get(index[3]);

        p2mp1 = v1.minus(v0);
        GLVecD<?> p3mp2 = v2.minus(v1);
        dm = p2mp1.length();
        dp = p3mp2.length();
        vp = p3mp2.scale(1.0 / dp);
        vm = p2mp1.scale((1.0 / dm));
        v = vm.scale(dp).plus(vp.scale(dm)).scale(1.0 / (dp + dm));

        GLVecD<?> v6 = v1.minus(v.scale(dm / 3.0));
        GLVecD<?> v7 = v1;
        GLVecD<?> v8 = v1.plus(v.scale(dp / 3.0));

        final double u = 2.0 + (iT * 4.0);
        GLVecD<?> out = (GLVecD<?>) v0.copyTo(Vectors.DEFAULT_FACTORY).zero();

        out = out.plus(v3.scale(N(0, 3, u)));
        out = out.plus(v4.scale(N(1, 3, u)));
        out = out.plus(v5.scale(N(2, 3, u)));
        out = out.plus(v6.scale(N(3, 3, u)));
        out = out.plus(v7.scale(N(4, 3, u)));
        out = out.plus(v8.scale(N(5, 3, u)));

        return (VecT) out;
    }

    /**
     * Generates a BSpline
     *
     * @param <VecT> the type of vector.
     * @param degrees the degree of the polynomial.
     * @return the spline function.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> Spline<VecT> getBSpline(int degrees) {
        if (degrees < 3) {
            throw new ArithmeticException("BSpline requires a polynomial with minimal degrees = 3!");
        }

        return (double t, List<VecT> points) -> {
            final int size = points.size();
            final double u = (degrees - 1.0) + (t * (size - (degrees - 1.0)));
            GLVecD<?> out = (GLVecD<?>) points.get(0).copyTo(Vectors.DEFAULT_FACTORY).zero();

            for (int i = 0; i < size; i++) {
                out = out.plus(points.get(i).scale(N(i, degrees, u)));
            }

            return (VecT) out;
        };
    }

    /**
     * Generates a TCB spline (Kochanek-Bartels). THis will evaluate a cubic
     * Hermite spline with tension, continuity, and bias parameters that
     * influence the tangents of each knot. A value of t=c=b=0 will result in a
     * Catmul-Rom spline.
     *
     * @param <VecT> the type of vector.
     * @param t the tension value; changes the length of the tangent vector.
     * @param c the continuity value; changes the sharpness in change between
     * tangents.
     * @param b the bias value; changes the direction of the tangent vector.
     *
     * @return the TCB spline.
     * @since 15.07.24
     */
    public static <VecT extends GLVecD> Spline<VecT> genTCBSpline(final double t, final double c, final double b) {
        return (double timestep, List<VecT> points) -> {
            final int size = points.size();
            final int[] index = new int[4];
            double iT = timestep * (size - 1.0);

            index[1] = min((int) iT, size - 1);
            index[0] = min(max(0, index[1] - 1), size - 1);
            index[2] = min(index[1] + 1, size - 1);
            index[3] = min(index[1] + 2, size - 1);

            iT -= index[1];
            GLVecD<?> v0 = points.get(index[0]);
            GLVecD<?> v1 = points.get(index[1]);
            GLVecD<?> v2 = points.get(index[2]);

            GLVecD<?> I = (v1.minus(v0)).scale((1.0 - t) * (1.0 + b) * (1.0 + c) / 6.0).plus((v2.minus(v1)).scale((1.0 - t) * (1.0 - b) * (1.0 - c) / 6.0));
            GLVecD<?> v3 = v1.minus(I);
            GLVecD<?> v4 = v1;
            GLVecD<?> v5 = v1.plus(I);

            v0 = points.get(index[1]);
            v1 = points.get(index[2]);
            v2 = points.get(index[3]);

            I = (v1.minus(v0)).scale((1.0 - t) * (1.0 + b) * (1.0 - c) / 6.0).plus((v2.minus(v1)).scale((1.0 - t) * (1.0 - b) * (1.0 + c) / 6.0));
            GLVecD<?> v6 = v1.minus(I);
            GLVecD<?> v7 = v1;
            GLVecD<?> v8 = v1.plus(I);

            final double u = 2.0 + (iT * 4.0);
            GLVecD<?> out = (GLVecD<?>) points.get(0).copyTo(Vectors.DEFAULT_FACTORY).zero();

            out = out.plus(v3.scale(N(0, 3, u)));
            out = out.plus(v4.scale(N(1, 3, u)));
            out = out.plus(v5.scale(N(2, 3, u)));
            out = out.plus(v6.scale(N(3, 3, u)));
            out = out.plus(v7.scale(N(4, 3, u)));
            out = out.plus(v8.scale(N(5, 3, u)));

            return (VecT) out;
        };
    }
}
