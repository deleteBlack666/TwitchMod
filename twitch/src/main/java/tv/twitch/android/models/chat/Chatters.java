package tv.twitch.android.models.chat;


import java.util.List;


public class Chatters {
    private List<String> broadcasters;
    private List<String> moderators;
    private int numViewers;
    private List<String> staff;
    private List<String> viewers;
    private List<String> vips;

    public final int getNumViewers() {
        return this.numViewers;
    }

    public final List<String> getBroadcasters() {
        return this.broadcasters;
    }

    public final List<String> getStaff() {
        return this.staff;
    }

    public final List<String> getModerators() {
        return this.moderators;
    }

    public final List<String> getVips() {
        return this.vips;
    }

    public final List<String> getViewers() {
        return this.viewers;
    }
}
