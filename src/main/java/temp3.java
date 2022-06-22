import java.util.Arrays;
import java.util.stream.Collectors;

public class temp3 {

    public static void main(String[] args) {

        String[] data = "Hello".split("");
        System.out.println(Arrays.toString(data));

        String collect = Arrays.stream(data).distinct().sorted().collect(Collectors.joining());
        System.out.println(collect);
    }
}
