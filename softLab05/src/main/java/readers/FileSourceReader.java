package readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSourceReader implements SourceReader{
    private final Path path;

    public FileSourceReader(Path path) {
        this.path = path;
    }

    @Override
    public List<Record> read() throws IOException {
        return Files.lines(path).map(Record::fromCsvLine).toList();
    }
}
