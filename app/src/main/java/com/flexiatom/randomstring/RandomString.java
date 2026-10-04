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

import java.security.SecureRandom;

final class RandomString {

    private static final char[] ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static final int MIN_LENGTH = 6;
    private static final int LENGTH_SPAN = 4;
    private static final SecureRandom DEFAULT_RANDOM = new SecureRandom();

    private RandomString() {
    }

    static String generate() {
        return generate(DEFAULT_RANDOM);
    }

    static String generate(SecureRandom random) {
        int length = MIN_LENGTH + random.nextInt(LENGTH_SPAN);
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = ALPHABET[random.nextInt(ALPHABET.length)];
        }
        return new String(chars);
    }
}
