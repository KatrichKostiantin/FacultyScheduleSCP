import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Discipline {
    private final int Id;
    private static int freeId = 0;

    public int getId() {
        return Id;
    }

    List<Lesson> lessons;
    private String name;
    public int numLectionPerWeek, numPracticePerWeek;

    public Discipline(String name, int numLectionPerWeek, int numPracticePerWeek) {
        this.name = name;
        Id = freeId++;
        this.numLectionPerWeek = numLectionPerWeek;
        this.numPracticePerWeek = numPracticePerWeek;
        lessons = new ArrayList<>(numLectionPerWeek + numPracticePerWeek);
        while (numLectionPerWeek-- > 0) lessons.add(new LectureLesson(Id));
        while (numPracticePerWeek-- > 0) lessons.add(new PracticeLesson(Id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Id == that.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    abstract static class Lesson {
        private TypeLesson typeLesson;
        private int disciplineId;

        public int getDisciplineId() {
            return disciplineId;
        }

        public Lesson(int disciplineId) {
            this.disciplineId = disciplineId;
        }

        abstract TypeLesson getTypeLesson();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lesson lesson = (Lesson) o;
            return disciplineId == lesson.disciplineId &&
                    typeLesson == lesson.typeLesson;
        }

        @Override
        public int hashCode() {
            return Objects.hash(typeLesson, disciplineId);
        }
    }

    class PracticeLesson extends Lesson {
        PracticeLesson(int disciplineId) {
            super(disciplineId);
        }

        @Override
        TypeLesson getTypeLesson() {
            return TypeLesson.Practise;
        }
    }

    class LectureLesson extends Lesson {
		LectureLesson(int disciplineId) {
			super( disciplineId);
		}

        @Override
        TypeLesson getTypeLesson() {
            return TypeLesson.Lecture;
        }
    }
}
