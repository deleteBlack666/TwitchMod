package androidx.recyclerview.widget;


import android.database.Observable;
import android.view.View;

import java.util.ArrayList;


public class RecyclerView {
    /* ... */

    LayoutManager mLayout;
    ArrayList<ItemDecoration> mItemDecorations;


    /* ... */

    void markItemDecorInsetsDirty() {}
    public void requestLayout() {}

    public static abstract class LayoutManager {
        /* ... */

        public void assertNotInLayoutOrScroll(String message) {/* ... */}

        /* ... */
    }

    public static abstract class ViewHolder {
        /* ... */

        public View itemView;

        public ViewHolder(View itemView2) {/* ... */}

        /* ... */
    }

    public static abstract class ItemDecoration {/* ... */}

    public static abstract class AdapterDataObserver {/* ... */}

    static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        public void notifyItemRangeChanged(int positionStart, int itemCount, Object payload) {/* ... */}
    }

    public static abstract class Adapter<VH extends ViewHolder> {
        private final AdapterDataObservable mObservable = new AdapterDataObservable();

        /* ... */

        public void onViewRecycled(VH vh) {}

        public void onViewDetachedFromWindow(VH vh) {}

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {}

        public final void notifyItemChanged(int position, Object payload) { // TODO: __INJECT_METHOD
            this.mObservable.notifyItemRangeChanged(position, 1, payload);
        }
    }

    /* ... */

    public void invalidateItemDecorations() { // TODO: __INJECT_METHOD
        if (this.mItemDecorations.size() != 0) {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null) {
                layoutManager.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
            }
            markItemDecorInsetsDirty();
            requestLayout();
        }
    }
}
