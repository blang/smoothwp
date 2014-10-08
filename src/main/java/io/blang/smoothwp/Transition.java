package io.blang.smoothwp;

public class Transition {
    private final double targetLat;
    private final double targetLon;
    private final double[] vec;
    private double vectorLength;
    private double[] lastWP;

    public Transition(double wp1latitude, double wp1longitude, double wp2latitude, double wp2longitude) {
        this.targetLat = wp2latitude;
        this.targetLon = wp2longitude;

        vec = new double[]{wp2latitude - wp1latitude, wp2longitude - wp1longitude};
        vectorLength = Math.sqrt(Math.pow(vec[0], 2) + Math.pow(vec[1], 2));
        vec[0] /= vectorLength;
        vec[1] /= vectorLength;

        lastWP = new double[]{wp1latitude, wp1longitude};
    }

    public boolean hasNext() {
        return vectorLength > 0;
    }

    public double[] next(double distance) {
        vectorLength -= distance;

        if (vectorLength <= 0) {
            return new double[]{targetLat, targetLon};
        }
        lastWP[0] += vec[0] * distance;
        lastWP[1] += vec[1] * distance;
        return new double[]{lastWP[0], lastWP[1]};
    }
}
