package model;

import java.time.LocalDate;

public record MyRecord(int id, String name, LocalDate date) {

    public static MyRecord fromCsvFile(String line){
        String[] parts = line.split(",");
        String timeStr = String.valueOf(parts[2].trim());
        int id = Integer.parseInt(parts[0].trim());
        String name = String.valueOf(parts[1].trim());
        LocalDate date = LocalDate.parse(timeStr);
        return new MyRecord(id, name, date);
    }

    public String toCsvFile(){
        return id + "," + name + "," + date.toString();
    }
}
