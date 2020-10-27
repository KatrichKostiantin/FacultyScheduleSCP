import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Timetable implements Comparable<Timetable> {
    private static final int DAYS_FOR_STUDY = 5;
    private static final int PAIR_PER_DAY = 7;
    private static int freeId = 0;
    private final int Id;
    private ArrayList<StudyDay> studyDays;
    private List<Classroom> classrooms;
    private ArrayList<Student> students;
    private long countOfErrors = -1;

    public Timetable(List<Classroom> classrooms, List<Student> students) {
        Id = freeId++;
        studyDays = new ArrayList<>();
        this.classrooms = classrooms;
        this.students = new ArrayList<>(students);
        for (int i = 0; i < DAYS_FOR_STUDY; i++)
            studyDays.add(new StudyDay(PAIR_PER_DAY, classrooms));
    }

    public Timetable(Timetable that) {
        Id = freeId++;
        this.classrooms = that.classrooms;
        this.studyDays = new ArrayList<>();
        for (StudyDay studyDay : that.getStudyDays())
            this.studyDays.add(new StudyDay(studyDay));
        this.students = that.students;
    }

    public long getCountOfErrors() {
        if (countOfErrors == -1)
            countOfErrors = countErrors();
        return countOfErrors;
    }

    public int getId() {
        return Id;
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

    @Override
    public int compareTo(Timetable o) {
        return Long.compare(getCountOfErrors(), o.getCountOfErrors());
    }

    public long countErrors() {
        long res = 0;
        res += countStudentDisciplines();
        for (Timetable.StudyDay studyDay : getStudyDays())
            for (Classroom keyClassroom : studyDay.getClassroomPairMap().keySet())
                for (int i = 0; i < studyDay.getClassroomPairMap().get(keyClassroom).length; i++) {
                    StudyPair studyPair = studyDay.getClassroomPairMap().get(keyClassroom)[i];
                    if (studyPair.getStudents().isEmpty())
                        continue;
                    //Перевірка чи може викладач викладати
                    if (!studyPair.getTeacher().canTeach(studyPair.getLesson()))
                        res++;
                    //Перевірка чи викладає на цій парі ще десь викладач
                    for (Classroom keyClassroomCheck : studyDay.getClassroomPairMap().keySet())
                        if (keyClassroomCheck != keyClassroom &&
                                studyDay.getClassroomPairMap().get(keyClassroomCheck)[i].getTeacher().equals(studyPair.getTeacher()))
                            res++;
                    //Перевірка чи слухають на цій парі ще десь всі студенти
                    for (Classroom keyClassroomCheck : studyDay.getClassroomPairMap().keySet())
                        if (keyClassroomCheck != keyClassroom)
                            for (Student tempStd : studyDay.getClassroomPairMap().get(keyClassroomCheck)[i].getStudents())
                                if (studyPair.getStudents().contains(tempStd))
                                    res++;

                    //Перевірки чи зможе аудиторія вмістити стільки студентів
                    if (keyClassroom.getCapacity() < studyPair.getStudents().size())
                        res += keyClassroom.getCapacity() - studyPair.getStudents().size();
                }
        return res;
    }

    private long countStudentDisciplines() {
        long res = 0;
        HashMap<Student, HashMap<Integer, TempCount>> map = new HashMap<>();
        for (Student student : students) {
            HashMap<Integer, TempCount> disciplineMap = new HashMap<>();
            for (Discipline discipline : student.getStudyDisciplines())
                disciplineMap.put(discipline.getId(), new TempCount(discipline.numLectionPerWeek, discipline.numPracticePerWeek));
            map.put(student, disciplineMap);
        }

        for (Timetable.StudyDay studyDay : getStudyDays())
            for (Classroom keyClassroom : studyDay.getClassroomPairMap().keySet())
                for (int i = 0; i < studyDay.getClassroomPairMap().get(keyClassroom).length; i++) {
                    StudyPair studyPair = studyDay.getClassroomPairMap().get(keyClassroom)[i];
                    List<Student> studentList = studyPair.getStudents();
                    if (studentList.isEmpty())
                        continue;
                    for (Student student : studentList) {
                        //Перевірка чи слухає студент
                        if (!map.get(student).containsKey(studyPair.getLesson().getDisciplineId())) {
                            res++;
                            continue;
                        }

                        if (studyPair.getLesson().getTypeLesson() == TypeLesson.Lecture) {
                            if (map.get(student).get(studyPair.getLesson().getDisciplineId()).getLectures() <= 0) res++;
                            else map.get(student).get(studyPair.getLesson().getDisciplineId()).incrementLectures();
                        } else {
                            if (map.get(student).get(studyPair.getLesson().getDisciplineId()).getPractices() <= 0)
                                res++;
                            else map.get(student).get(studyPair.getLesson().getDisciplineId()).incrementPractices();
                        }
                    }
                }
        //Перевірка що не залишилось курсів
        for (Student key : map.keySet()) {
            for (Integer id : map.get(key).keySet()) {
                res += map.get(key).get(id).getLectures();
                res += map.get(key).get(id).getPractices();
            }
        }
        return res;
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
