package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    private static final ObjectMapper objectMapper =
            new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static <T> void writeJson(List<T> list, String path) {
        try {
            final var fileOutputStream = new FileOutputStream(path);
            objectMapper.writeValue(fileOutputStream, list);
            fileOutputStream.close();
        } catch (IOException ioException) {
            ioException.fillInStackTrace();
        }
    }
}
