import model.MyRecord;
import readers.SourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ResourceSourceReader implements SourceReader {

    private final String resourceName;

    public ResourceSourceReader(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public List<MyRecord> read() throws IOException {
        try (var is = getClass().getClassLoader().getResourceAsStream(resourceName);
             var reader = new BufferedReader(new InputStreamReader(is))) {

            return reader.lines()
                    .map(MyRecord::fromCsvFile)
                    .toList();
        }
    }
}
