/*
 * Copyright (C) 2016 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tv.twitch.android.mod.bridges.dexter;

import tv.twitch.android.mod.bridges.dexter.listener.multi.MultiplePermissionsListener;

import java.util.Collection;

import tv.twitch.android.mod.bridges.dexter.listener.PermissionRequestErrorListener;
import tv.twitch.android.mod.bridges.dexter.listener.single.PermissionListener;

public interface DexterBuilder {

  DexterBuilder onSameThread();

  DexterBuilder withErrorListener(PermissionRequestErrorListener errorListener);

  void check();

  interface Permission {
    DexterBuilder.SinglePermissionListener withPermission(String permission);

    DexterBuilder.MultiPermissionListener withPermissions(String... permissions);

    DexterBuilder.MultiPermissionListener withPermissions(Collection<String> permissions);
  }

  interface SinglePermissionListener {
    DexterBuilder withListener(PermissionListener listener);
  }

  interface MultiPermissionListener {
    DexterBuilder withListener(MultiplePermissionsListener listener);
  }
}