package com.erif.snacking.helper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.snackbar.Snackbar;

public class ScrollAwareBehavior extends CoordinatorLayout.Behavior<View> {

    protected static final int ENTER_ANIMATION_DURATION = 225;
    protected static final int EXIT_ANIMATION_DURATION = 175;

    private static final int STATE_SCROLLED_DOWN = 1;
    private static final int STATE_SCROLLED_UP = 2;

    private int height = 0;
    private int currentState = STATE_SCROLLED_UP;
    private int additionalHiddenOffsetY = 0;
    @Nullable
    private ViewPropertyAnimator currentAnimator;

    public ScrollAwareBehavior() {}

    public ScrollAwareBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull View child, int layoutDirection) {
        ViewGroup.MarginLayoutParams paramsCompat = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        height = child.getMeasuredHeight() + paramsCompat.bottomMargin;
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    /**
     * Sets an additional offset for the y position used to hide the view.
     *
     * @param child the child view that is hidden by this behavior
     * @param offset the additional offset in pixels that should be added when the view slides away
     */
    public void setAdditionalHiddenOffsetY(@NonNull View child, @Dimension int offset) {
        additionalHiddenOffsetY = offset;

        if (currentState == STATE_SCROLLED_DOWN) {
            child.setTranslationY(height + additionalHiddenOffsetY);
        }
    }

    @Override
    public boolean onStartNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
            @NonNull View directTargetChild, @NonNull View target, int nestedScrollAxes, int type
    ) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target,
            int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed
    ) {
        if (dyConsumed > 0) {
            slideDown(child);
        } else if (dyConsumed < 0) {
            slideUp(child);
        }
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        super.onDependentViewRemoved(parent, child, dependency);
        child.setTranslationY(0f);
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            Log.d("QuickSnackBar", "Y: "+dependency.getTranslationY()+" Height: "+dependency.getHeight());
            Log.d("QuickSnackBar", "ChildY: "+child.getTranslationY());
            float translationY = Math.min(0f, dependency.getTranslationY() - dependency.getHeight());
            if (dependency.getTranslationY() != 0f) {
                child.setTranslationY(translationY);
            }
            return true;
        }
        return false;
    }

    /** Returns true if the current state is scrolled up. */
    public boolean isScrolledUp() {
        return currentState == STATE_SCROLLED_UP;
    }

    /**
     * Performs an animation that will slide the child from it's current position to be totally on the
     * screen.
     */
    public void slideUp(@NonNull View child) {
        slideUp(child, /*animate=*/ true);
    }

    /**
     * Slides the child with or without animation from its current position to be totally on the
     * screen.
     *
     * @param animate {@code true} to slide with animation.
     */
    @SuppressLint("RestrictedApi")
    public void slideUp(@NonNull View child, boolean animate) {
        if (isScrolledUp()) {
            return;
        }

        if (currentAnimator != null) {
            currentAnimator.cancel();
            child.clearAnimation();
        }
        currentState = STATE_SCROLLED_UP;
        int targetTranslationY = 0;
        if (animate) {
            animateChildTo(
                    child,
                    targetTranslationY,
                    ENTER_ANIMATION_DURATION,
                    AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR
            );
        } else {
            child.setTranslationY(targetTranslationY);
        }
    }

    /** Returns true if the current state is scrolled down. */
    public boolean isScrolledDown() {
        return currentState == STATE_SCROLLED_DOWN;
    }

    /**
     * Performs an animation that will slide the child from it's current position to be totally off
     * the screen.
     */
    public void slideDown(@NonNull View child) {
        slideDown(child, /*animate=*/ true);
    }

    /**
     * Slides the child with or without animation from its current position to be totally off the
     * screen.
     *
     * @param animate {@code true} to slide with animation.
     */
    @SuppressLint("RestrictedApi")
    public void slideDown(@NonNull View child, boolean animate) {
        if (isScrolledDown()) {
            return;
        }

        if (currentAnimator != null) {
            currentAnimator.cancel();
            child.clearAnimation();
        }
        currentState = STATE_SCROLLED_DOWN;
        int targetTranslationY = height + additionalHiddenOffsetY;
        if (animate) {
            animateChildTo(
                    child,
                    targetTranslationY,
                    EXIT_ANIMATION_DURATION,
                    AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR
            );
        } else {
            child.setTranslationY(targetTranslationY);
        }
    }

    private void animateChildTo(
            @NonNull View child, int targetY, long duration, TimeInterpolator interpolator) {
        currentAnimator =
                child
                        .animate()
                        .translationY(targetY)
                        .setInterpolator(interpolator)
                        .setDuration(duration)
                        .setListener(
                                    new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            currentAnimator = null;
                                        }
                                    }
                                );
    }
}
