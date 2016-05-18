package com.malinkang.animationsample;

import android.animation.TypeEvaluator;

/**
 * 因为系统将完全无法知道如何从初始对象过度到结束对象，
 * 因此这个时候我们就需要实现一个自己的TypeEvaluator来告知系统如何进行过度。
 */
public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }

}