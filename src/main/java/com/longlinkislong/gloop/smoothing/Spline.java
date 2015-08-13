/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.longlinkislong.gloop.smoothing;

import com.longlinkislong.gloop.GLVecD;
import java.util.List;

/**
 *
 * @author zmichaels
 * @param <T>
 */
@FunctionalInterface
public interface Spline <T extends GLVecD>{
    T eval(double t, List<T> points);
}
