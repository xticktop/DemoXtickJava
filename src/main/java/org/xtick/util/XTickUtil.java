package org.xtick.util;

import org.xtick.bean.QuantPacket;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class XTickUtil {
    public static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (Exception e) {
            System.err.println(String.format("休眠失败。" + e.getMessage()));
        }
    }

    public static String processData(InputStream inputStream) {
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().endsWith(".json")) {
                    byte[] buffer = new byte[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        sb.append(new String(buffer, 0, len));
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            System.err.println(String.format("解析数据失败。" + e.getMessage()));
        }
        return "";
    }

    public static String processGZipData(InputStream inputStream) {
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
            byte[] buffer = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = gzipInputStream.read(buffer)) > 0) {
                sb.append(new String(buffer, 0, len));
            }
            return sb.toString();
        } catch (Exception e) {
            System.err.println(String.format("解析数据失败。" + e.getMessage()));
        }
        return "";
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }

    public static void processData(byte[] data, Consumer<String> consumer) {
        if (detectCompressionFormat(data).equals("gzip")) {
            processGzipData(data, consumer);
        } else if (detectCompressionFormat(data).equals("zip")) {
            processZipData(data, consumer);
        } else {
            consumer.accept(new String(data));
        }
    }

    public static String detectCompressionFormat(byte[] datas) {
        if ((datas.length >= 4) && ((datas[0] & 0xFF) == 0x50 && (datas[1] & 0xFF) == 0x4B) && (
                ((datas[2] & 0xFF) == 0x03 && (datas[3] & 0xFF) == 0x04) ||
                        ((datas[2] & 0xFF) == 0x05 && (datas[3] & 0xFF) == 0x06) ||
                        ((datas[2] & 0xFF) == 0x07 && (datas[3] & 0xFF) == 0x08)
        )) {
            return "zip";
        } else if (datas.length >= 2 && ((datas[0] & 0xFF) == 0x1F && (datas[1] & 0xFF) == 0x8B)) {
            return "gzip";
        }
        return "unknown";
    }

    public static void processZipData(byte[] data, Consumer<String> consumer) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().endsWith(".json")) {
                    byte[] buffer = new byte[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        sb.append(new String(buffer, 0, len));
                    }
                    consumer.accept(sb.toString());
                    return;
                }
            }
            consumer.accept(convertInputStreamToString(inputStream));
        } catch (Exception e) {
            System.err.println(String.format("解析数据失败。" + e.getMessage()));
        }
    }

    public static void processGzipData(byte[] data, Consumer<String> consumer) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
            byte[] buffer = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = gzipInputStream.read(buffer)) > 0) {
                sb.append(new String(buffer, 0, len));
            }
            consumer.accept(sb.toString());
        } catch (Exception e) {
            System.err.println(String.format("解析数据失败。" + e.getMessage()));
        }
    }



}
