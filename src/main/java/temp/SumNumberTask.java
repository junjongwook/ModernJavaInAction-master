package temp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class SumNumberTask extends RecursiveTask<Integer> {

    private final List<Integer> numbers;
    
    public SumNumberTask(List<Integer> numbers) {

        this.numbers = numbers;
    }
    
    @Override
    protected Integer compute() {

        if (numbers.size() <= 25) {
            
            return numbers.stream().mapToInt(Integer::intValue).sum();
        }
        List<SumNumberTask> subTasks = partitioningTask(this.numbers);
        
        return ForkJoinTask.invokeAll(subTasks)
            .stream()
            .mapToInt(ForkJoinTask::join)
            .sum();
    }

    private List<SumNumberTask> partitioningTask(List<Integer> numbers) {

        List<Integer> sharedFirst = numbers.subList(0, numbers.size() / 2);
        List<Integer> sharedSecond = numbers.subList(numbers.size() / 2, numbers.size());

        SumNumberTask firstTask = new SumNumberTask(sharedFirst);
        SumNumberTask secondTask = new SumNumberTask(sharedSecond);

        List<SumNumberTask> subtasks = new ArrayList<>();
        subtasks.add(firstTask);
        subtasks.add(secondTask);

        return subtasks;
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
       
        List<Integer> numbers = makeRandomNumbers();
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum + " <=== single thread");

        SumNumberTask sumNumberTask = new SumNumberTask(numbers);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(sumNumberTask);
        Integer integer = submit.get();
        System.out.println(integer + " <=== multi thread");
    }

    private static List<Integer> makeRandomNumbers() {

        return new Random().ints(1000, 1, 100)
            .boxed()
            .collect(Collectors.toList());
    }

}
