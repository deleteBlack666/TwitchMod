package tv.twitch.android.shared.emotes.emotepicker;


import android.widget.ImageView;

import tv.twitch.android.core.adapters.TwitchSectionAdapter;
import tv.twitch.android.core.mvp.viewdelegate.RxViewDelegate;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateEvent;
import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.bridge.interfaces.IEmotePickerViewDelegate;
import tv.twitch.android.mod.hooks.HookController;
import tv.twitch.android.mod.util.Helper;
import tv.twitch.android.mod.util.Logger;
import tv.twitch.android.shared.emotes.emotepicker.models.EmotePickerSection;
import tv.twitch.android.shared.ui.elements.list.ContentListViewDelegate;


public class EmotePickerViewDelegate extends RxViewDelegate<EmotePickerPresenter.EmotePickerState, EmotePickerViewDelegate.Event> implements IEmotePickerViewDelegate { // TODO: __IMPLEMENT
    private final ImageView bttvEmotesButton = getBttvButtonView(); // TODO: __INJECT_FIELD
    private final ContentListViewDelegate listViewDelegate = null;

    @Override
    public void scrollToBttvSection() { // TODO: __INJECT_METHOD
        int pos = Helper.calcBttvPos((TwitchSectionAdapter) listViewDelegate.getAdapter());
        listViewDelegate.xScrollToPosition(pos);
    }

    /* ... */

    public static abstract class Event implements ViewDelegateEvent {
        /* ... */

        public static final class EmoteSectionSelectedEvent extends Event  {
            /* ... */

            public EmoteSectionSelectedEvent(EmotePickerSection emotePickerSection) {/* ... */}

            /* ... */
        }

        /* ... */
    }


    public EmotePickerViewDelegate() {

        /* ... */

        setupBttvEmoteButton(); // TODO: __INJECT_CODE
    }

    public void render(EmotePickerPresenter.EmotePickerState emotePickerState) {
        /* ... */

        setBttvSelected(emotePickerState); // TODO: __INJECT_CODE

        /* ... */
    }

    private final void setBttvSelected(EmotePickerPresenter.EmotePickerState emotePickerState) { // TODO: __INJECT_METHOD
        this.bttvEmotesButton.setSelected(emotePickerState.getSelectedEmotePickerSection() == EmotePickerSection.BTTV);
    }

    private void setupBttvEmoteButton() { // TODO: __INJECT_METHOD
        if (this.bttvEmotesButton == null) {
            Logger.error("bttvEmotesButton is null");
            return;
        }

        HookController.setupBttvEmotesButtonClickListener(bttvEmotesButton, this);
    }

    private ImageView getBttvButtonView() { // TODO: __INJECT_METHOD
        return findView(ResourcesManager.getId("bttv_emote_button"));
    }

    /* ... */
}
