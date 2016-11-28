package com.app.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.app.appInterface.HideInterface;
import com.app.appInterface.RevelInterface;


/**
 * Created by Rakshith on 2/22/2016.
 */
public class AppAnimation {

    public static void showViewRevel(View myView, View fromView) {

        int x = myView.getWidth() - 30;
        int y = 0;

        if (Build.VERSION.SDK_INT < 21) {
            myView.setVisibility(View.VISIBLE);
            return;
        }

// get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

// get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, x, y, 0, finalRadius);

// make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }


    public static void showViewRevel(View myView, int x, int y) {


        if (Build.VERSION.SDK_INT < 21) {
            myView.setVisibility(View.VISIBLE);
            return;
        }

// get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

// get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, x, y, 0, finalRadius);

// make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.setDuration(1000);
        anim.start();
    }


    public static void showViewCenterRevel(View myView, final RevelInterface revelInterface) {


        if (Build.VERSION.SDK_INT < 21) {
            myView.setVisibility(View.VISIBLE);
            return;
        }

// get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;
// get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);


// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

// make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.setDuration(1000);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (revelInterface != null)
                    revelInterface.onRevelFinsh();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
        anim.setDuration(1000);
        anim.start();
    }



    @TargetApi(21)
    public static void showViewSideReveal(final  View view, final RevelInterface revelInterface) {

        // get the center for the clipping circle
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        Log.d("Reveal", String.format(" ImageView Dimens %d %d %d %d", view.getTop(), view.getLeft(), view.getBottom(), view.getRight()));
        Log.d("Reveal", String.format("cx : %d, cy : %d", cx, cy));
        // get the final radius for the clipping circle
        int finalRadius = view.getWidth() * 2;
        // create and start the animator for this view
        // (the start radius is zero)
        try {
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, view.getLeft(), view.getTop(), 0, finalRadius);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    view.setVisibility(View.VISIBLE);
                }
            });
            anim.setDuration(1000);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (revelInterface != null)
                        revelInterface.onRevelFinsh();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

            });
            anim.start();
        }catch(Exception IllegalStateException){
            view.setVisibility(View.VISIBLE);
        }

    }


    public static void hideViewRevel(final View myView, final HideInterface hideInterface) {

        if (Build.VERSION.SDK_INT < 21) {
            myView.setVisibility(View.INVISIBLE);
            return;
        }

// get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

// get the initial radius for the clipping circle
        float initialRadius = (float) Math.hypot(cx, cy);

// create the animation (the final radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

// make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });


        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (hideInterface != null)
                    hideInterface.onHidden();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
// start the animation
        anim.start();
    }


    public static void expandView(View animatedView, Activity activity) {
        boolean expand = true;
        animatedView.clearAnimation();
        Display display = activity.getWindowManager().getDefaultDisplay();
        int maxWidth = display.getWidth();
        int maxHeight = display.getHeight();
        ExpandCollapseViewAnimation animation = new ExpandCollapseViewAnimation(animatedView, maxWidth, maxHeight, expand);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // btn.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        animatedView.startAnimation(animation);
        animatedView.invalidate();
    }

    public static void collapseView(View animatedView, Activity activity) {
        boolean expand = false;
        animatedView.clearAnimation();
        ExpandCollapseViewAnimation animation = new ExpandCollapseViewAnimation(
                animatedView, 50, 50, expand);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //btn.setVisibility(View.VISIBLE);
            }
        });
        animatedView.startAnimation(animation);
        animatedView.invalidate();
    }


    public static void centerAndZoomView(View view, View root, final Activity activity) {

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);

        int xDest = dm.widthPixels / 2;
        xDest -= (view.getMeasuredWidth() / 2);
        int yDest = dm.heightPixels / 2 - (view.getMeasuredHeight() / 2) - statusBarOffset;

        TranslateAnimation anim = new TranslateAnimation(0, xDest - originalPos[0], 0, yDest - originalPos[1]);

        Animation scale
                = new ScaleAnimation(1.0f, root.getMeasuredWidth() / view.getMeasuredWidth(), 1.0f, root.getMeasuredHeight() / view.getMeasuredHeight(),
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setInterpolator(new AccelerateInterpolator());

        AnimationSet set = new AnimationSet(false);

        set.addAnimation(scale);
        set.addAnimation(anim);

        set.setFillAfter(true);
        set.setDuration(1000);
        //set.start();


        view.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }
        });

    }


    public static void animateButton(final View mFloatingButton, final View myView) {

        mFloatingButton.animate().translationXBy(0.5f).translationY(150).translationXBy(-0.9f)
                .translationX(-150).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                animateReavel((int) mFloatingButton.getX(), 150, mFloatingButton, myView);
            }
        });

    }

    public static void animateReavel(int cx, int cy, final View mFloatingButton, final View myView) {


        // get the final radius for the clipping circle
        float finalRadius = hypo(myView.getWidth(), myView.getHeight());

        Animator animator =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mFloatingButton.setVisibility(View.INVISIBLE);
                myView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1000);
        animator.start();

    }

    static float hypo(int a, int b) {
        return (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public static Animator mCurrentAnimator;
    public static int mShortAnimationDuration = 500;
    public static Rect startBounds = new Rect();
    public static Rect finalBounds = new Rect();
    public static Point globalOffset = new Point();
    public static float startScaleFinal;

    public static void zoomImageFromThumb(final View thumbView, final View expandedImageView, final Activity activity, int root) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.

        boolean isZommed = true;

        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.


        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.


        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        activity.findViewById(root)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        //( activity.findViewById(R.id.fab)).setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                zoomOutImage(thumbView, expandedImageView, activity);

            }


        });
    }

    public static void zoomOutImage(final View thumbView, final View expandedImageView, final Activity activity) {
        boolean isZommed = false;
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Animate the four positioning/sizing properties in parallel,
        // back to their original values.
        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator
                .ofFloat(expandedImageView, View.X, startBounds.left))
                .with(ObjectAnimator
                        .ofFloat(expandedImageView,
                                View.Y, startBounds.top))
                .with(ObjectAnimator
                        .ofFloat(expandedImageView,
                                View.SCALE_X, startScaleFinal))
                .with(ObjectAnimator
                        .ofFloat(expandedImageView,
                                View.SCALE_Y, startScaleFinal));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                thumbView.setAlpha(1f);
                //( activity.findViewById(R.id.fab)).setAlpha(1f);
                expandedImageView.setVisibility(View.GONE);
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                thumbView.setAlpha(1f);
                // ( activity.findViewById(R.id.fab)).setAlpha(1f);
                expandedImageView.setVisibility(View.GONE);
                mCurrentAnimator = null;
            }
        });
    }
}


