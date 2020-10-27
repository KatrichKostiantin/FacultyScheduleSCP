import java.util.List;
import java.util.Objects;

public class Teacher {
    private static int freeId = 0;
    private final int Id;
    private String name;
    private List<Discipline> lectureDisciplines, practiceDisciplines;

    public Teacher(String name, List<Discipline> lectureDisciplines, List<Discipline> practiceDisciplines) {
        Id = freeId++;
        this.name = name;
        this.lectureDisciplines = lectureDisciplines;
        this.practiceDisciplines = practiceDisciplines;
    }

    public int getId() {
        return Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Id == teacher.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public List<Discipline> getLectureDisciplines() {
        return lectureDisciplines;
    }

    public void setLectureDisciplines(List<Discipline> lectureDisciplines) {
        this.lectureDisciplines = lectureDisciplines;
    }

    public List<Discipline> getPracticeDisciplines() {
        return practiceDisciplines;
    }

    public void setPracticeDisciplines(List<Discipline> practiceDisciplines) {
        this.practiceDisciplines = practiceDisciplines;
    }

    public boolean canTeach(Discipline.Lesson lesson) {
        if (lesson.getTypeLesson() == TypeLesson.Lecture) {
            for (Discipline discipline : lectureDisciplines)
                if (discipline.getId() == lesson.getDisciplineId())
                    return true;
            return false;
        } else {
            for (Discipline discipline : lectureDisciplines)
                if (discipline.getId() == lesson.getDisciplineId())
                    return true;
            for (Discipline discipline : practiceDisciplines)
                if (discipline.getId() == lesson.getDisciplineId())
                    return true;
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
