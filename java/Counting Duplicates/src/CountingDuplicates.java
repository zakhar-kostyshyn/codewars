import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class CountingDuplicates {

    public static int duplicateCount(String text) {
        return (int) text.toLowerCase().chars()
                .boxed()
                .collect(groupingBy(
                        identity(),
                        counting()
                )).values().stream()
                    .filter(v -> v > 1)
                    .count();
    }

}


