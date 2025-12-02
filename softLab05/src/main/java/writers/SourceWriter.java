package writers;

import java.util.List;

public interface SourceWriter {
    void write(List<Record> data) throws Exception;
}
