package org.example.tomcat.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class PayloadCachingRequestWrapper extends HttpServletRequestWrapper {

    private static final int BUFFER_SIZE = 128;
    private String payload;

    public PayloadCachingRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        InputStream stream = request.getInputStream();
        if (stream == null) return;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[BUFFER_SIZE];
            while (true) {
                int readCount = reader.read(buffer);
                if (readCount == -1) break;
                sb.append(buffer, 0, readCount);
            }
            this.payload = sb.toString();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        return new ServletInputStream() {

            private final ByteArrayInputStream sourceStream = new ByteArrayInputStream(payload.getBytes());
            private boolean finished = false;

            @Override
            public int read() {
                int data = this.sourceStream.read();
                if (data == -1) {
                    this.finished = true;
                }
                return data;
            }

            @Override
            public int available() {
                return this.sourceStream.available();
            }

            @Override
            public void close() throws IOException {
                super.close();
                this.sourceStream.close();
            }

            @Override
            public boolean isFinished() {
                return this.finished;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException();
            }

        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getPayload() {
        return this.payload;
    }

}
