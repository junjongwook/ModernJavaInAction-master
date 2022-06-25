import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class temp5 {

    public static void main(String[] args) {

        Stream<int[]> p = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(1, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1.0 == 0)
                        .mapToObj(b -> new int[] {a, b, (int) Math.sqrt(a * a + b * b)})
                );
        p.limit(20).map(Arrays::toString).forEach(System.out::println);

    }
}
