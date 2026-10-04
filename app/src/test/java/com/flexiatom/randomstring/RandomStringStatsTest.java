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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RandomStringStatsTest {

    @Test
    public void uniformSamplingOverAlphabetAndLengths() {
        SecureRandom random = new SecureRandom();
        int samples = 200_000;
        Map<Integer, Integer> lengthCount = new TreeMap<>();
        Map<Character, Integer> charCount = new HashMap<>();
        long totalChars = 0;

        for (int i = 0; i < 5; i++) {
            System.out.println("sample: " + RandomString.generate(random));
        }

        for (int i = 0; i < samples; i++) {
            String s = RandomString.generate(random);
            assertTrue("illegal string: " + s, s.matches("[A-Za-z0-9]{6,9}"));
            lengthCount.merge(s.length(), 1, Integer::sum);
            for (char c : s.toCharArray()) {
                charCount.merge(c, 1, Integer::sum);
            }
            totalChars += s.length();
        }

        for (int length = 6; length <= 9; length++) {
            int count = lengthCount.getOrDefault(length, 0);
            System.out.println("length " + length + ": " + count);
            assertTrue("length " + length + " count " + count + " out of [22%,28%]",
                    count >= samples * 0.22 && count <= samples * 0.28);
        }

        assertEquals("alphabet coverage", 62, charCount.size());
        double expected = (double) totalChars / 62;
        char minChar = 0;
        char maxChar = 0;
        for (Map.Entry<Character, Integer> e : charCount.entrySet()) {
            double deviation = Math.abs(e.getValue() - expected) / expected;
            System.out.printf("char %s: %d (dev %.4f)%n", e.getKey(), e.getValue(), deviation);
            assertTrue("char " + e.getKey() + " deviation " + deviation + " over 15%",
                    deviation <= 0.15);
            if (e.getValue() < charCount.getOrDefault(minChar, Integer.MAX_VALUE)) {
                minChar = e.getKey();
            }
            if (e.getValue() > charCount.getOrDefault(maxChar, 0)) {
                maxChar = e.getKey();
            }
        }
        System.out.printf("totalChars=%d expectedPerChar=%.0f min=%s(%d) max=%s(%d)%n",
                totalChars, expected, minChar, charCount.get(minChar),
                maxChar, charCount.get(maxChar));
    }
}
