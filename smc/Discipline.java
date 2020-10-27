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
        while (numLectionPerWeek-- > 0) lessons.add(new LectureLesson(this));
        while (numPracticePerWeek-- > 0) lessons.add(new PracticeLesson(this));
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
        private  Discipline discipline;

        public Lesson(Discipline discipline) {
            this.discipline = discipline;
        }

        public Discipline getDiscipline() {
            return discipline;
        }

        abstract TypeLesson getTypeLesson();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lesson lesson = (Lesson) o;
            return discipline.getId() == lesson.discipline.getId() &&
                    typeLesson == lesson.typeLesson;
        }

        @Override
        public int hashCode() {
            return Objects.hash(typeLesson, discipline.getId());
        }
    }

    class PracticeLesson extends Lesson {
        PracticeLesson(Discipline discipline) {
            super(discipline);
        }

        @Override
        TypeLesson getTypeLesson() {
            return TypeLesson.Practise;
        }
    }

    class LectureLesson extends Lesson {
		LectureLesson(Discipline discipline) {
			super( discipline);
		}

        @Override
        TypeLesson getTypeLesson() {
            return TypeLesson.Lecture;
        }
    }
}
