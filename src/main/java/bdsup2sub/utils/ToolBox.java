/*
 * Copyright 2014 Volker Oth (0xdeadbeef) / Miklos Juhasz (mjuhasz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bdsup2sub.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class ToolBox {

    private static final DecimalFormat FPS_FORMATTER = new DecimalFormat("##.###", DecimalFormatSymbols.getInstance(Locale.US));

    public static String leftZeroPad(int value, int width) {
        return String.format("%0" + width + "d", value);
    }

    public static String toHexLeftZeroPadded(long value, int width) {
        return String.format("0x%0" + width + "x", value);
    }

    public static String formatDouble(double value) {
        return FPS_FORMATTER.format(value);
    }

    /**
     * Write ASCII string to buffer[index] (no special handling for multi-byte characters)
     * @param buffer Byte array
     * @param index Index to write to
     * @param s String containing ASCII characters
     * @throws ArrayIndexOutOfBoundsException
     */
    public static void setString(byte buffer[], int index, String s) {
        for (int i =0; i<s.length(); i++) {
            buffer[index+i] = (byte)s.charAt(i);
        }
    }

    /**
     * Returns the first few bytes of a file to check it's type
     * @param fname Filename of the file
     * @param num Number of bytes to return
     * @return Array of bytes (size num) from the beginning of the file
     */
    public static byte[] getFileID(String fname, int num) {
        byte buf[] = new byte[num];
        File f = new File(fname);
        if (f.length() < num) {
            return null;
        }
        try {
            FileInputStream fi = new FileInputStream(fname);
            fi.read(buf);
            fi.close();
        } catch (Exception ex) {
            return null;
        }
        return buf;
    }

    /**
     * Convert String to integer
     * @param s String containing integer (assumed: positive)
     * @return Integer value or -1.0 if no valid numerical value
     */
    public static int getInt(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    /**
     * Convert String to double
     * @param s String containing double
     * @return Double value or -1.0 if no valid numerical value
     */
    public static double getDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException ex) {
            return -1.0;
        }
    }
}
