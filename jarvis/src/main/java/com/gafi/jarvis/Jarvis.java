package com.gafi.jarvis;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Jarvis {
    private int numbers = 5;
    private int xMax = 36;
    private int yMax = 792;
    private int[][] data;

    public Jarvis(int numbers) {
        this.numbers = numbers;
        this.data = new int[numbers][numbers];
    }

    public int[][] pointParHasard() {
        for (int i = 0; i < numbers; i++) {
            for (int j = 0; j < 2; j++) {
                int random;
                Random randoms = new Random();
                if(j == 0)
                    // case X value
                    random = randoms.nextInt(xMax);
                else
                    // case Y value
                    random = randoms.nextInt(yMax);

                data[i][j] = random;
            }
        }

        return data;
    }

    public String anglePolaireInferieur(int[] p0, int[] p1, int[] p2) {
        int x_a = p0[0], y_a = p0[1];
        double x_b = p1[0], y_b = p1[1];
        double x_p = p2[0], y_p = p2[1];

        double determinant = (x_b - x_a) * (y_p - y_a) - (y_b - y_a) * (x_p - x_a);

        return determinant < 0 ? "Vrai" : "Faux";
    }

    public int[][] getData() {
        return this.data;
    }

    public int[] getpMin(int[][] Q) {
        int yMin = Integer.MAX_VALUE;
        int[] pointMin = Q[0];
        int pointMinIndex = 0;

        for (int i = 0; i < Q.length; i++) {
            if(Q[i][1] < yMin) {
                yMin = Q[i][1];
                pointMin = Q[i];
                pointMinIndex = i;
            }
        }
        return pointMin;
    }

    private int direction(int[] p, int[] q, int[] r) {
        int val = (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
        if (val == 0) return 0;  // collinear
        return (val > 0) ? 1 : -1;  // clockwise or counterclockwise
    }

    private int distanceSq(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    public List<int[]> findEnvConv(int[][] Q) {
        List<int[]> points = new ArrayList<>();
        // Insert all points in a list
        for (int i = 0; i < Q.length; i++) {
            points.add(Q[i]);
        }
        // Find the leftmost point
        int[] a = getpMin(Q);
        if (a == null) {
            return new ArrayList<>();
        }

        int index = points.indexOf(a);
        // Selection sort
        int l = index;
        List<int[]> result = new ArrayList<>();
        result.add(a);
        while (true) {
            int q = (l + 1) % points.size();
            for (int i = 0; i < points.size(); i++) {
                if (i == l) continue;
                // Find the greatest left turn
                // In case of collinearity, consider the farthest point
                int d = direction(points.get(l), points.get(i), points.get(q));
                if (d > 0 || (d == 0 && distanceSq(points.get(i), points.get(l)) > distanceSq(points.get(q), points.get(l)))) {
                    q = i;
                }
            }
            l = q;
            if (l == index) break;
            result.add(points.get(q));
        }
        return result;
    }

    public List<int[]> getListData() {
        List<int[]> listData = new ArrayList<>();
        for (int i = 0; i < this.data.length; i++) {
            listData.add(this.data[i]);
        }
        return listData;
    }
}