package com.example.blogcode.effectivejava.item17;

import lombok.Getter;

import java.math.BigInteger;

/**
 * packageName    : com.example.blogcode.effectivejava.item17
 * fileName       : Complex
 * author         : tkdwk567@naver.com
 * date           : 2022/07/21
 */

@Getter
public final class Complex {
    private final double re;
    private final double im;

    public static final Complex ONE = new Complex(1, 1);
    public static final Complex TWO = new Complex(2, 2);
    public static final Complex THREE = new Complex(3, 3);
    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }

    public Complex plus(Complex c) {
        return new Complex(this.re + c.re, this.im + c.im);
    }

    public BigInteger safeInstance(BigInteger val) {
        return val.getClass() == BigInteger.class ?
                val : new BigInteger(val.toByteArray());
    }
}
