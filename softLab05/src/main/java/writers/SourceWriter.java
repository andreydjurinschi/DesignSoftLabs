package writers;

import model.MyRecord;

import java.util.List;

public interface SourceWriter {
    void write(List<MyRecord> data) throws Exception;
}
