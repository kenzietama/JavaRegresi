import java.util.Arrays;

public class MetodeLinear {

    public static void main(String[] args) {
        // Example data
        double[] x = {1,2,2,2,5,6,6,6,2,0,5,2,2,2,8,3,4,2,9,0,3,0,6,6,3,4,9,6,5,3,3,1,9,1,3,4,3,2,1,3,4,0,0,5,4,3,0,6,3,2,6,1,9,0,3,7,5,8,0,0,4,5,7,2,5,2,7,3,1,8,9,7,5,2,1,3,3,0,8,9,7,6,5,2,3,1,6,1,9,0,5,3,7,0,8,5,1,7,4,4};
        double[] y = {91,65,45,36,66,61,63,42,61,69,84,73,27,33,68,43,67,70,30,63,71,85,73,57,35,49,66,83,74,74,39,36,58,47,60,74,42,68,32,64,45,39,58,36,71,54,17,54,58,53,27,65,75,52,78,91,33,47,78,38,70,98,87,49,41,71,54,42,91,61,74,54,81,52,65,36,61,35,15,88,45,49,33,60,71,81,67,95,58,29,21,38,60,76,69,30,57,81,36,25};

        // Fit the data
        double[] coeffs = fit(x, y);

        // Calculate predicted y values
        double[] predictedY = new double[y.length];
        for (int i = 0; i < x.length; i++) {
            predictedY[i] = coeffs[0] + coeffs[1] * x[i];
        }

        // Calculate RMS error
        double sumOfSquaredErrors = 0;
        for (int i = 0; i < y.length; i++) {
            double error = y[i] - predictedY[i];
            sumOfSquaredErrors += Math.pow(error, 2);
        }
        double rmsError = Math.sqrt(sumOfSquaredErrors / y.length);

        // Print the coefficients and RMS error
        System.out.println("Titik potong: " + coeffs[0]);
        System.out.println("Gradien: " + coeffs[1]);
        System.out.println("RMS Error: " + rmsError);
    }

    // Function to fit the data and return the coefficients
    public static double[] fit(double[] x, double[] y) {
        int n = x.length;

        // Calculate means
        double sumX = Arrays.stream(x).sum();
        double sumY = Arrays.stream(y).sum();
        double meanX = sumX / n;
        double meanY = sumY / n;

        // Calculate coefficients
        double slope = 0;
        double intercept = 0;
        for (int i = 0; i < n; i++) {
            slope += (x[i] - meanX) * (y[i] - meanY);
            intercept += (x[i] - meanX) * (x[i] - meanX);
        }
        slope /= intercept;
        intercept = meanY - slope * meanX;

        return new double[]{intercept, slope};
    }
}
