import java.util.ArrayList;
import java.util.List;

public class StudyPair {
    private List<Student> students;
    private Teacher teacher;
    private Discipline.Lesson lesson;

    public StudyPair() {
        this.students = new ArrayList<>();
    }

    public StudyPair(StudyPair[] studyPairs) {

    }

    public StudyPair(StudyPair studyPair) {
        students = new ArrayList<>();
        this.students.addAll(studyPair.students);
        this.teacher = studyPair.teacher;
        this.lesson = studyPair.lesson;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline.Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Discipline.Lesson lesson) {
        this.lesson = lesson;
    }
}
