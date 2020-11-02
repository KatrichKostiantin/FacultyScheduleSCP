import java.util.ArrayList;

public class NodeStudent extends NodeTemplate implements Comparable<NodeStudent>{

 /* Может надо будет через id реализовать equals, но вроде сслыками нормально будет хз
	private final int Id;
	private static int freeId = 0; */

    private static ArrayList<Student> allPossibleValues;
    private ArrayList<Student> possibleValues;
    private ArrayList<NodeStudent> connectionNodeStudents;
    private Student substitutedValue;
    private boolean isCheck = false;

    public NodeStudent(ArrayList<Student> allPossibleValues) {
        if (allPossibleValues == null)
            NodeStudent.allPossibleValues = allPossibleValues;
    }

    public NodeStudent() {
    }

    public ArrayList<Student> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(ArrayList<Student> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public void setConnectionNodeStudents(ArrayList<NodeStudent> connectionNodeStudents) {
        ArrayList<NodeStudent> newConnectionNodeStudents = new ArrayList<>(connectionNodeStudents);
        newConnectionNodeStudents.remove(this);
        this.connectionNodeStudents = newConnectionNodeStudents;
    }

    public void beforeForwardChecking(NodeLesson nodeLesson) {
        possibleValues.clear();
        isCheck = false;
        for (Student student : allPossibleValues)
            for (Discipline.Lesson lesson : nodeLesson.getPossibleValues())
                if (student.getStudyDisciplines().contains(lesson.getDiscipline()))
                    possibleValues.add(student);

    }

    public void startChecking() {
        isCheck = true;
        for (NodeStudent nodeStudent : connectionNodeStudents) {
            nodeStudent.forwardChecking(this);
        }
    }


    public ArrayList<NodeStudent> getConnectionNodeStudents() {
        return connectionNodeStudents;
    }

    public Student getSubstitutedValue() {
        return substitutedValue;
    }

    public void forwardChecking(NodeStudent checkingFrom){
        if (substitutedValue != null){
            possibleValues.remove(checkingFrom.getSubstitutedValue());
        }
        if (!isCheck)
            for (NodeStudent nodeStudent : connectionNodeStudents) {
                nodeStudent.forwardChecking(this);
            }
        isCheck = true;
    }

    @Override
    public int compareTo(NodeStudent o) {
        return 0;
    }
}
