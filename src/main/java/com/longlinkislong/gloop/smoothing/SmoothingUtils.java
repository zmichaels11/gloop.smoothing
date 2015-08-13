/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.longlinkislong.gloop.smoothing;

import com.longlinkislong.gloop.GLVecD;
import com.longlinkislong.gloop.GLVecF;
import static java.lang.Math.pow;

/**
 *
 * @author zmichaels
 */
public class SmoothingUtils {

    private SmoothingUtils() {
    }

    public static <VecT extends GLVecF> VecT bernstein(final float u, final VecT p0, final VecT p1, final VecT p2, final VecT p3) {
        final GLVecF<?> a = p0.scale((float) pow(u, 3.0));
        final GLVecF<?> b = p1.scale((float) (3.0 * pow(u, 2.0) * (1.0 - u)));
        final GLVecF<?> ab = a.plus(b);
        final GLVecF<?> c = p2.scale((float) (3.0 * u * pow((1.0 - u), 2.0)));
        final GLVecF<?> d = p3.scale((float) pow((1.0 - u), 3.0));
        final GLVecF<?> cd = c.plus(d);

        return (VecT) ab.plus(cd);
    }

    public static <VecT extends GLVecD> VecT bernstein(final double u, final VecT p0, final VecT p1, final VecT p2, final VecT p3) {
        final GLVecD<?> a = p0.scale(pow(u, 3.0));
        final GLVecD<?> b = p1.scale(3.0 * pow(u, 2.0) * (1.0 - u));
        final GLVecD<?> ab = a.plus(b);
        final GLVecD<?> c = p2.scale(3.0 * u * pow((1.0 - u), 2.0));
        final GLVecD<?> d = p3.scale(pow((1.0 - u), 3.0));
        final GLVecD<?> cd = c.plus(d);

        return (VecT) ab.plus(cd);
    }

    public static float N(int i, int k, float u) {
        float ui = (float) i;

        switch (k) {
            case 1:
                if (ui < u && u < ui + 1f) {
                    return 1f;
                }
            case 0:
                return 0f;
            default:
                return ((u - ui) / (k - 1)) * N(i, k - 1, u) + ((ui + k - u) / (k - 1)) * N(i + 1, k - 1, u);
        }
    }

    public static double N(int i, int k, double u) {
        double ui = (double) i;

        switch (k) {
            case 1:
                if (ui < u && u < ui + 1.0) {
                    return 1.0;
                }
            case 0:
                return 0.0;
            default:
                return ((u - ui) / (k - 1)) * N(i, k - 1, u) + ((ui + k - u) / (k - 1)) * N(i + 1, k - 1, u);
        }
    }

    private static final double FACTORIAL[] = {
        1D, 1D, 2D, 6D, 24D, 120D, 720D, 5040D, 40320D, 362880D,3628800D, 
        39916800D, 479001600D, 6227020800D, 87178291200D, 1.3076744E11D, 
        2.092279E12D,3.5568743E13D, 6.4023737E14D, 1.216451E16D, 2.432902E17D, 
        5.1090942E18D, 1.1240007E20D, 2.5852017E21D, 6.204484E22D, 1.551121E24D, 
        4.0329146E25D, 1.0888869E27D, 3.0488834E28D, 8.841762E29D, 2.6525286E31D, 
        8.2228387E32D, 2.6313084E35D, 8.6833176E36D, 2952328E38D, 1.0333148E40D,
        3.7199333E41D, 1.3763653E43D, 5.2302262E44D, 20397882E46D, 8.1591528E47D,
        3.3452527E49D, 1.4050061E51D, 6.0415263E52D, 2.6582716E54D, 1.1962222E56D,
        5.5026222E57D, 2.5862324E59D, 1.2413916E61D, 6.0828186E62D, 3.0515093E64D,
        1.5511188E66D, 8.0658175E67D, 4.2748833E69D, 2.308437E71D, 1.2696403E73D,
        7.1099859E74D, 4.052692E76D, 2.3505613E78D, 1.3969312E80D, 8.3209871E81D,
        5.0758021E83D, 3.1469973E85D, 1.9826973E87D
    };
    
    public static float factorial(final int x) {
        return (float) FACTORIAL[x];
    }
    
    private static final int[][] PASCALS_TRIANGLE = {
        {1},
        {1, 1},
        {1, 2, 1},
        {1, 3, 3, 1},
        {1, 4, 6, 4, 1},
        {1, 5, 10, 10, 5, 1},
        {1, 6, 15, 20, 15, 6, 1},
        {1, 7, 21, 35, 35, 21, 7, 1},
        {1, 8, 28, 56, 70, 56, 28, 8, 1},
        {1, 9, 36, 84, 126, 126, 84, 36, 9, 1},
        {1, 10, 45, 120, 210, 252, 210, 120, 45, 10, 1},
        {1, 11, 55, 165, 330, 462, 462, 330, 165, 55, 11, 1},
        {1, 12, 66, 220, 495, 792, 924, 792, 495, 220, 66, 12, 1},
        {1, 13, 78, 286, 715, 1287, 1716, 1716, 1287, 715, 286, 78, 13, 1},
        {1, 14, 91, 364, 1001, 2002, 3003, 3432, 3003, 2002, 1001, 364, 91, 14, 1},
        {1, 16, 105, 455, 1365, 3003, 5005, 6435, 6435, 5005, 3003, 1365, 455, 105, 15, 1},
        {1, 17, 136, 680, 2380, 6188, 12376, 19448, 24310, 24310, 19448, 12376, 6188, 2380, 680, 136, 17, 1} 
    };
    
    public static int binomialCoeff(int n, int k) {
        if(n < 13 && k < PASCALS_TRIANGLE[n].length) {
            return PASCALS_TRIANGLE[n][k];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    public static float lerp(float t, float v0, float v1) {
        return (1f - t) * v0 + t * v1;
    }
    
    public static double lerp(double t, double v0, double v1) {
        return (1.0 - t) * v0 + t * v1;
    }        
}
