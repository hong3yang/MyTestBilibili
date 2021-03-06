package com.example.hong3.mybilibili.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;




/**
 * <pre>
 *
 *     author: Blankj
 *
 *     blog  : http://blankj.com
 *
 *     time  : 2016/8/13
 *
 *     desc  : 转换相关工具类
 *
 * </pre>
 */

public class ConvertUtils {


    private ConvertUtils() {

        throw new UnsupportedOperationException("u can't instantiate me...");

    }


    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    /**
     * byteArr转hexString
     * <p>
     * <p>例如：</p>
     * <p>
     * bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
     *
     * @param bytes 字节数组
     * @return 16进制大写字符串
     */

    public static String bytes2HexString(byte[] bytes) {

        if (bytes == null) return null;

        int len = bytes.length;

        if (len <= 0) return null;

        char[] ret = new char[len << 1];

        for (int i = 0, j = 0; i < len; i++) {

            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];

            ret[j++] = hexDigits[bytes[i] & 0x0f];

        }

        return new String(ret);

    }


    /**
     * hexString转byteArr
     * <p>
     * <p>例如：</p>
     * <p>
     * hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }
     *
     * @param hexString 十六进制字符串
     * @return 字节数组
     */

    public static byte[] hexString2Bytes(String hexString) {

        if (StringUtils.isSpace(hexString)) return null;

        int len = hexString.length();

        if (len % 2 != 0) {

            hexString = "0" + hexString;

            len = len + 1;

        }

        char[] hexBytes = hexString.toUpperCase().toCharArray();

        byte[] ret = new byte[len >> 1];

        for (int i = 0; i < len; i += 2) {

            ret[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));

        }

        return ret;

    }


    /**
     * hexChar转int
     *
     * @param hexChar hex单个字节
     * @return 0..15
     */

    private static int hex2Dec(char hexChar) {

        if (hexChar >= '0' && hexChar <= '9') {

            return hexChar - '0';

        } else if (hexChar >= 'A' && hexChar <= 'F') {

            return hexChar - 'A' + 10;

        } else {

            throw new IllegalArgumentException();

        }

    }


    /**
     * charArr转byteArr
     *
     * @param chars 字符数组
     * @return 字节数组
     */

    public static byte[] chars2Bytes(char[] chars) {

        if (chars == null || chars.length <= 0) return null;

        int len = chars.length;

        byte[] bytes = new byte[len];

        for (int i = 0; i < len; i++) {

            bytes[i] = (byte) (chars[i]);

        }

        return bytes;

    }


    /**
     * byteArr转charArr
     *
     * @param bytes 字节数组
     * @return 字符数组
     */

    public static char[] bytes2Chars(byte[] bytes) {

        if (bytes == null) return null;

        int len = bytes.length;

        if (len <= 0) return null;

        char[] chars = new char[len];

        for (int i = 0; i < len; i++) {

            chars[i] = (char) (bytes[i] & 0xff);

        }

        return chars;

    }


    /**
     * 字节数转以unit为单位的size
     *
     * @param byteNum 字节数
     * @param unit    单位类型
     *                <p>
     *                <ul>
     *                <p>
     *                <li>{@link ConstUtils.MemoryUnit#BYTE}: 字节</li>
     *                <p>
     *                <li>{@link ConstUtils.MemoryUnit#KB}  : 千字节</li>
     *                <p>
     *                <li>{@link ConstUtils.MemoryUnit#MB}  : 兆</li>
     *                <p>
     *                <li>{@link ConstUtils.MemoryUnit#GB}  : GB</li>
     *                <p>
     *                </ul>
     * @return 以unit为单位的size
     */

    public static double byte2Size(long byteNum, ConstUtils.MemoryUnit unit) {

        if (byteNum < 0) return -1;

        switch (unit) {

            default:

            case BYTE:

                return (double) byteNum / ConstUtils.BYTE;

            case KB:

                return (double) byteNum / ConstUtils.KB;

            case MB:

                return (double) byteNum / ConstUtils.MB;

            case GB:

                return (double) byteNum / ConstUtils.GB;

        }

    }


    /**
     * 以unit为单位的size转字节数
     *
     * @param size 大小
     * @param unit 单位类型
     *             <p>
     *             <ul>
     *             <p>
     *             <li>{@link ConstUtils.MemoryUnit#BYTE}: 字节</li>
     *             <p>
     *             <li>{@link ConstUtils.MemoryUnit#KB}  : 千字节</li>
     *             <p>
     *             <li>{@link ConstUtils.MemoryUnit#MB}  : 兆</li>
     *             <p>
     *             <li>{@link ConstUtils.MemoryUnit#GB}  : GB</li>
     *             <p>
     *             </ul>
     * @return 字节数
     */

    public static long size2Byte(long size, ConstUtils.MemoryUnit unit) {

        if (size < 0) return -1;

        switch (unit) {

            default:

            case BYTE:

                return size;

            case KB:

                return size * ConstUtils.KB;

            case MB:

                return size * ConstUtils.MB;

            case GB:

                return size * ConstUtils.GB;

        }

    }


    /**
     * 字节数转合适内存大小
     * <p>
     * <p>保留3位小数</p>
     *
     * @param byteNum 字节数
     * @return 合适内存大小
     */

    @SuppressLint("DefaultLocale")

    public static String byte2FitSize(long byteNum) {

        if (byteNum < 0) {

            return "shouldn't be less than zero!";

        } else if (byteNum < ConstUtils.KB) {

            return String.format("%.3fB", (double) byteNum);

        } else if (byteNum < ConstUtils.MB) {

            return String.format("%.3fKB", (double) byteNum / ConstUtils.KB);

        } else if (byteNum < ConstUtils.GB) {

            return String.format("%.3fMB", (double) byteNum / ConstUtils.MB);

        } else {

            return String.format("%.3fGB", (double) byteNum / ConstUtils.GB);

        }

    }


    /**
     * 毫秒时间戳转合适时间长度
     *
     * @param millis 毫秒时间戳
     * @return 合适时间长度
     */

    @SuppressLint("DefaultLocale")

    public static String millis2FitTimeSpan(long millis) {

        if (millis < 0) {

            return "shouldn't be less than zero!";

        } else if (millis < ConstUtils.SEC) {

            return String.format("%d毫秒", millis);

        } else if (millis < ConstUtils.MIN) {

            return String.format("%d秒", millis / ConstUtils.SEC);

        } else if (millis < ConstUtils.HOUR) {

            return String.format("%d分", millis / ConstUtils.MIN);

        } else if (millis < ConstUtils.DAY) {

            return String.format("%d小时", millis / ConstUtils.HOUR);

        } else {

            return String.format("%d天", millis / ConstUtils.DAY);

        }

    }


    /**
     * bytes转bits
     *
     * @param bytes 字节数组
     * @return bits
     */

    public static String bytes2Bits(byte[] bytes) {

        StringBuilder sb = new StringBuilder();

        for (byte aByte : bytes) {

            for (int j = 7; j >= 0; --j) {

                sb.append(((aByte >> j) & 0x01) == 0 ? '0' : '1');

            }

        }

        return sb.toString();

    }


    /**
     * bits转bytes
     *
     * @param bits 二进制
     * @return bytes
     */

    public static byte[] bits2Bytes(String bits) {

        int lenMod = bits.length() % 8;

        int byteLen = bits.length() / 8;

        // 不是8的倍数前面补0

        if (lenMod != 0) {

            for (int i = lenMod; i < 8; i++) {

                bits = "0" + bits;

            }

            byteLen++;

        }

        byte[] bytes = new byte[byteLen];

        for (int i = 0; i < byteLen; ++i) {

            for (int j = 0; j < 8; ++j) {

                bytes[i] <<= 1;

                bytes[i] |= bits.charAt(i * 8 + j) - '0';

            }

        }

        return bytes;

    }


    /**
     * inputStream转outputStream
     *
     * @param is 输入流
     * @return outputStream子类
     */

    public static ByteArrayOutputStream input2OutputStream(InputStream is) {

        if (is == null) return null;

        try {

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            byte[] b = new byte[ConstUtils.KB];

            int len;

            while ((len = is.read(b, 0, ConstUtils.KB)) != -1) {

                os.write(b, 0, len);

            }

            return os;

        } catch (IOException e) {

            e.printStackTrace();

            return null;

        } finally {

            closeIO(is);

        }

    }


    /**
     * outputStream转inputStream
     *
     * @param out 输出流
     * @return inputStream子类
     */

    public ByteArrayInputStream output2InputStream(OutputStream out) {

        if (out == null) return null;

        return new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());

    }


    /**
     * inputStream转byteArr
     *
     * @param is 输入流
     * @return 字节数组
     */

    public static byte[] inputStream2Bytes(InputStream is) {

        if (is == null) return null;

        return input2OutputStream(is).toByteArray();

    }


    /**
     * byteArr转inputStream
     *
     * @param bytes 字节数组
     * @return 输入流
     */

    public static InputStream bytes2InputStream(byte[] bytes) {

        if (bytes == null || bytes.length <= 0) return null;

        return new ByteArrayInputStream(bytes);

    }


    /**
     * outputStream转byteArr
     *
     * @param out 输出流
     * @return 字节数组
     */

    public static byte[] outputStream2Bytes(OutputStream out) {

        if (out == null) return null;

        return ((ByteArrayOutputStream) out).toByteArray();

    }


    /**
     * outputStream转byteArr
     *
     * @param bytes 字节数组
     * @return 字节数组
     */

    public static OutputStream bytes2OutputStream(byte[] bytes) {

        if (bytes == null || bytes.length <= 0) return null;

        ByteArrayOutputStream os = null;

        try {

            os = new ByteArrayOutputStream();

            os.write(bytes);

            return os;

        } catch (IOException e) {

            e.printStackTrace();

            return null;

        } finally {

            closeIO(os);

        }

    }


    /**
     * inputStream转string按编码
     *
     * @param is          输入流
     * @param charsetName 编码格式
     * @return 字符串
     */

    public static String inputStream2String(InputStream is, String charsetName) {

        if (is == null || StringUtils.isSpace(charsetName)) return null;

        try {

            return new String(inputStream2Bytes(is), charsetName);

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

            return null;

        }

    }


    /**
     * string转inputStream按编码
     *
     * @param string      字符串
     * @param charsetName 编码格式
     * @return 输入流
     */

    public static InputStream string2InputStream(String string, String charsetName) {

        if (string == null || StringUtils.isSpace(charsetName)) return null;

        try {

            return new ByteArrayInputStream(string.getBytes(charsetName));

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

            return null;

        }

    }


    /**
     * outputStream转string按编码
     *
     * @param out         输出流
     * @param charsetName 编码格式
     * @return 字符串
     */

    public static String outputStream2String(OutputStream out, String charsetName) {

        if (out == null || StringUtils.isSpace(charsetName)) return null;

        try {

            return new String(outputStream2Bytes(out), charsetName);

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

            return null;

        }

    }


    /**
     * string转outputStream按编码
     *
     * @param string      字符串
     * @param charsetName 编码格式
     * @return 输入流
     */

    public static OutputStream string2OutputStream(String string, String charsetName) {

        if (string == null || StringUtils.isSpace(charsetName)) return null;

        try {

            return bytes2OutputStream(string.getBytes(charsetName));

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

            return null;

        }

    }


    /**
     * bitmap转byteArr
     *
     * @param bitmap bitmap对象
     * @param format 格式
     * @return 字节数组
     */

    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat format) {

        if (bitmap == null) return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(format, 100, baos);

        return baos.toByteArray();

    }


    /**
     * byteArr转bitmap
     *
     * @param bytes 字节数组
     * @return bitmap
     */

    public static Bitmap bytes2Bitmap(byte[] bytes) {

        return (bytes == null || bytes.length == 0) ? null : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

    }


    /**
     * drawable转bitmap
     *
     * @param drawable drawable对象
     * @return bitmap
     */

    public static Bitmap drawable2Bitmap(Drawable drawable) {

        return drawable == null ? null : ((BitmapDrawable) drawable).getBitmap();

    }


    /**
     * bitmap转drawable
     *
     * @param res    resources对象
     * @param bitmap bitmap对象
     * @return drawable
     */

    public static Drawable bitmap2Drawable(Resources res, Bitmap bitmap) {

        return bitmap == null ? null : new BitmapDrawable(res, bitmap);

    }


    /**
     * drawable转byteArr
     *
     * @param drawable drawable对象
     * @param format   格式
     * @return 字节数组
     */

    public static byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat format) {

        return drawable == null ? null : bitmap2Bytes(drawable2Bitmap(drawable), format);

    }


    /**
     * byteArr转drawable
     *
     * @param res   resources对象
     * @param bytes 字节数组
     * @return drawable
     */

    public static Drawable bytes2Drawable(Resources res, byte[] bytes) {

        return res == null ? null : bitmap2Drawable(res, bytes2Bitmap(bytes));

    }


    /**
     * view转Bitmap
     *
     * @param view 视图
     * @return bitmap
     */

    public static Bitmap view2Bitmap(View view) {

        if (view == null) return null;

        Bitmap ret = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(ret);

        Drawable bgDrawable = view.getBackground();

        if (bgDrawable != null) {

            bgDrawable.draw(canvas);

        } else {

            canvas.drawColor(Color.WHITE);

        }

        view.draw(canvas);

        return ret;

    }


    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */

    public static int dp2px(Context context, float dpValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);

    }


    /**
     * px转dp
     *
     * @param context 上下文
     * @param pxValue px值
     * @return dp值
     */

    public static int px2dp(Context context, float pxValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);

    }


    /**
     * sp转px
     *
     * @param context 上下文
     * @param spValue sp值
     * @return px值
     */

    public static int sp2px(Context context, float spValue) {

        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;

        return (int) (spValue * fontScale + 0.5f);

    }


    /**
     * px转sp
     *
     * @param context 上下文
     * @param pxValue px值
     * @return sp值
     */

    public static int px2sp(Context context, float pxValue) {

        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;

        return (int) (pxValue / fontScale + 0.5f);

    }

    /**

     * 关闭IO

     *

     * @param closeables closeable

     */

    public static void closeIO(Closeable... closeables) {

        if (closeables == null) return;

        for (Closeable closeable : closeables) {

            if (closeable != null) {

                try {

                    closeable.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

    }



    /**

     * 安静关闭IO

     *

     * @param closeables closeable

     */

    public static void closeIOQuietly(Closeable... closeables) {

        if (closeables == null) return;

        for (Closeable closeable : closeables) {

            if (closeable != null) {

                try {

                    closeable.close();

                } catch (IOException ignored) {

                }

            }

        }

    }

}