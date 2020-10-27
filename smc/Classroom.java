import java.util.Objects;

public class Classroom {
    private int num;
    private int capacity;

    public Classroom(int num, int capacity) {
        this.num = num;
        this.capacity = capacity;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return num == classroom.num &&
                capacity == classroom.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, capacity);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
