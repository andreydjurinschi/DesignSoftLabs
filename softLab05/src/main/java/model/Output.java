package model;

import java.nio.file.Path;

public sealed interface Output permits CsvFormat, JsonFormat { }


