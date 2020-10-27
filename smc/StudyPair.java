import java.util.ArrayList;
import java.util.List;

public class StudyPair {
    private List<NodeStudent> students;
    private NodeTeacher teacher;
    private NodeLesson lesson;

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

    public List<NodeStudent> getStudents() {
        return students;
    }

    public void setStudents(List<NodeStudent> students) {
        this.students = students;
    }

    public NodeTeacher getTeacher() {
        return teacher;
    }

    public void setTeacher(NodeTeacher teacher) {
        this.teacher = teacher;
    }

    public NodeLesson getLesson() {
        return lesson;
    }

    public void setLesson(NodeLesson lesson) {
        this.lesson = lesson;
    }
}
