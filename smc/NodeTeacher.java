import java.util.ArrayList;
import java.util.List;

public class NodeTeacher extends NodeTemplate{
    private static ArrayList<Teacher> allPossibleValues;
    /* Может надо будет через id реализовать equals, но вроде сслыками нормально будет хз
	private final int Id;
	private static int freeId = 0; */

    private ArrayList<Teacher> possibleValues = new ArrayList<>();
    private Teacher substitutedValue;
    private NodeLesson possibleLesson;
    private boolean isCheck = false;

    private ArrayList<NodeTeacher> nodeConnection;

    public NodeTeacher(ArrayList<Teacher> possibleValues) {
        if (allPossibleValues == null)
            allPossibleValues = possibleValues;
        possibleValues.addAll(allPossibleValues);
    }

    public NodeTeacher() {
        possibleValues.addAll(allPossibleValues);
    }

    public void setNodeConnection(ArrayList<NodeTeacher> nodeConnection) {
        ArrayList<NodeTeacher> newNodeConnection = new ArrayList<>(nodeConnection);
        newNodeConnection.remove(this);
        this.nodeConnection = newNodeConnection;
    }

    public void startChecking() {
        for (NodeTeacher nodeTeacher : nodeConnection) {
            nodeTeacher.resetPossibleValues();
        }
        isCheck = true;
        for (NodeTeacher nodeTeacher : nodeConnection) {
            nodeTeacher.forwardChecking(this);
        }
    }

    private void resetPossibleValues() {
        possibleValues.clear();
        possibleValues.addAll(allPossibleValues);
        isCheck = false;
    }

    public void forwardChecking(NodeTeacher checkingFrom) {
        if (substitutedValue != null){
            possibleValues.remove(checkingFrom.getSubstitutedValue());
            possibleLesson.forwardChecking(this);
        }
        if (!isCheck)
            for (NodeTeacher nodeTeacher : nodeConnection) {
                nodeTeacher.forwardChecking(this);
            }
        isCheck = true;
    }

    public List<Teacher> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(ArrayList<Teacher> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public Teacher getSubstitutedValue() {
        return substitutedValue;
    }

    public void setSubstitutedValue(Teacher substitutedValue) {
        this.substitutedValue = substitutedValue;
    }
}
