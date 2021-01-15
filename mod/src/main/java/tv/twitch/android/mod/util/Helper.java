package tv.twitch.android.mod.util;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import okhttp3.HttpUrl;
import okhttp3.Request;
import tv.twitch.android.api.parsers.PlayableModelParser;
import tv.twitch.android.core.adapters.RecyclerAdapterSection;
import tv.twitch.android.core.adapters.TwitchSectionAdapter;
import tv.twitch.android.mod.bridge.LoaderLS;
import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.models.Playable;
import tv.twitch.android.models.clips.ClipModel;
import tv.twitch.android.shared.emotes.emotepicker.adapter.EmotePickerAdapterSection;
import tv.twitch.android.shared.emotes.emotepicker.models.EmotePickerSection;


public class Helper {
    public static void openUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            Logger.error("Empty url");
            return;
        }

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "https://" + url;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        LoaderLS.getInstance().startActivity(intent);
    }

    public static void restartToActivity(Class<?> activityClass) {
        Intent intent = new Intent(LoaderLS.getInstance(), activityClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(LoaderLS.getInstance(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        ((AlarmManager) LoaderLS.getInstance().getSystemService(Context.ALARM_SERVICE)).setExact(AlarmManager.RTC, 1500, pendingIntent);
        LoaderLS.killApp();
    }

    public static void showRestartDialog(final Activity context, String message) {
        new AlertDialog.Builder(context).setMessage(message).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartToActivity(context.getClass());
            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    public static String fixFilename(final String filename, final char replace) {
        if (TextUtils.isEmpty(filename))
            return filename;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < filename.length(); i++) {
            char ch = filename.charAt(i);
            if (isValidFatFilenameChar(ch)) {
                stringBuilder.append(ch);
            } else {
                stringBuilder.append(replace);
            }
        }

        return stringBuilder.toString();
    }

    public static boolean isValidFatFilenameChar(final char c) {
        if (c <= 0x1f)
            return false;

        switch (c) {
            case '"':
            case '*':
            case '/':
            case ':':
            case '<':
            case '>':
            case '?':
            case '\\':
            case '|':
            case 0x7F:
                return false;
            default:
                return true;
        }
    }

    public static void downloadMP4File(final Context context, final String url, final String filename) {
        final String fixedFilename = fixFilename(filename, '_');
        PermissionUtil.checkWritePermission(context, new PermissionUtil.ResultCallback() {
            @Override
            public void onPermissionGranted() {
                Uri uri = Uri.parse(url);
                DownloadManager downloadManager = (DownloadManager) LoaderLS.getInstance().getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                request.setMimeType("video/mp4");
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/twitch/" + fixedFilename + ".mp4");

                downloadManager.enqueue(request);
                Helper.showToast(String.format(ResourcesManager.getString("mod_downloading"), fixedFilename));
            }

            @Override
            public void onPermissionDenied() {}

            @Override
            public void onError() {}
        });
    }

    public static int getRandomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static int getChannelId(PlayableModelParser playableModelParser, Playable playable) {
        if (playableModelParser == null) {
            Logger.error("playableModelParser is null");
            return 0;
        }
        if (playable == null) {
            Logger.error("playable is null");
            return 0;
        }

        if (playable instanceof ClipModel) {
            return ((ClipModel) playable).getBroadcasterId();
        }

        return playableModelParser.getChannelId(playable);
    }

    public static void showToast(String message) {
        Toast.makeText(LoaderLS.getInstance().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public static boolean isOnStackTrace(String clazz) {
        if (TextUtils.isEmpty(clazz)) {
            Logger.error("Empty clazz");
            return false;
        }

        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement == null)
                continue;

            if (TextUtils.isEmpty(stackTraceElement.getClassName()))
                continue;

            if (!stackTraceElement.getClassName().equalsIgnoreCase(clazz))
                continue;

            return true;
        }

        return false;
    }

    public static void showPartnerBanner(Context context) {}

    public static int getFileLength(@NonNull String url) {
        URL fileUrl;
        try {
            fileUrl = new URL(url);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setRequestMethod("HEAD");
            return conn.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static int calcBttvPos(TwitchSectionAdapter adapter) {
        if (adapter == null) {
            Logger.error("adapter is null");
            return 0;
        }

        List<RecyclerAdapterSection> sections = adapter.getSections();
        if (sections == null || sections.size() == 0)
            return 0;

        int sizeWithHeader = 0;
        for (RecyclerAdapterSection t : sections) {
            EmotePickerAdapterSection section = (EmotePickerAdapterSection) t;

            if (section.getEmotePickerSection() == EmotePickerSection.BTTV)
                return sizeWithHeader;

            sizeWithHeader += section.sizeWithHeader();
        }

        return 0;
    }

    public static String decodeBase64(String data) {
        if (TextUtils.isEmpty(data))
            return data;

        try {
            return new String(Base64.decode(data, Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            return null;
        }
    }

    public static String readTextFromAssets(Context context, String filepath) {
        if (context == null) {
            Logger.error("context is null");

            return null;
        }

        if (TextUtils.isEmpty(filepath)) {
            Logger.error("empty filepath");

            return null;
        }

        try {
            InputStream inputStream = context.getApplicationContext().getAssets().open(filepath);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (stringBuilder.length() == 0) {
                    stringBuilder.append(line);
                } else {
                    stringBuilder.append("\n").append(line);
                }
            }

            inputStream.close();
            bufferedReader.close();

            return stringBuilder.toString();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static HashMap<String, String> parseConfig(InputStream is) {
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        HashMap<String, String> res = new HashMap<>();

        try {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] pair = line.split("=", 2);
                if (pair.length != 2) {
                    continue;
                }

                res.put(pair[0], pair[1]);
            }

            inputStreamReader.close();
            bufferedReader.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();

            return null;
        } finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    public static String[] getAssetsEntries(String root) {
        if (TextUtils.isEmpty(root))
            root = "";

        try {
            return LoaderLS.getInstance().getApplicationContext().getAssets().list(root);
        } catch (IOException ex) {
            Logger.error("root=" + root);
            ex.printStackTrace();
        }

        return null;
    }

    public static boolean isAccessTokenRequest(Request request) {
        if (request == null)
            return false;

        HttpUrl url = request.url();

        String host = url.host();
        if (TextUtils.isEmpty(host))
            return false;

        if (!host.equals("api.twitch.tv"))
            return false;

        List<String> segments = url.pathSegments();
        if (segments.isEmpty())
            return false;

        String lastSegment = segments.get(segments.size()-1);
        if (TextUtils.isEmpty(lastSegment))
            return false;

        return lastSegment.equals("access_token");
    }

    public static boolean isGQLAccessTokenRequest(Request request) {
        if (request == null)
            return false;

        HttpUrl url = request.url();

        String host = url.host();
        if (TextUtils.isEmpty(host))
            return false;

        if (!host.equals("gql.twitch.tv"))
            return false;

        String opName = request.header("X-APOLLO-OPERATION-NAME");
        if (opName == null || TextUtils.isEmpty(opName))
            return false;

        return opName.equals("StreamAccessTokenQuery");
    }

    public static boolean isUsherRequest(Request request) {
        if (request == null)
            return false;

        HttpUrl url = request.url();

        String host = url.host();
        if (TextUtils.isEmpty(host))
            return false;

        return url.host().equals("usher.ttvnw.net");
    }

    public static boolean isHiDensityDevice() {
        Resources resources = LoaderLS.getInstance().getResources();
        if (resources != null) {
            DisplayMetrics dm = resources.getDisplayMetrics();
            if (dm != null) {
                return dm.density > 2.0f;
            }
        }

        return false;
    }
}
