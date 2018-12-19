/*
 * Copyright (C) 2019 The Android Open Source Project
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

package com.android.settings.panel;

import static com.android.settings.panel.SettingsPanelActivity.KEY_PANEL_PACKAGE_NAME;
import static com.android.settings.panel.SettingsPanelActivity.KEY_PANEL_TYPE_ARGUMENT;

import static com.google.common.truth.Truth.assertThat;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class SettingsPanelActivityTest {

    @Test
    public void startMediaOutputSlice_withPackageName_bundleShouldHaveValue() {
        final Intent intent = new Intent()
                .setAction("com.android.settings.panel.action.MEDIA_OUTPUT")
                .putExtra("com.android.settings.panel.extra.PACKAGE_NAME",
                        "com.google.android.music");

        final SettingsPanelActivity activity =
                Robolectric.buildActivity(SettingsPanelActivity.class, intent).create().get();

        assertThat(activity.mBundle.getString(KEY_PANEL_PACKAGE_NAME))
                .isEqualTo("com.google.android.music");
        assertThat(activity.mBundle.getString(KEY_PANEL_TYPE_ARGUMENT))
                .isEqualTo("com.android.settings.panel.action.MEDIA_OUTPUT");
    }

    @Test
    public void startMediaOutputSlice_withoutPackageName_bundleShouldNotHaveValue() {
        final Intent intent = new Intent()
                .setAction("com.android.settings.panel.action.MEDIA_OUTPUT");

        final SettingsPanelActivity activity =
                Robolectric.buildActivity(SettingsPanelActivity.class, intent).create().get();

        assertThat(activity.mBundle.containsKey(KEY_PANEL_PACKAGE_NAME)).isFalse();
        assertThat(activity.mBundle.containsKey(KEY_PANEL_TYPE_ARGUMENT)).isFalse();
    }
}
