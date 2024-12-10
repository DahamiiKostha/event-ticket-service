package com.oop.eventticketingsystem.service.logs;

import com.oop.eventticketingsystem.util.Global;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *A class for LogReaderService connecting to fontend
 */
@Service
public class LogReaderService {

    private long lastReadPosition = 0;

    public String readNewLogs() throws IOException {
        StringBuilder newLogs = new StringBuilder();

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(Global.LOG_PATH, "r")) {
            randomAccessFile.seek(lastReadPosition);
            String line;
            while ((line = randomAccessFile.readLine()) != null) {
                newLogs.append(line).append(System.lineSeparator());
            }
            lastReadPosition = randomAccessFile.getFilePointer();
        }

        return newLogs.toString();
    }
}

