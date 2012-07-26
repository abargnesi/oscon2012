import java.util.Date;

/**
 * Simple contrived Person class.
 */
public class Person {
    private String name;
    private Date birth;
    private int age;

    public Person(final String name, final Date birth, final int age) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("name cannot be null");
        }

        if (birth == null) {
            throw new IllegalArgumentException("birth cannot be null");
        }

        if (age <= 0) {
            throw new IllegalArgumentException("age cannot be less or equal to zero");
        }

        this.name = name;
        this.birth = birth;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Date getBirth() {
        return birth;
    }

    public int getAge() {
        return age;
    }
}

