package tv.twitch.android.shared.experiments;


import tv.twitch.android.mod.hooks.Hook;


public class ExperimentHelper {
    /* ... */

    public final boolean isInOnGroupForBinaryExperiment(Experiment experiment) { // TODO: __INJECT_METHOD
        return Hook.hookExperimental(experiment, isInOnGroupForBinaryExperimentOrg(experiment));
    }

    public final String getGroupForExperiment(Experiment experiment) { // TODO: __INJECT_METHOD
        return Hook.hookExperimentalGroup(experiment, getGroupForExperimentOrg(experiment));
    }

    public boolean isInGroupForMultiVariantExperiment(Experiment experiment, String str) { // TODO: __RENAME__isInGroupForMultiVariantExperiment
        return Hook.hookExperimentalMultiVariant(experiment, str, isInGroupForMultiVariantExperimentOrg(experiment, str));
    }

    public final String getGroupForExperimentOrg(Experiment experiment) { // TODO: __RENAME__getGroupForExperiment
        return null;
    }

    public boolean isInOnGroupForBinaryExperimentOrg(Experiment experiment) { // TODO: __RENAME__isInOnGroupForBinaryExperiment
        return false;
    }

    public boolean isInGroupForMultiVariantExperimentOrg(Experiment experiment, String str) { // TODO: __RENAME__isInGroupForMultiVariantExperiment
        return false;
    }

    /* ... */
}
