    package writers;

    import model.MyRecord;

    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.List;

    public class CsvSourceWriter implements SourceWriter{
        private final Path path;

        public CsvSourceWriter(Path path) {
            this.path = path;
        }


        @Override
        public void write(List<MyRecord> data) throws Exception {
            var lines = data.stream().map(MyRecord::toCsvFile).toList();
            Files.write(path, lines);
        }
    }
