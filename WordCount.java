import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java WordCount <inputFileName> <outputFileName>");
            System.exit(1);
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        Map<String, Integer> wordCount = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName)) ) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().toLowerCase(); // Convert to lowercase to make it case-insensitive
                wordCount.put(line, wordCount.getOrDefault(line, 0) + 1);
            }
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
            System.exit(1);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing the output file: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Word count results have been written to " + outputFileName);
    }
}
