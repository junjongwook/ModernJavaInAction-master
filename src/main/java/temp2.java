import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class temp2 {

    public static void main(String[] args) {

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(4, 5);

        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> { return
                    numbers2.stream()
                            .map(j -> new int[] {i, j});
                })
                .filter(a -> (a[0] + a[1]) % 3 == 0)
                .collect(Collectors.toList());

        pairs.stream()
                .map(Arrays::toString)
                .forEach(System.out::println);
    }
}
