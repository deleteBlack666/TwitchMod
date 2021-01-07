package tv.twitch.android.shared.ui.elements.span;


import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.lang.ref.WeakReference;

import tv.twitch.android.mod.hooks.General;
import tv.twitch.android.mod.util.Logger;


public class GlideChatImageTarget extends SimpleTarget<Drawable> {
    private UrlDrawable mUrlDrawable;
    private WeakReference<View> mContainerView; // TODO: __INJECT_FIELD

    public GlideChatImageTarget(Context context, UrlDrawable urlDrawable, int i) {}


    private float calcMin(float f1, float f2) { // TODO: __INJECT_METHOD
        if (mUrlDrawable.isTwitchEmote() || !mUrlDrawable.shouldWide())
            return Math.min(f1, f2);

        return f1;
    }

    public void setContainerView(View view) { // TODO: __INJECT_METHOD
        if (view == null) {
            Logger.debug("view is null");
            return;
        }

        mContainerView = new WeakReference<>(view);
    }

    private Point scaleSquared(float f, float f2, float f3) {
        float f4 = 1.0f;
        float f5 = 1.0f;

        /* ... */

        float min = calcMin(f4, f5); // TODO: __INJECT_CODE

        /* ... */

        return null;
    }

    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
        /* ... */

        General.maybeInvalidateChatItem(mContainerView, mUrlDrawable); // TODO: __INJECT_CODE
    }
}
