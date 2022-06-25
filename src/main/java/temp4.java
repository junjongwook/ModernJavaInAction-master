import java.util.Arrays;
import java.util.stream.IntStream;

public class temp4 {

    public static void main(String[] args) {

        IntStream stream = IntStream.rangeClosed(1, 100);
        int[] ints = stream.toArray();

        System.out.println(Arrays.toString(ints));
        Arrays.stream(ints).limit(5)
                .forEach(System.out::println);
    }
}
