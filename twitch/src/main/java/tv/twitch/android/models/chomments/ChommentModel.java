package tv.twitch.android.models.chomments;


public class ChommentModel {
    private ChommentCommenterModel commenter;
    private ChommentMessageModel message;
    private int contentOffsetSeconds;

    /* ... */

    public final ChommentMessageModel getMessage() {
        return this.message;
    }

    public ChommentCommenterModel getCommenter() {
        return commenter;
    }

    public final int getContentOffsetSeconds() {
        return this.contentOffsetSeconds;
    }

    /* ... */
}
