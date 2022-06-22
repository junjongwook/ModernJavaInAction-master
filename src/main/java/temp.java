import modernjavainaction.chap01.FilteringApples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class temp {

    public static void main(String[] args) {

        Runnable r1 = () -> { System.out.println("Hello, Runnable"); };
        process(r1);

        process(() -> System.out.println("Hello, ahahaha"));
    }

    public static void process(Runnable r) {

        r.run();
    }
}