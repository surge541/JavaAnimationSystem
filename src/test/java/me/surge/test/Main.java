package me.surge.test;

import me.surge.animation.Animation;
import me.surge.animation.BoundedAnimation;
import me.surge.animation.ColourAnimation;
import me.surge.animation.Easing;

import java.awt.*;

/**
 * @author Surge
 * @since 22/10/2022
 */
public class Main {

    public static void main(String[] args) {
        // Normal animation
        System.out.println("NORMAL ANIMATION");

        // Create animation object
        Animation animation = new Animation(() -> 200f, false, () -> Easing.LINEAR);

        // Set animation state
        animation.setState(true);

        // Get and print animation factor
        while (animation.getAnimationFactor() < 1.0) {
            System.out.println(animation.getAnimationFactor());
        }



        // Bounded animation
        System.out.println("BOUNDED ANIMATION");

        // Create bounded animation object
        BoundedAnimation bounded = new BoundedAnimation(5f, 20f, 200f, false, Easing.LINEAR);

        // Set animation state
        bounded.setState(true);

        // Get and print animation factor
        while (bounded.getAnimationValue() < bounded.getMaximum()) {
            System.out.println(bounded.getAnimationValue());
        }



        // Colour animation
        System.out.println("COLOUR ANIMATION");

        // Create bounded animation object
        ColourAnimation colourAnimation = new ColourAnimation(Color.RED, Color.BLUE, 200f, false, Easing.LINEAR);

        // Set animation state
        colourAnimation.setState(true);

        // Get and print animation factor
        while (colourAnimation.getAnimationFactor() < 1) {
            // Colour
            Color colour = colourAnimation.getColour();

            System.out.printf("R %d G %d B %d A %d%n", colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha());
        }
    }

}
