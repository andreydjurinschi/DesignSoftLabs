package readers;

import model.MyRecord;

import java.util.List;

public interface SourceReader {
    List<MyRecord> read() throws Exception;
}
