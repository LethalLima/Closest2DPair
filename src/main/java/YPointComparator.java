package main.java;

import java.util.Comparator;

/**
 * Created by Jeffrey Lima on 7/29/16.
 */
public class YPointComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        return Double.compare(o1.getY(), o2.getY());
    }
}
