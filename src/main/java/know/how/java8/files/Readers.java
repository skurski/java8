package know.how.java8.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Streams should BE CLOSE when dealing with Input/Output.
 * Streams implements AutoCloseable so can be used in try with resources.
 */
public class Readers {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("input.txt");
        System.out.println("Path for input file: " + path.toAbsolutePath() + "\n");

        try (Stream<String> lines = Files.lines(path)){

            lines.map(String::toUpperCase)
                    .filter(s -> s.contains("IT"))
                    .forEach(System.out::println);
        }
    }
}
