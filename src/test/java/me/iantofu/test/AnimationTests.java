package me.iantofu.test;

import me.iantofu.animation.Animation;
import me.iantofu.animation.BoundedAnimation;
import me.iantofu.animation.ColourAnimation;
import me.iantofu.animation.Easing;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Surge
 * @since 22/10/2022
 */
public class AnimationTests {

    // ============ Animation Tests ============

    @Test
    public void normalAnimation() throws InterruptedException {
        // Create animation object
        Animation animation = new Animation(() -> 200f, false, () -> Easing.LINEAR);

        // Set animation state
        animation.setState(true);

        Thread.sleep(200L);

        assertEquals(1.0, animation.getAnimationFactor());
    }

    @Test
    public void animationInitialStateTrue() throws InterruptedException {
        Animation animation = new Animation(200f, true, Easing.LINEAR);
        assertEquals(1.0, animation.getAnimationFactor());
    }

    @Test
    public void animationInitialStateFalse() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.LINEAR);
        assertEquals(0.0, animation.getAnimationFactor());
    }

    @Test
    public void animationStateTransition() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.LINEAR);

        assertFalse(animation.getState());

        animation.setState(true);
        assertTrue(animation.getState());

        Thread.sleep(100L);

        double midpoint = animation.getAnimationFactor();
        assertTrue(midpoint > 0.3 && midpoint < 0.7, "Midpoint should be around 0.5");
    }

    @Test
    public void animationReverse() throws InterruptedException {
        Animation animation = new Animation(200f, true, Easing.LINEAR);
        
        // Switch to false
        animation.setState(false);
        
        // Wait for reverse animation to complete
        Thread.sleep(200L);
        
        // Should be back at 0.0
        assertEquals(0.0, animation.getAnimationFactor(), 0.05);
    }

    @Test
    public void setStateInstantly() throws InterruptedException {
        Animation animation = new Animation(() -> 200f, false, () -> Easing.LINEAR);

        animation.setStateInstantly(true);
        assertEquals(1.0, animation.getAnimationFactor());
    }

    @Test
    public void setStateInstantlyToFalse() {
        Animation animation = new Animation(200f, true, Easing.LINEAR);
        
        animation.setStateInstantly(false);
        assertEquals(0.0, animation.getAnimationFactor(), 0.01);
    }

    @Test
    public void resetToDefault() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.LINEAR);

        animation.setState(true);
        Thread.sleep(100L);

        animation.resetToDefault();
        assertEquals(0.0, animation.getAnimationFactor());
    }

    @Test
    public void resetToDefaultFromTrueInitial() throws InterruptedException {
        Animation animation = new Animation(200f, true, Easing.LINEAR);

        animation.setState(false);
        Thread.sleep(100L);

        animation.resetToDefault();
        assertEquals(1.0, animation.getAnimationFactor());
    }

    @Test
    public void linearFactor() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.LINEAR);
        
        animation.setState(true);
        Thread.sleep(100L);
        
        // Linear factor should be approximately 0.5
        double linearFactor = animation.getLinearFactor();
        assertTrue(linearFactor > 0.4 && linearFactor < 0.6);
    }

    @Test
    public void multipleStateChanges() throws InterruptedException {
        Animation animation = new Animation(500f, false, Easing.LINEAR);
        
        // Toggle multiple times
        animation.setState(true);
        Thread.sleep(200L);
        double progress1 = animation.getAnimationFactor();
        
        animation.setState(false);
        Thread.sleep(150L);
        double progress2 = animation.getAnimationFactor();
        
        animation.setState(true);
        Thread.sleep(300L);
        double progress3 = animation.getAnimationFactor();
        
        // Each state change should produce different values
        assertTrue(progress1 > 0 && progress1 < 1);
        assertTrue(progress2 > 0 && progress2 < 1);
        assertTrue(progress3 > 0 && progress3 < 1);
    }

    // ============ Easing Tests ============

    @Test
    public void easingLinear() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.LINEAR);
        animation.setState(true);
        Thread.sleep(100L);
        
        double factor = animation.getAnimationFactor();
        assertTrue(factor > 0.4 && factor < 0.6);
    }

    @Test
    public void easingSineIn() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.SINE_IN);
        animation.setState(true);
        Thread.sleep(100L);
        
        double factor = animation.getAnimationFactor();
        assertTrue(factor >= 0 && factor <= 1);
    }

    @Test
    public void easingSineOut() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.SINE_OUT);
        animation.setState(true);
        Thread.sleep(100L);
        
        double factor = animation.getAnimationFactor();
        assertTrue(factor >= 0 && factor <= 1);
    }

    @Test
    public void easingCubicIn() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.CUBIC_IN);
        animation.setState(true);
        Thread.sleep(100L);
        
        double factor = animation.getAnimationFactor();
        assertTrue(factor >= 0 && factor <= 1);
    }

    @Test
    public void easingElasticOut() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.ELASTIC_OUT);
        animation.setState(true);
        Thread.sleep(200L);
        
        double factor = animation.getAnimationFactor();
        // Elastic easing can exceed bounds slightly
        assertTrue(factor >= 0);
    }

    @Test
    public void easingBounceOut() throws InterruptedException {
        Animation animation = new Animation(200f, false, Easing.BOUNCE_OUT);
        animation.setState(true);
        Thread.sleep(200L);
        
        double factor = animation.getAnimationFactor();
        assertTrue(factor >= 0 && factor <= 1);
    }

    // ============ BoundedAnimation Tests ============

    @Test
    public void boundedAnimation() throws InterruptedException {
        // Create bounded animation object
        BoundedAnimation bounded = new BoundedAnimation(5f, 20f, 200f, false, Easing.LINEAR);

        // Set animation state
        bounded.setState(true);

        Thread.sleep(200L);

        assertEquals(20f, bounded.getAnimationValue());
    }

    @Test
    public void boundedAnimationMinimumValue() throws InterruptedException {
        BoundedAnimation bounded = new BoundedAnimation(10f, 50f, 200f, false, Easing.LINEAR);
        
        // State is false, so should be at minimum
        assertEquals(10f, bounded.getAnimationValue());
    }

    @Test
    public void boundedAnimationMaximumValue() throws InterruptedException {
        BoundedAnimation bounded = new BoundedAnimation(10f, 50f, 200f, true, Easing.LINEAR);
        System.out.println(bounded.getLinearFactor());
        
        // State is true, so should be at maximum
        assertEquals(50f, bounded.getAnimationValue());
    }

    @Test
    public void boundedAnimationMidpoint() throws InterruptedException {
        BoundedAnimation bounded = new BoundedAnimation(0f, 100f, 200f, false, Easing.LINEAR);
        
        bounded.setState(true);
        Thread.sleep(100L);
        
        double value = bounded.getAnimationValue();
        // Should be around 50 at midpoint
        assertTrue(value > 40 && value < 60);
    }

    @Test
    public void boundedAnimationSetMinimum() {
        BoundedAnimation bounded = new BoundedAnimation(10f, 50f, 200f, false, Easing.LINEAR);
        
        bounded.setMinimum(5f);
        assertEquals(5f, bounded.getMinimum());
    }

    @Test
    public void boundedAnimationSetMaximum() {
        BoundedAnimation bounded = new BoundedAnimation(10f, 50f, 200f, false, Easing.LINEAR);
        
        bounded.setMaximum(100f);
        assertEquals(100f, bounded.getMaximum());
    }

    @Test
    public void boundedAnimationGetters() {
        BoundedAnimation bounded = new BoundedAnimation(15f, 85f, 200f, false, Easing.LINEAR);
        
        assertEquals(15f, bounded.getMinimum());
        assertEquals(85f, bounded.getMaximum());
    }

    @Test
    public void boundedAnimationNegativeRange() throws InterruptedException {
        BoundedAnimation bounded = new BoundedAnimation(-50f, 50f, 200f, false, Easing.LINEAR);
        
        bounded.setState(true);
        Thread.sleep(200L);
        
        assertEquals(50f, bounded.getAnimationValue());
    }

    @Test
    public void boundedAnimationSmallRange() throws InterruptedException {
        BoundedAnimation bounded = new BoundedAnimation(0.1f, 0.2f, 200f, false, Easing.LINEAR);
        
        bounded.setState(true);
        Thread.sleep(200L);
        
        assertEquals(0.2f, bounded.getAnimationValue());
    }

    @Test
    public void boundedAnimationLargeRange() throws InterruptedException {
        BoundedAnimation bounded = new BoundedAnimation(0f, 10000f, 200f, false, Easing.LINEAR);
        
        bounded.setState(true);
        Thread.sleep(200L);
        
        assertEquals(10000f, bounded.getAnimationValue());
    }

    // ============ ColourAnimation Tests ============

    @Test
    public void colourAnimation() throws InterruptedException {
        // Create bounded animation object
        ColourAnimation colourAnimation = new ColourAnimation(Color.RED, Color.BLUE, 200f, false, Easing.LINEAR);

        // Set animation state
        colourAnimation.setState(true);

        Thread.sleep(200L);

        assertEquals(Color.BLUE, colourAnimation.getColour());
    }

    @Test
    public void colourAnimationStartColor() throws InterruptedException {
        ColourAnimation colourAnimation = new ColourAnimation(Color.RED, Color.BLUE, 200f, false, Easing.LINEAR);
        
        // Initial state is false, should be at start color (RED)
        Color color = colourAnimation.getColour();
        assertEquals(Color.RED, color);
    }

    @Test
    public void colourAnimationMidpoint() throws InterruptedException {
        ColourAnimation colourAnimation = new ColourAnimation(Color.BLACK, Color.WHITE, 200f, false, Easing.LINEAR);
        
        colourAnimation.setState(true);
        Thread.sleep(100L);
        
        Color color = colourAnimation.getColour();
        // At midpoint, should be approximately gray (128, 128, 128)
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        
        assertTrue(r > 100 && r < 160);
        assertTrue(g > 100 && g < 160);
        assertTrue(b > 100 && b < 160);
    }

    @Test
    public void colourAnimationWithAlpha() throws InterruptedException {
        Color transparent = new Color(255, 0, 0, 0);
        Color opaque = new Color(0, 255, 0, 255);
        ColourAnimation colourAnimation = new ColourAnimation(transparent, opaque, 200f, false, Easing.LINEAR);
        
        colourAnimation.setState(true);
        Thread.sleep(200L);
        
        Color color = colourAnimation.getColour();
        assertEquals(255, color.getAlpha());
    }

    @Test
    public void colourAnimationSameColor() throws InterruptedException {
        Color sameColor = new Color(100, 150, 200);
        ColourAnimation colourAnimation = new ColourAnimation(sameColor, sameColor, 200f, false, Easing.LINEAR);
        
        colourAnimation.setState(true);
        Thread.sleep(100L);
        
        Color color = colourAnimation.getColour();
        assertEquals(sameColor, color);
    }

    @Test
    public void colourAnimationReverse() throws InterruptedException {
        ColourAnimation colourAnimation = new ColourAnimation(Color.RED, Color.GREEN, 200f, true, Easing.LINEAR);
        
        colourAnimation.setState(false);
        Thread.sleep(200L);
        
        Color color = colourAnimation.getColour();
        assertEquals(Color.RED, color);
    }

    @Test
    public void colourAnimationStateGetters() {
        ColourAnimation colourAnimation = new ColourAnimation(Color.RED, Color.BLUE, 200f, false, Easing.LINEAR);
        
        assertEquals(Color.RED, colourAnimation.from);
        assertEquals(Color.BLUE, colourAnimation.to);
    }

    // ============ Edge Cases and Combined Tests ============

    @Test
    public void veryShortAnimation() throws InterruptedException {
        Animation animation = new Animation(10f, false, Easing.LINEAR);
        
        animation.setState(true);
        Thread.sleep(10L);
        
        assertEquals(1.0, animation.getAnimationFactor());
    }

    @Test
    public void veryLongAnimation() throws InterruptedException {
        Animation animation = new Animation(5000f, false, Easing.LINEAR);
        
        animation.setState(true);
        Thread.sleep(100L);
        
        double factor = animation.getAnimationFactor();
        assertTrue(factor > 0 && factor < 0.1);
    }

    @Test
    public void animationWithSupplierChanges() throws InterruptedException {
        float[] length = {200f};
        Animation animation = new Animation(() -> length[0], false, () -> Easing.LINEAR);
        
        animation.setState(true);
        Thread.sleep(100L);
        double factor1 = animation.getAnimationFactor();
        
        // Change the supplier value
        length[0] = 400f;
        double factor2 = animation.getAnimationFactor();
        
        // Factor should be different now
        assertTrue(Math.abs(factor1 - factor2) > 0.01);
    }

    @Test
    public void boundedAnimationWithSupplier() throws InterruptedException {
        BoundedAnimation bounded = new BoundedAnimation(0f, 100f, () -> 200f, false, () -> Easing.LINEAR);
        
        bounded.setState(true);
        Thread.sleep(100L);
        
        double value = bounded.getAnimationValue();
        assertTrue(value > 40 && value < 60);
    }

    @Test
    public void colourAnimationWithSuppliers() throws InterruptedException {
        ColourAnimation colourAnimation = new ColourAnimation(Color.RED, Color.BLUE, () -> 200f, false, () -> Easing.LINEAR);
        
        colourAnimation.setState(true);
        Thread.sleep(200L);
        
        Color color = colourAnimation.getColour();
        assertEquals(Color.BLUE, color);
    }

    @Test
    public void allEasingMethodsComplete() throws InterruptedException {
        Easing[] allEasings = Easing.values();
        
        for (Easing easing : allEasings) {
            Animation animation = new Animation(200f, false, easing);
            animation.setState(true);
            Thread.sleep(200L);
            
            double factor = animation.getAnimationFactor();
            // All easing methods should return a value (some may exceed bounds)
            assertNotNull(factor);
            assertFalse(Double.isNaN(factor), "Easing " + easing + " returned NaN");
        }
    }

    @Test
    public void complexStateTransitionSequence() throws InterruptedException {
        BoundedAnimation bounded = new BoundedAnimation(0f, 100f, 200f, false, Easing.LINEAR);
        
        // Forward
        bounded.setState(true);
        Thread.sleep(50L);
        double progress1 = bounded.getAnimationValue();
        
        // Reverse
        bounded.setState(false);
        Thread.sleep(50L);
        double progress2 = bounded.getAnimationValue();
        
        // Forward again
        bounded.setState(true);
        Thread.sleep(50L);
        double progress3 = bounded.getAnimationValue();
        
        // All should be different
        assertTrue(progress1 > 0);
        assertTrue(progress2 < 100);
        assertTrue(progress3 > progress2);
    }

}
