package tv.twitch.a.k.d0.b.s;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.n.g.c;

import tv.twitch.android.mod.bridges.IDrawable;
import tv.twitch.android.mod.bridges.LoaderLS;
import tv.twitch.android.mod.models.settings.Gifs;
import tv.twitch.android.mod.settings.PrefManager;


/**
 * Source: UrlDrawable
 */
public class h extends BitmapDrawable implements IDrawable { // TODO: __IMPLEMENT
    public Drawable a;
    public String b;
    public d c;
    private boolean isBits = false; // TODO: __ADD_FIELD


    /**
     * @param str Url
     * @param dVar MediaSpan
     */
    public h(String str, d dVar) {
        this.b = str;
        this.c = dVar;
    }

    /**
     * @param drawable emote
     */
    public final void a(Drawable drawable) {
        this.a = drawable;
    }

    @Override
    public void draw(Canvas canvas) { // TODO: __REPLACE_METHOD
        Drawable drawable = this.a;
        if (drawable != null) {
            drawable.draw(canvas);
            if (drawable instanceof com.bumptech.glide.load.n.g.c) {
                if (isBits)
                    ((c) drawable).start();
                else {
                    PrefManager manager = LoaderLS.getInstance().getPrefManager();
                    if (manager.getGifsStrategy() == Gifs.ANIMATED)
                        ((c) drawable).start();
                }
            }
        }
    }

    public h(String str, d dVar, int i2, Object gVar) {
        isBits = i2 == 2; // TODO: __INJECT_CODE
        // this;
    }

    @Override
    public Drawable getDrawable() {
        return a;
    }
}
