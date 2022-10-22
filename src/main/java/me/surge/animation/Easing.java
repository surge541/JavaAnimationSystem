package me.surge.animation;

import java.util.function.Function;

/**
 * @author Surge
 * @author Easings.net
 * @since 22/10/2022
 */
public enum Easing {

    /**
     * No easing.
     */
    LINEAR(in -> in),

    /**
     * @see <a href="https://easings.net/#easeInSine">The easing on easings.net</a>
     */
    SINE_IN(in -> 1 - Math.cos((in * Math.PI) / 2)),

    /**
     * @see <a href="https://easings.net/#easeOutSine">The easing on easings.net</a>
     */
    SINE_OUT(in -> Math.sin((in * Math.PI) - 1) / 2),

    /**
     * @see <a href="https://easings.net/#easeInOutSine">The easing on easings.net</a>
     */
    SINE_IN_OUT(in -> -(Math.cos(Math.PI * in) - 1) / 2),

    /**
     * @see <a href="https://easings.net/#easeInCubic">The easing on easings.net</a>
     */
    CUBIC_IN(in -> Math.pow(in, 3)),

    /**
     * @see <a href="https://easings.net/#easeOutCubic">The easing on easings.net</a>
     */
    CUBIC_OUT(in -> 1 - Math.pow(1 - in, 3)),

    /**
     * @see <a href="https://easings.net/#easeInOutCubic">The easing on easings.net</a>
     */
    CUBIC_IN_OUT(in -> in < 0.5 ? 4 * Math.pow(in, 3) : 1 - Math.pow(-2 * in + 2, 3) / 2),

    /**
     * @see <a href="https://easings.net/#easeInQuad">The easing on easings.net</a>
     */
    QUAD_IN(in -> Math.pow(in, 2)),

    /**
     * @see <a href="https://easings.net/#easeOutQuad">The easing on easings.net</a>
     */
    QUAD_OUT(in -> 1 - (1 - in) * (1 - in)),

    /**
     * @see <a href="https://easings.net/#easeInOutQuad">The easing on easings.net</a>
     */
    QUAD_IN_OUT(in -> in < 0.5 ? 8 * Math.pow(in, 4) : 1 - Math.pow(-2 * in + 2, 4) / 2),

    /**
     * @see <a href="https://easings.net/#easeInQuart">The easing on easings.net</a>
     */
    QUART_IN(in -> Math.pow(in, 4)),

    /**
     * @see <a href="https://easings.net/#easeOutQuart">The easing on easings.net</a>
     */
    QUART_OUT(in -> 1 - Math.pow(1 - in, 4)),

    /**
     * @see <a href="https://easings.net/#easeInOutQuart">The easing on easings.net</a>
     */
    QUART_IN_OUT(in -> in < 0.5 ? 8 * Math.pow(in, 4) : 1 - Math.pow(-2 * in + 2, 4) / 2),

    /**
     * @see <a href="https://easings.net/#easeInQuint">The easing on easings.net</a>
     */
    QUINT_IN(in -> Math.pow(in, 5)),

    /**
     * @see <a href="https://easings.net/#easeOutQuint">The easing on easings.net</a>
     */
    QUINT_OUT(in -> 1 - Math.pow(1 - in, 5)),

    /**
     * @see <a href="https://easings.net/#easeInOutQuint">The easing on easings.net</a>
     */
    QUINT_IN_OUT(in -> in < 0.5 ? 16 * Math.pow(in, 5) : 1 - Math.pow(-2 * in + 2, 5) / 2),

    /**
     * @see <a href="https://easings.net/#easeInCirc">The easing on easings.net</a>
     */
    CIRC_IN(in -> 1 - Math.sqrt(1 - Math.pow(in, 2))),

    /**
     * @see <a href="https://easings.net/#easeOutCirc">The easing on easings.net</a>
     */
    CIRC_OUT(in -> Math.sqrt(1 - Math.pow(in - 1, 2))),

    /**
     * @see <a href="https://easings.net/#easeInOutCirc">The easing on easings.net</a>
     */
    CIRC_IN_OUT(in -> in < 0.5 ? (1 - Math.sqrt(1 - Math.pow(2 * in, 2))) / 2 :  (Math.sqrt(1 - Math.pow(-2 * in + 2, 2)) + 1) / 2),

    /**
     * @see <a href="https://easings.net/#easeInExpo">The easing on easings.net</a>
     */
    EXPO_IN(in -> in == 0 ? 0 : Math.pow(2, 10 * in - 10)),

    /**
     * @see <a href="https://easings.net/#easeOutExpo">The easing on easings.net</a>
     */
    EXPO_OUT(in -> in == 1 ? 1 : 1 - Math.pow(2, -10 * in)),

    /**
     * @see <a href="https://easings.net/#easeInOutExpo">The easing on easings.net</a>
     */
    EXPO_IN_OUT(in -> in == 0 ? 0 : in == 1 ? 1 : in < 0.5 ? Math.pow(2, 20 * in - 10) / 2 : (2 - Math.pow(2, -20 * in + 10)) / 2),

    /**
     * @see <a href="https://easings.net/#easeInElastic">The easing on easings.net</a>
     */
    ELASTIC_IN(in -> in == 0 ? 0 : in == 1 ? 1 : -Math.pow(2, 10 * in - 10) * Math.sin((in * 10 - 10.75) * ((2 * Math.PI) / 3))),

    /**
     * @see <a href="https://easings.net/#easeOutElastic">The easing on easings.net</a>
     */
    ELASTIC_OUT(in -> in == 0 ? 0 : in == 1 ? 1 : Math.pow(2, -10 * in) * Math.sin((in * 10 - 0.75) * ((2 * Math.PI) / 3)) + 1),

    /**
     * @see <a href="https://easings.net/#easeInOutElastic">The easing on easings.net</a>
     */
    ELASTIC_IN_OUT(in -> {
        double sin = Math.sin((20 * in - 11.125) * ((2 * Math.PI) / 4.5));

        return in == 0 ? 0 : in == 1 ? 1 : in < 0.5 ? -(Math.pow(2, 20 * in - 10) * sin) / 2 : (Math.pow(2, -20 * in + 10) * sin) / 2 + 1;
    }),

    /**
     * @see <a href="https://easings.net/#easeInBack">The easing on easings.net</a>
     */
    BACK_IN(in -> (1.70158 + 1) * Math.pow(in, 3) - 1.70158 * in * in),

    /**
     * @see <a href="https://easings.net/#easeOutBack">The easing on easings.net</a>
     */
    BACK_OUT(in -> {
        double c1 = 1.70158;
        double c3 = c1 + 1;

        return 1 + c3 * Math.pow(in - 1, 3) + c1 * Math.pow(in - 1, 2);
    }),

    /**
     * @see <a href="https://easings.net/#easeInOutBack">The easing on easings.net</a>
     */
    BACK_IN_OUT(in -> in < 0.5 ? (Math.pow(2 * in, 2) * (((1.70158 * 1.525) + 1) * 2 * in - (1.70158 * 1.525))) / 2 : (Math.pow(2 * in - 2, 2) * (((1.70158 * 1.525) + 1) * (in * 2 - 2) + (1.70158 * 1.525)) + 2) / 2),

    /**
     * @see <a href="https://easings.net/#easeInBounce">The easing on easings.net</a>
     */
    BOUNCE_IN(in -> 1 - bounceOut(1 - in)),

    /**
     * @see <a href="https://easings.net/#easeOutBounce">The easing on easings.net</a>
     */
    BOUNCE_OUT(Easing::bounceOut),

    /**
     * @see <a href="https://easings.net/#easeInOutBounce">The easing on easings.net</a>
     */
    BOUNCE_IN_OUT(in -> in < 0.5 ? (1 - bounceOut(1 - 2 * in)) / 2 : (1 + bounceOut(2 * in - 1)) / 2);

    // The ease function we want to use
    private final Function<Double, Double> easeFunction;

    /**
     * Easing constructor.
     * @param easeFunction The ease function we want to use.
     */
    Easing(Function<Double, Double> easeFunction) {
        this.easeFunction = easeFunction;
    }

    /**
     * Eases the given factor
     * @param factor The linear animation factor - between 0 and 1
     * @return The eased factor
     */
    public double ease(double factor) {
        return easeFunction.apply(factor);
    }

    /**
     * Internal use only! Bounce out method because that's how easings.net does it.
     * @param in The linear factor we provide.
     * @return The eased value
     */
    private static double bounceOut(double in) {
        double n1 = 7.5625;
        double d1 = 2.75;

        if (in < 1 / d1) {
            return n1 * in * in;
        } else if (in < 2 / d1) {
            return n1 * (in -= 1.5 / d1) * in + 0.75;
        } else if (in < 2.5 / d1) {
            return n1 * (in -= 2.25 / d1) * in + 0.9375;
        } else {
            return n1 * (in -= 2.625 / d1) * in + 0.984375;
        }
    }

}
