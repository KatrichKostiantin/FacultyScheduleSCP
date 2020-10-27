import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Timetable {
    private static final int DAYS_FOR_STUDY = 5;
    private static final int PAIR_PER_DAY = 7;
    private ArrayList<StudyDay> studyDays;
    private List<Classroom> classrooms;

    public Timetable(List<Classroom> classrooms) {
        studyDays = new ArrayList<>();
        this.classrooms = classrooms;
        for (int i = 0; i < DAYS_FOR_STUDY; i++)
            studyDays.add(new StudyDay(PAIR_PER_DAY, classrooms));
    }

    public List<StudyDay> getStudyDays() {
        return studyDays;
    }

    public void setStudyDays(ArrayList<StudyDay> studyDays) {
        this.studyDays = studyDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyDays);
    }

    class StudyDay {
        private HashMap<Classroom, StudyPair[]> classroomPairMap;

        public StudyDay(StudyDay classroomPairMap) {
            this.classroomPairMap = new HashMap<>();
            for (Classroom key : classroomPairMap.classroomPairMap.keySet()) {
                StudyPair[] old = classroomPairMap.classroomPairMap.get(key);
                StudyPair[] temp = new StudyPair[old.length];
                for (int i = 0; i < old.length; i++)
                    temp[i] = new StudyPair(old[i]);
                this.classroomPairMap.put(key, temp);
            }
        }

        public StudyDay(int countOfPairs, List<Classroom> classrooms) {
            classroomPairMap = new HashMap<>();
            for (Classroom classroom : classrooms) {
                StudyPair[] pairOnDayInClassroom = new StudyPair[countOfPairs];
                for (int i = 0; i < countOfPairs; i++)
                    pairOnDayInClassroom[i] = new StudyPair();
                classroomPairMap.put(classroom, pairOnDayInClassroom);
            }
        }

        public HashMap<Classroom, StudyPair[]> getClassroomPairMap() {
            return classroomPairMap;
        }

        public void setClassroomPairMap(HashMap<Classroom, StudyPair[]> classroomPairMap) {
            this.classroomPairMap = classroomPairMap;
        }
    }

    private class TempCount {
        private int practices = 0, lectures = 0;

        public TempCount(int numLectionPerWeek, int numPracticePerWeek) {
            lectures = numLectionPerWeek;
            practices = numPracticePerWeek;
        }

        public int getPractices() {
            return practices;
        }

        public int getLectures() {
            return lectures;
        }

        public void incrementLectures() {
            lectures--;
        }

        public void incrementPractices() {
            practices--;
        }
    }
}
