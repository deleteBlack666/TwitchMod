package tv.twitch.android.shared.ui.elements.list;


import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateState;



public abstract class ListViewState implements ViewDelegateState {
    public static final class Loading extends ListViewState {
        public static final Loading INSTANCE = new Loading();
    }

    public static final class Loaded extends ListViewState {
        public static final Loaded INSTANCE = new Loaded();
    }

    public static final class Error extends ListViewState {
        public static final Error INSTANCE = new Error();
    }

    public static final class Empty extends ListViewState {
        public static final Empty INSTANCE = new Empty();
    }
}