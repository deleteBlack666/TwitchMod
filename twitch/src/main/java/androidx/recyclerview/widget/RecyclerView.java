package androidx.recyclerview.widget;


import android.view.ViewGroup;


public class RecyclerView {
    /* ... */

    public static abstract class ViewHolder {/* ... */}

    public static abstract class Adapter<VH extends ViewHolder> {
        /* ... */

        public abstract int getItemCount();

        public abstract void onBindViewHolder(VH vh, int i);

        public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

        /* ... */
    }

    /* ... */

    public void stopScroll() {/* ... */}
}