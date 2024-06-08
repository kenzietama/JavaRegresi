import java.util.Arrays;

public class MetodePangkatSederhana {

    public static void main(String[] args) {
        // Data yang akan di-regresi
        double[] x = {1, 2, 3, 4, 0};
        double[] y = {2.3, 3.4, 4.5, 2, 6.7};

        // Panggil metode untuk melakukan regresi
        double[] regressionCoefficients = performSimplePowerRegression(x, y);

        // Print hasil regresi
        System.out.println("Persamaan regresi: y = " + regressionCoefficients[0] + " * x^" + regressionCoefficients[1]);
    }

    public static double[] performSimplePowerRegression(double[] x, double[] y) {
        // Menghitung log dari data
        double[] logX = new double[x.length];
        double[] logY = new double[y.length];

        for (int i = 0; i < x.length; i++) {
            logX[i] = Math.log(x[i]);
            logY[i] = Math.log(y[i]);
        }

        // Melakukan regresi linear pada data yang sudah di-logaritmik
        double[] linearRegressionCoefficients = performLinearRegression(logX, logY);

        // Transformasi kembali ke skala aslinya
        double a = Math.exp(linearRegressionCoefficients[1]);
        double b = linearRegressionCoefficients[0];

        // Mengembalikan koefisien regresi
        return new double[]{a, b};
    }

    public static double[] performLinearRegression(double[] x, double[] y) {
        // Hitung jumlah data
        int n = x.length;

        // Hitung jumlah dari x, y, x^2, dan xy
        double sumX = 0, sumY = 0, sumXX = 0, sumXY = 0;
        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXX += x[i] * x[i];
            sumXY += x[i] * y[i];
        }

        // Hitung koefisien regresi
        double b = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        double a = (sumY - b * sumX) / n;

        // Mengembalikan koefisien regresi
        return new double[]{a, b};
    }
}
