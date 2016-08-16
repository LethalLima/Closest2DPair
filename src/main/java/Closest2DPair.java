package main.java;

import java.util.*;

/**
 * Created by Jeffrey Lima on 8/10/16.
 * Calculates closest pair of points given a list of points.
 * Note: Duplicate points are not added.
 * Note: Even though there may be more than one with the same
 * shortest distance, the first one added will be the point returned.
 */
public class Closest2DPair {
    private Pair<Pair<Point, Point>, Double> closestPair;

    public Closest2DPair(List<Point> pointsList) {
        // remove duplicate points
        Set<Point> uniquePoints = new LinkedHashSet<>(pointsList);
        pointsList = new ArrayList<>(uniquePoints);

        // do not run algorithm, if size of list is not at least 2
        if(pointsList.size() <= 1)
            throw new IllegalArgumentException("Need at least two points to calculate distance between points.");

        Collections.sort(pointsList, Point.sortByPointX());
        List<Point> sortedByX = new ArrayList<>(pointsList);
        Collections.sort(pointsList, Point.sortByPointY());

        List<Point> supportList = new ArrayList<>(sortedByX.size());
        this.closestPair = recursiveClosest(sortedByX, pointsList, supportList);
    }

    private Pair<Pair<Point, Point>, Double> recursiveClosest(List<Point> sortedByX,
                                                              List<Point> sortedByY,
                                                              List<Point> supportList) {
        // perform brute force on list that are less than or equal to 3
        if(sortedByX.size() <= 3) {
            return bruteForceClosestPoint(sortedByX);
        } else {
            // split sortedByX in halves with helper method
            List<Point> firstHalfSortedByX = shortenList(sortedByX, 0, sortedByX.size()/2);
            List<Point> secondHalfSortedByX = shortenList(sortedByX, sortedByX.size()/2, sortedByX.size());
            // return array sorted by Y with only the points in the new sorted by x list
            List<Point> firstHalfSortedByY = linearSearchAndSortPoints(firstHalfSortedByX, sortedByY);
            List<Point> secondHalfSortedByY = linearSearchAndSortPoints(secondHalfSortedByX, sortedByY);

            // recursive functions
            Pair<Pair<Point, Point>, Double> deltaLeft = recursiveClosest(firstHalfSortedByX, firstHalfSortedByY, supportList);
            Pair<Pair<Point, Point>, Double> deltaRight = recursiveClosest(secondHalfSortedByX, secondHalfSortedByY, supportList);
            // check which delta is smaller
            Pair<Pair<Point, Point>, Double> delta = deltaLeft.getE2() < deltaRight.getE2() ? deltaLeft : deltaRight;

            // Clear list before adding  to it
            supportList.clear();
            Point midPoint = sortedByX.get(sortedByX.size()/2);
            for(Point point : sortedByY) {
                // Check if there are any points close to mid point squared
                // value stored is the distance squared
                if(Math.pow(point.getX() - midPoint.getX(), 2.0) < delta.getE2()) {
                    supportList.add(point);
                }
            }

            // iterate through points added to see if any points are less than delta
            for(int i = 0; i < supportList.size(); i++) {
                for( int j = i*i + 1; j < supportList.size(); j++) {
                    if(supportList.get(i).distanceSquared(supportList.get(j)) < delta.getE2()) {
                        delta.setE1(new Pair<>(supportList.get(i), supportList.get(j)));
                        delta.setE2(supportList.get(i).distanceSquared(supportList.get(j)));
                    }
                }
            }
            return delta;
        }
    }

    private List<Point> shortenList(List<Point> sortedByX, int low, int high) {
        List<Point> shorterList = new ArrayList<>();
        for(int i = low; i < high; i++) {
            shorterList.add(sortedByX.get(i));
        }
        return shorterList;
    }

    private List<Point> linearSearchAndSortPoints(List<Point> sortedByX, List<Point> sortedByY) {
        Set<Point> setSortedByX = new HashSet<>(sortedByX);
        List<Point> newSortedByY = new ArrayList<>();
        for(Point point : sortedByY) {
            if(setSortedByX.contains(point)) {
                newSortedByY.add(point);
            }
        }
        return newSortedByY;
    }

    // this method sets j to i squared plus 1 because there is no point in checking distance,
    // if it has already been computed earlier
    private Pair<Pair<Point, Point>, Double> bruteForceClosestPoint(List<Point> points) {
        Double closest = null, supportList;
        Point left, right, firstPoint = null, secondPoint = null;
        for(int i = 0; i < points.size(); i++) {
            left = points.get(i);
            // no need to check points that the distance squared has already been calculated
            // and plus 1 to not compare the point to itself
            for(int j = i*i + 1; j < points.size(); j++) {
                right = points.get(j);
                // condition is only true once, when we store the first and second points distance squared
                if(closest == null){
                    closest = Point.distanceSquared(left, right);
                    firstPoint = left;
                    secondPoint = right;
                } else {
                    if (closest > (supportList = Point.distanceSquared(left, right))) {
                        closest = supportList;
                        firstPoint = left;
                        secondPoint = right;
                    }
                }
            }
        }
        return new Pair<>(new Pair<>(firstPoint, secondPoint), closest);
    }

    public double getClosestDistance() {
        return this.closestPair.getE2();
    }

    public Point getFirstPoint() {
        return this.closestPair.getE1().getE1();
    }

    public Point getSecondPoint() {
        return this.closestPair.getE1().getE2();
    }
}
