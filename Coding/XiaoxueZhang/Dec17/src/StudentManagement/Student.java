package StudentManagement;
//- Create `Student` class:
//  - `private String id`, `private String name`, `private int score`
//  - Constructor, getters, and `@Override toString()`
//  - Override `equals()` and `hashCode()` based on id only
public class Student {
    private String id;
    private String name;
    private int score;

    public Student(String id, String name,int score){
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Student other = (Student) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
