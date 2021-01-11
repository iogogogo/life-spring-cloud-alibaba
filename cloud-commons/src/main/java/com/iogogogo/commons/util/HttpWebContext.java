package com.iogogogo.commons.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tao.zeng on 2021/1/8.
 */
@Slf4j
public class HttpWebContext {

    public static void writer(HttpServletRequest request, HttpServletResponse response, Object data) {
        try (PrintWriter writer = response.getWriter()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            writer.write(JsonParse.toJson(data));
            writer.flush();
        } catch (IOException e) {
            log.error("HttpWebContext writer failure", e);
        }
    }
}
