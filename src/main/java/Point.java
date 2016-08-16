package main.java;

import java.util.Comparator;

/**
 * Created by Jeffrey Lima on 7/27/16.
 */
public class Point {
    private double x;
    private double y;

    public Point() {

    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        return Double.compare(point.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    static Comparator<Point> sortByPointX() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(o1.getX(), o2.getX());
            }
        };
    }

    static Comparator<Point> sortByPointY() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(o1.getY(), o2.getY());
            }
        };
    }

    public static Double distanceSquared(Point left, Point right) {
        return Math.pow(left.getX() - right.getX(), 2.0) + Math.pow(left.getY() - right.getY(), 2.0);
    }

    public Double distanceSquared(Point other) {
        return Math.pow(this.getX() - other.getX(), 2.0) + Math.pow(this.getY() - other.getY(), 2.0);
    }
}
