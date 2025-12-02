package writers;

import model.CsvFormat;
import model.JsonFormat;
import model.Output;

public class WriterFactory {
    public SourceWriter create(Output output){
        return switch (output){
            case JsonFormat format -> new JsonSourceWriter(format.path());
            case CsvFormat f -> new CsvSourceWriter(f.path());
        };
    }
}
