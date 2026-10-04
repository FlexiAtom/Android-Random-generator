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

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result_text);
        MaterialButton generateButton = findViewById(R.id.btn_generate);
        MaterialButton copyButton = findViewById(R.id.btn_copy);

        generateButton.setOnClickListener(v -> {
            resultText.setText(RandomString.generate());
            copyButton.setEnabled(true);
        });

        copyButton.setOnClickListener(v -> {
            CharSequence text = resultText.getText();
            ClipboardManager clipboard =
                    (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(
                    ClipData.newPlainText(getString(R.string.app_name), text));
            Snackbar.make(v, R.string.copied, Snackbar.LENGTH_SHORT).show();
        });
    }
}
