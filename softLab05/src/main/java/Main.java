import model.JsonFormat;
import model.Output;
import processingService.ProcessingService;
import readers.SourceReader;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {

        SourceReader reader = new ResourceSourceReader("holidays.csv");

        Output format = new JsonFormat(Path.of("output.json"));

        System.out.println(Path.of("output.json").toAbsolutePath());

        ProcessingService service = DIContainer.build(format, reader);

        service.execute();
    }
}
