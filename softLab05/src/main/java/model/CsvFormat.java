package model;

import java.nio.file.Path;

public record CsvFormat(Path path) implements Output{}
