package writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.MyRecord;

import java.nio.file.Path;
import java.util.List;

public class JsonSourceWriter implements SourceWriter {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Path path;

    public JsonSourceWriter(Path path) {
        this.path = path;
    }

    @Override
    public void write(List<MyRecord> data) throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), data);
    }
}
