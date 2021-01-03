package com.iogogogo.commons.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.Closeable;

/**
 * Created by tao.zeng on 2021/1/3.
 */
@Slf4j
public class IoUtils {

    public static void close(Closeable... closeables) {
        if (closeables == null || closeables.length <= 0) return;
        for (Closeable io : closeables) {
            IOUtils.closeQuietly(io);
        }
    }
}
