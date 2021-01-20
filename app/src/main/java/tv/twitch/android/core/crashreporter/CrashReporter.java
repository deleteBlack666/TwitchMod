package tv.twitch.android.core.crashreporter;


import android.app.Application;

import tv.twitch.android.mod.util.Logger;


public class CrashReporter {
    private static volatile Application application;
    private static volatile CrashReporterListener crashReporterListener;

    public interface CrashReporterListener {/* ... */}


    private final Object getCrashlytics() { // TODO: __REMOVE
        /* ... */

        return null;
    }

    public final void init(Application application, CrashReporterListener crashReporterListener) { // TODO: __REPLACE_METHOD
        this.application = application;
        this.crashReporterListener = crashReporterListener;
    }

    public final void setUserNameForDebugBuilds(String str) {} // TODO: __REPLACE_METHOD

    public final void setUserIdentifierForDebugBuilds(String str) {} // TODO: __REPLACE_METHOD

    public final void setString(String str, String str2) { // TODO: __REPLACE_METHOD
        Logger.debug(str + " --> " + str2);
    }
}
