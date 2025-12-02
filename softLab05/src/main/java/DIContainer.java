import model.MyRecord;
import model.Output;
import processingService.ProcessingService;
import readers.SourceReader;
import writers.SourceWriter;
import writers.WriterFactory;

import java.util.List;
import java.util.function.Function;

public class DIContainer {
    public static ProcessingService build(Output format, SourceReader reader) {
        WriterFactory factory = new WriterFactory();
        SourceWriter writer = factory.create(format);

        List<Function<List<MyRecord>, List<MyRecord>>> steps = List.of(); // нет обработки

        return new ProcessingService(writer,reader, steps);
    }

}
