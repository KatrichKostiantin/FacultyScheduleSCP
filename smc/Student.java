import java.util.List;
import java.util.Objects;

public class Student {
    private final int Id;
    private static int freeId = 0;

    public int getId() {
        return Id;
    }
    private String name;
    private List<Discipline> studyDisciplines;

    public Student(String name, List<Discipline> studyDisciplines) {
        Id = freeId++;
        this.name = name;
        this.studyDisciplines = studyDisciplines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Id == student.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public List<Discipline> getStudyDisciplines() {
        return studyDisciplines;
    }

    public void setStudyDisciplines(List<Discipline> studyDisciplines) {
        this.studyDisciplines = studyDisciplines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
