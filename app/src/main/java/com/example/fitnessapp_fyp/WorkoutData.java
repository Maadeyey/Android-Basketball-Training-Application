package com.example.fitnessapp_fyp;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutData {

    public static List<String[]> loadCSV(String filePath) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> data = new ArrayList<>();
        String[] line;
        while ((line = reader.readNext()) != null) {
            data.add(line);
        }
        reader.close();
        return data;
    }

    public static void main(String[] args) throws IOException, CsvValidationException {
        List<String[]> data = loadCSV("app/src/main/assets/megaGymDataset.csv");
        // process the data as needed
    }

}
