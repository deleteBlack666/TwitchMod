package tv.twitch.android.app.run;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Arrays;

import tv.twitch.android.mod.badges.BadgeManager;
import tv.twitch.android.mod.fragments.settings.MainSettingsFragment;
import tv.twitch.android.mod.util.FragmentUtil;
import tv.twitch.android.mod.util.Logger;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, new MainSettingsFragment());
        transaction.commit();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Logger.debug(BadgeManager.INSTANCE.findBadges(73227142).toString());
                    } catch (Throwable throwable) {
                        ;
                    }
                }

            }
        }).start();
    }
}
