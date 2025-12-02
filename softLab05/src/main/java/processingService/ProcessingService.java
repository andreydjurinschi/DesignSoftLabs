package processingService;

import model.MyRecord;
import readers.SourceReader;
import writers.SourceWriter;

import java.util.List;
import java.util.function.Function;

public class ProcessingService {
    private final SourceWriter writer;
    private final SourceReader reader;
    private final List<Function<List<MyRecord>, List<MyRecord>>> steps;

    public ProcessingService(SourceWriter writer, SourceReader reader, List<Function<List<MyRecord>, List<MyRecord>>> steps) {
        this.writer = writer;
        this.reader = reader;
        this.steps = steps;
    }

    public void execute() throws Exception {
        List<MyRecord> data = reader.read();

        for (var func : steps) {
            data = func.apply(data);
        }

        writer.write(data);
    }
}
