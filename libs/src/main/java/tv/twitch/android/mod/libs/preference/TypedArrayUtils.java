/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tv.twitch.android.mod.libs.preference;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP_PREFIX;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleableRes;

import org.xmlpull.v1.XmlPullParser;

/**
 * Compat methods for accessing TypedArray values.
 *
 * All the getNamed*() functions added the attribute name match, to take care of potential ID
 * collision between the private attributes in older OS version (OEM) and the attributes existed in
 * the newer OS version.
 * For example, if an private attribute named "abcdefg" in Kitkat has the
 * same id value as "android:pathData" in Lollipop, we need to match the attribute's namefirst.
 *
 * @hide
 */
@RestrictTo(LIBRARY_GROUP_PREFIX)
public final class TypedArrayUtils {

    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    /**
     * @return Whether the current node ofthe  {@link XmlPullParser} has an attribute with the
     * specified {@code attrName}.
     */
    public static boolean hasAttribute(@NonNull XmlPullParser parser, @NonNull String attrName) {
        return parser.getAttributeValue(NAMESPACE, attrName) != null;
    }


    /**
     * @return a boolean value of {@code index}. If it does not exist, {@code defaultValue}.
     */
    public static boolean getBoolean(@NonNull TypedArray a, @StyleableRes int index,
                                     boolean defaultValue) {
        return a.getBoolean(index, defaultValue);
    }


    /**
     * @return a drawable value of {@code index}. If it does not exist, {@code null}.
     */
    @Nullable
    public static Drawable getDrawable(@NonNull TypedArray a, @StyleableRes int index) {
        return a.getDrawable(index);
    }

    /**
     * @return an int value of {@code index}. If it does not exist, {@code defaultValue}.
     */
    public static int getInt(@NonNull TypedArray a, @StyleableRes int index,
                             int defaultValue) {
        return a.getInt(index, defaultValue);
    }

    /**
     * @return a resource ID value of {@code index}. If it does not exist, {@code defaultValue}.
     */
    @AnyRes
    public static int getResourceId(@NonNull TypedArray a, @StyleableRes int index,
                                    @AnyRes int defaultValue) {
        return a.getResourceId(index, defaultValue);
    }

    /**
     * @return a string value of {@code index}. If it does not exist, {@code null}.
     */
    @Nullable
    public static String getString(@NonNull TypedArray a, @StyleableRes int index) {
        try {
            return a.getString(index);
        } catch (Throwable th2) {
            th2.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves a text attribute value with the specified fallback ID.
     *
     * @return a text value of {@code index}. If it does not exist, {@code null}.
     */
    @Nullable
    public static CharSequence getText(@NonNull TypedArray a, @StyleableRes int index) {
        return a.getText(index);
    }

    /**
     * Retrieves a string array attribute value with the specified fallback ID.
     *
     * @return a string array value of {@code index}. If it does not exist, {@code null}.
     */
    @Nullable
    public static CharSequence[] getTextArray(@NonNull TypedArray a, @StyleableRes int index) {
        return a.getTextArray(index);
    }

    /**
     * @return The resource ID value in the {@code context} specified by {@code attr}. If it does
     * not exist, {@code fallbackAttr}.
     */
    public static int getAttr(@NonNull Context context, int attr, int fallbackAttr) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attr, value, true);
        if (value.resourceId != 0) {
            return attr;
        }
        return fallbackAttr;
    }

    private TypedArrayUtils() {
    }
}
