package readers;

import java.util.List;

public interface SourceReader {
    List<Record> read() throws Exception;
}
