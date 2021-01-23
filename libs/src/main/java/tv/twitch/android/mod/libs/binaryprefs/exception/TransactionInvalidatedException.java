package tv.twitch.android.mod.libs.binaryprefs.exception;

import tv.twitch.android.mod.libs.binaryprefs.PreferencesEditor;

/**
 * Exception will be thrown if {@link PreferencesEditor#apply()} or {@link PreferencesEditor#commit()}
 * called twice for one instance of {@link PreferencesEditor}.
 */
public final class TransactionInvalidatedException extends RuntimeException {

    public TransactionInvalidatedException(String msg) {
        super(msg);
    }
}