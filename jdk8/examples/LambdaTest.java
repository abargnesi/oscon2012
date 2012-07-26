import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterables;
import java.util.ParallelIterable;
import java.util.ParallelIterables;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.System.out;

// juicy bits
import java.util.functions.*;

public class LambdaTest {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(final String[] args) throws ParseException {
        final List<Person> people = new ArrayList<>();
        people.add(new Person("Tony", 30, 1));
        people.add(new Person("William", 40, 2));
        people.add(new Person("Jordan", 28, 1));
        people.add(new Person("Nick", 33, 2));
        people.add(new Person("James", 29, 0));

        // forEach with Lambda
        out.println("forEach person, print name | age | num children");
        people.forEach(p -> {out.println(p.getName() + "\t\t" + p.getAge() + "\t\t" + p.getNumChildren());});

        out.println("");

        // filter
        out.println("filter people with children, print name");
        Predicate<Person> encumbered = p -> p.getNumChildren() > 0;
        Iterable<Person> parents = people.filter(encumbered);
        parents.forEach(p -> {out.println(p.getName());});

        out.println("");

        // map
        out.println("map people to their \"parental\" age, print age");
        Mapper<Person, Integer> parentalAge = x -> x.getAge() + (x.getNumChildren() * 4);
        Iterable<Integer> ages = Iterables.map(people, parentalAge);
        ages.forEach(i -> {out.println(i);});

        out.println("");

        // reduce
        BinaryOperator<Person> oldest = (x,y) -> {
            if (x.getNumChildren() > y.getNumChildren()) {
                return x;
            } else if (x.getNumChildren() < y.getNumChildren()) {
                return y;
            } else {
                return x.getAge() > y.getAge() ? x : y;
            }
        };
        Person wisest = Iterables.reduce(people, people.get(0), oldest);
        out.println("Wisest Person: " + wisest.getName());

        // filter/map/reduce
        int numChildren = people
            .filter(p -> p.getAge() > 30)
            .map(p -> p.getNumChildren())
            .reduce(0, (x,y) -> x+y);
        out.println("Number of children for People over 30: " + numChildren);

        out.println("");

        // methods of java.util.Iterables are available
        out.println("First person in list: " + people.getFirst().getName());
        out.println("Any 20-somethings: " + people.anyMatch(p -> p.getAge() < 30));
        out.println("All of legal drinking age: " + people.allMatch(p -> p.getAge() >= 21));

        out.println("");

        out.print("Sort people by name: ");
        Iterable<Person> sorted = people.sorted((p1,p2) -> p1.getName().compareTo(p2.getName()));
        sorted.forEach(p -> out.print(p.getName() + " "));

        out.println("");

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            numbers.add(i);
        }
        
        out.println("Mapped 1000000 integers to binary strings.");

        Integer[] ia = new Integer[numbers.size()];
        numbers.toArray(ia);

        long start = System.currentTimeMillis();
        String[] binaryArray = new String[numbers.size()];
        for (int i = 0; i < ia.length; i++) {
            binaryArray[i] = Integer.toBinaryString(i);
        }
        long end = System.currentTimeMillis();
        out.println("Sequential Time: " + ((end - start) / 1000f));

        ParallelIterable<Integer> parallelArray = Arrays.parallel(ia);

        start = System.currentTimeMillis();
        ParallelIterable<String> binaryStrings = ParallelIterables
            .map(parallelArray, i -> Integer.toBinaryString(i));
        end = System.currentTimeMillis();
        out.println("Parallell Time (Map): " + ((end - start) / 1000f));

    }
}

