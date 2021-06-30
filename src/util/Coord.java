package util;

public class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        System.out.println("hashcode = " + ((x + y) * (x + y + 1) / 2 + y));
        System.out.println(x + " + " + y);
        return (x + y) * (x + y + 1) / 2 + y;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println(obj.hashCode() + " + " + hashCode());
        if(this == obj) {
            return true;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        Coord c = (Coord) obj;
        if(c.x == x && c.y == y) {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
