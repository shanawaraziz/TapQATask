package org.task.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class RequestPayload {
    public static String getRequestPayload() throws IOException {
        String fileName = "requestBody.json";

        InputStream is = RequestPayload.class.getClassLoader().getResourceAsStream("requestBody.json");
        if (is == null) {
            throw new IOException("File not found: " + fileName);
        }
        return IOUtils.toString(is, StandardCharsets.UTF_8);
    }
}
