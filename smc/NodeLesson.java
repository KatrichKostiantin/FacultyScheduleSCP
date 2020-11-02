import java.util.ArrayList;

public class NodeLesson extends NodeTemplate implements Comparable<NodeLesson>{
 /* Может надо будет через id реализовать equals, но вроде сслыками нормально будет хз
	private final int Id;
	private static int freeId = 0; */

    private ArrayList<Discipline.Lesson> possibleValues;
    private static ArrayList<Discipline.Lesson> allPossibleValues;
    private ArrayList<NodeStudent> connectionNodeStudents;
    private Discipline.Lesson substitutedValue;

    public NodeLesson(ArrayList<NodeStudent> connectionNodeStudents, ArrayList<Discipline.Lesson> allPossibleValues){
        this.connectionNodeStudents = connectionNodeStudents;
        if(allPossibleValues == null)
            NodeLesson.allPossibleValues = allPossibleValues;
    }
    public NodeLesson(ArrayList<NodeStudent> connectionNodeStudents){
        this.connectionNodeStudents = connectionNodeStudents;
    }

    public ArrayList<Discipline.Lesson> getPossibleValues() {
        return possibleValues;
    }

    public void forwardChecking(NodeTeacher nodeTeacher) {
        for(Teacher teacher: nodeTeacher.getPossibleValues()){
            for(Discipline.Lesson lesson: allPossibleValues){
                if(teacher.canTeach(lesson)) possibleValues.add(lesson);
            }
        }

        for(NodeStudent nodeStudent: connectionNodeStudents)
            nodeStudent.beforeForwardChecking(this);

        connectionNodeStudents.get(0).startChecking();

    }

    @Override
    public int compareTo(NodeLesson o) {
        return 0;
    }
}
