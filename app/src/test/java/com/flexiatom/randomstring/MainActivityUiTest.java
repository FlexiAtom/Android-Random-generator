/*
 * Copyright (C) 2026 FlexiAtom <3685587367@qq.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.flexiatom.randomstring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.ClipboardManager;
import android.content.Context;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;

import com.google.android.material.button.MaterialButton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34)
public class MainActivityUiTest {

    @Test
    public void generateThenCopyIntoClipboard() {
        try (ActivityScenario<MainActivity> scenario =
                     ActivityScenario.launch(MainActivity.class)) {
            scenario.onActivity(activity -> {
                TextView result = activity.findViewById(R.id.result_text);
                MaterialButton generate = activity.findViewById(R.id.btn_generate);
                MaterialButton copy = activity.findViewById(R.id.btn_copy);

                assertEquals("initial screen shows nothing", "", result.getText().toString());
                assertFalse("copy disabled before first generate", copy.isEnabled());

                generate.performClick();
                String generated = result.getText().toString();
                assertTrue("generated: " + generated, generated.matches("[A-Za-z0-9]{6,9}"));
                assertTrue("copy enabled after generate", copy.isEnabled());

                copy.performClick();
                ClipboardManager clipboard = (ClipboardManager)
                        activity.getSystemService(Context.CLIPBOARD_SERVICE);
                assertEquals(generated,
                        clipboard.getPrimaryClip().getItemAt(0).getText().toString());

                generate.performClick();
                String second = result.getText().toString();
                assertTrue("second generated: " + second, second.matches("[A-Za-z0-9]{6,9}"));
                copy.performClick();
                assertEquals(second,
                        clipboard.getPrimaryClip().getItemAt(0).getText().toString());
            });
        }
    }
}
