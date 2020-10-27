import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadingFiles {
    BufferedReader myR;
    ;
    private List<Discipline> allDisciplines = new ArrayList<Discipline>();
    ;
    private List<Teacher> allTeachers = new ArrayList<Teacher>();
    private List<Student> allStudents = new ArrayList<Student>();
    private List<Classroom> allClassrooms = new ArrayList<Classroom>();

    public ReadingFiles() throws IOException {
        readDisciplines();
        readStudents();
        readTeachers();
        readClassrooms();
    }

    private void readDisciplines() throws IOException {
        myR = new BufferedReader(new FileReader("disciplines.txt"));
        String s;
        String[] str;
        while (true) {
            s = myR.readLine();
            if (s == null) break;
            str = s.split(";");
            allDisciplines.add(new Discipline(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2])));
        }
        myR.close();
    }

    private void readStudents() throws IOException {
        myR = new BufferedReader(new FileReader("students.txt"));
        String s;
        String[] str;
        ArrayList<Discipline> disciplines;
        while (true) {
            s = myR.readLine();
            if (s == null) break;
            disciplines = new ArrayList<Discipline>();
            str = s.split(";");
            for (int i = 1; i < str.length; i++) {
                disciplines.add(allDisciplines.get(Integer.parseInt(str[i])));
            }
            allStudents.add(new Student(str[0], disciplines));
        }
        myR.close();
    }

    private void readTeachers() throws IOException {
        myR = new BufferedReader(new FileReader("teachers.txt"));
        String s;
        String[] str;
        ArrayList<Discipline> disciplines;
        while (true) {
            s = myR.readLine();
            if (s == null) break;
            disciplines = new ArrayList<Discipline>();
            str = s.split(";");
            for (int i = 1; i < str.length; i++) {
                disciplines.add(allDisciplines.get(Integer.parseInt(str[i])));
            }
            allTeachers.add(new Teacher(str[0], disciplines, new ArrayList<Discipline>()));
        }
        myR.close();
    }

    private void readClassrooms() throws IOException {
        myR = new BufferedReader(new FileReader("classrooms.txt"));
        String s;
        String[] str;
        while (true) {
            s = myR.readLine();
            if (s == null) break;
            str = s.split(";");
            allClassrooms.add(new Classroom(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }
        myR.close();
    }

    public List<Discipline> getAllDisciplines() {
        return allDisciplines;
    }

    public List<Teacher> getAllTeachers() {
        return allTeachers;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public List<Classroom> getAllClassrooms() {
        return allClassrooms;
    }

}