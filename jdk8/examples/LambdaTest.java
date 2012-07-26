import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

public class LambdaTest {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(final String[] args) throws ParseException {
        final List<Person> people = new ArrayList<Person>();
        people.add(new Person("Tony", df.parse("1974-03-03"), 30));
        people.add(new Person("William", df.parse("1984-03-03"), 24));
        people.add(new Person("Jordan", df.parse("1984-07-03"), 42));
        people.add(new Person("Nick", df.parse("1991-04-03"), 19));
        people.add(new Person("James", df.parse("1980-09-03"), 52));
    }
}

