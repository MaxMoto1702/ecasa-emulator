package ru.softwarecom.uspn.emulators.ecasa;

public class SimpleBean {
    private int x = 1;
    private int y = 2;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
