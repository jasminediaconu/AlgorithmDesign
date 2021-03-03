package main.MidtermPreparation.DivideAndConquer.SnuggleInTight;

import java.util.ArrayList;
import java.util.List;

public class SnuggleInTight {
    /**
     * Takes a list of points and returns the distance between the closest pair.
     * This is done with divide and conquer.
     *
     * @param points
     *     - list of points that need to be considered.
     * @return smallest pair-wise distance between points.
     */
    public static double closestPair(List<Point> points) {

        // 1. Sorting points by X coord
        Util.sortByX(points);

        return divideAndConquer(points);
    }

    public static double divideAndConquer(List<Point> points) {
        // 2. If the list contains <= 3 points, use bruteforce
        int size = points.size();

        if(size <= 3) return Util.bruteForce(points);

        // 3. Else create left and right list
        int middle = size/2;

        List<Point> leftPoints = points.subList(0, middle);
        List<Point> rightPoints = points.subList(middle, points.size());

        // 4. Now compute the min distance on the sides
        double distanceLeft = closestPair(leftPoints);
        double distanceRight = closestPair(rightPoints);

        double minDistance = Math.min(distanceLeft, distanceRight);

        // 5. Create the strip: points within minDistance from middlePoint
        ArrayList<Point> strip = new ArrayList<>();

        Point middlePoint = points.get(middle);

        for(int i = 0; i < size; i++) {
            if(Math.abs(points.get(i).x - middlePoint.x) < minDistance) strip.add(points.get(i));
        }

        return stripClosest(strip, minDistance);
    }

    public static double stripClosest(List<Point> strip, double d) {
        double minDistance = d;
        int size = strip.size();

        // 6. Sort the strip by Y values
        Util.sortByY(strip);

        // 7. Now check if the points in the strip are closer
        //    than the ones on the sides
        for(int i = 0; i < size-1; i++) {
            for(int j = i+1; j < size && (strip.get(j).y - strip.get(i).y) < minDistance; j++) {
                double currentDistance = Util.distance(strip.get(i), strip.get(j));
                if(currentDistance < minDistance) minDistance = currentDistance;
            }
        }

        return minDistance;
    }
}
