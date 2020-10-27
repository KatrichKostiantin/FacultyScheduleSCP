import java.util.List;
import java.util.PriorityQueue;

public class SCP {
    private Timetable mainTimetable;
    private List<Student> studentList;
    private List<Teacher> teacherList;

    private PriorityQueue<NodeTemplate> mainQueue;

    public SCP(Timetable mainTimetable, List<Student> studentList, List<Teacher> teacherList) {
        this.mainTimetable = mainTimetable;
        this.studentList = studentList;
        this.teacherList = teacherList;
    }

    public void startMainProcess(){

    }
}
