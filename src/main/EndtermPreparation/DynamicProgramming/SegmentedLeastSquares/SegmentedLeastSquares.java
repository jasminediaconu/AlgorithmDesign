package main.EndtermPreparation.DynamicProgramming.SegmentedLeastSquares;

import java.util.Arrays;
import java.util.Stack;

public class SegmentedLeastSquares {
    static class Point implements Comparable<Point> {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            return Double.compare(this.x, p.x);
        }
    }

    static class Segment {
        Point point1;
        Point point2;

        public Segment(Point point1, Point point2) {
            this.point1 = point1;
            this.point2 = point2;
        }
    }

    public static double findMinimumCost(Point[] points, int c) {
        int n = points.length;

        double error[][] = new double[n][n]; // total error
        double a[][] = new double[n][n]; // the slope of the segment
        double b[][] = new double[n][n]; // the y-intercept of the segment

        // we need to compute these for each possible segment
        double xySums[] = new double[n]; // array of sums x*y
        double xSums[] = new double[n]; // array of x sums
        double ySums[] = new double[n]; // array of y sums
        double xSquareSums[] = new double[n]; // array of x^2 sums
        double ySquareSums[] = new double[n]; // array of y^2 sums

        // Initialize with 0 (we don't have any point yet)
        xySums[0] = 0;
        xSums[0] = 0;
        ySums[0] = 0;
        xSquareSums[0] = 0;
        ySquareSums[0] = 0;

        // memoization array
        double minCost[] = new double[n];
        minCost[0] = 0;

        int lastIndex[] = new int[n]; // Store the end point of the j-1 segment
        lastIndex[0] = 0;

        Arrays.sort(points); // sort points by x-coord

        for(int i = 1; i < n; i++) {
            xSums[i] = xSums[i-1] + points[i].x;
            ySums[i] = ySums[i-1] + points[i].y;
            xSquareSums[i] = xSquareSums[i-1] + (points[i].x * points[i].x);
            ySquareSums[i] = ySquareSums[i-1] + (points[i].y * points[i].y);
        }

        // Computing the error
        for(int i = 1; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                int currentN = j - i + 1; // number of points in the segment

                double xySum = xySums[j] - xySums[i-1];
                double xSum = xSums[j] - xSums[i-1];
                double ySum = ySums[j] - ySums[i-1];
                double xSquareSum = xSquareSums[j] - xSquareSums[i-1];
                double ySquareSum = ySquareSums[j] - ySquareSums[i-1];

                // a = (n * (Sum(xy) - Sum(x)*Sum(x))) / (n * Sum(x^2) - (Sum(x))^2)
                a[i][j] = (currentN * xySum - xSum * ySum)/(currentN * xSquareSum - (xSum * xSum));

                // b = (Sum(y) - a * Sum(x)) / n
                b[i][j] = (ySum - a[i][j] * xSum)/(currentN);

                // e[i][j] = Sum(y - ax - b)^2
                error[i][j] = (ySum - (a[i][j] * xSum) - b[i][j]) * (ySum - (a[i][j] * xSum) - b[i][j]);
            }
        }

        // Checking for the minimum cost
        for(int j = 1; j < n; j++) {
            minCost[j] = error[1][j] + c;
            lastIndex[j] = 1;

            for(int i = 2; i <= j; i++) {
                if(minCost[i-1] + error[i][j] + c < minCost[j]) {
                    minCost[j] = minCost[i-1] + error[i][j] + c;
                    lastIndex[j] = i;
                }
            }
        }

        return minCost[n-1];
    }
    
    public static Stack<Segment> getSegments(int[] lastIndex, Point[] points, int[][] a, int[][] b) {
        Stack<Segment> segments = new Stack<Segment>();
        Stack<Segment> result = new Stack<>();

        int currentIndex = points.length;

        while (currentIndex > 1) {
            int nextIndex = lastIndex[currentIndex];
            if (nextIndex == currentIndex) {
                segments.push(new Segment(points[currentIndex-1], points[currentIndex]));
            }
            else {
                double x1 = points[nextIndex].x;
                double y1 = x1 * a[nextIndex][currentIndex] + b[nextIndex][currentIndex];
                double x2 = points[currentIndex].x;
                double y2 = x2 * a[nextIndex][currentIndex] + b[nextIndex][currentIndex];
                segments.push(new Segment(new Point(x1, y1), new Point(x2, y2)));
            }

            currentIndex = nextIndex - 1;
        }

        while (!segments.empty()) {
            result.push(segments.peek());
            segments.pop();
        }

        return result;
    }
}