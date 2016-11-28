package com.app.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Rakshith on 2/23/2016.
 */
public class ExpandCollapseViewAnimation extends Animation {
    int targetWidth;
    int targetHeight;
    int initialWidth;
    int initialHeight;
    boolean expand;
    View view;

    public ExpandCollapseViewAnimation(View view, int targetWidth, int targetHeight , boolean expand) {
        this.view = view;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        this.initialWidth = view.getWidth();
        this.initialHeight = view.getHeight();
        this.expand = expand;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newWidth, newHeight;
        if (expand) {
            newWidth = this.initialWidth
                    + (int) ((this.targetWidth - this.initialWidth) * interpolatedTime);
            newHeight = this.initialHeight
                    + (int) ((this.targetHeight - this.initialHeight) * interpolatedTime);
        } else {
            newWidth = this.initialWidth
                    - (int) ((this.initialWidth - this.targetWidth) * interpolatedTime);
            newHeight = this.initialHeight
                    - (int) ((this.initialHeight - this.targetHeight) * interpolatedTime);
        }

        view.getLayoutParams().width = newWidth;
        view.getLayoutParams().height = newHeight;
        view.requestLayout();
    }
    }