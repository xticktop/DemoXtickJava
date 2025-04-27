package org.xtick.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class XTickUtil {
    public static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (Exception e) {
            log.info("Failed to sleep ", e);
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
            return convertInputStreamToString(inputStream);
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

    public static void processData(InputStream inputStream, Consumer<String> consumer) {
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
                }
            }
            consumer.accept(convertInputStreamToString(inputStream));
        } catch (Exception e) {
            System.err.println(String.format("解析数据失败。" + e.getMessage()));
        }
    }
}
