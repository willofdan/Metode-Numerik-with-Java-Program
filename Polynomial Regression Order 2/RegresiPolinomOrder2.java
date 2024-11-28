import java.util.*;

public class RegresiPolinomOrder2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan N(Banyaknya/Panjang Angka):");
        int n = input.nextInt();
        System.out.println("===== NILAI x =====");
        float[] x = isInputX(n);
        System.out.println("===== NILAI Y =====");
        float[] y = isInputY(n);

        float xbar = isAverage(x);
        float ybar = isAverage(y);
        float sigmaX = isSigma(x);
        float sigmaY = isSigma(y);
        float[] x2 = isX2(x);
        float sigmax2 = isSigma(x2);
        float[] x3 = isX3(x);
        float sigmax3 = isSigma(x3);
        float[] x4 = isX4(x);
        float sigmax4 = isSigma(x4);
        float[] xy = isXY(x, y);
        float sigmaxy = isSigma(xy);
        float[] x2y = isX2Y(x2,y);
        float sigmax2y = isSigma(x2y);
        float[] yminybar = isYminYbar(y, ybar);
        float sigmayminybar = isSigma(yminybar);
        float[] yminybar2 = isYminYbar2(y, ybar);
        float sigmayminybar2 = isSigma(yminybar2);

        float[][] matriks = {
                {n, sigmaX, sigmax2},
                {sigmaX, sigmax2, sigmax3},
                {sigmax2, sigmax3, sigmax4}
        };

        float[][] matPerbandingan = {
                {sigmaY},
                {sigmaxy},
                {sigmax2y}
        };

        float[][] matInverse = isInverse(matriks);
        float[][] matHasil = isMultiplyMatrices(matInverse, matPerbandingan);

        float a0 = matHasil[0][0];
        float a1 = matHasil[1][0];
        float a2 = matHasil[2][0];

        float[] yAaXaX = new float[n];
        for (int i = 0; i < n; i++) {
            yAaXaX[i] = (float) Math.pow(y[i]- a0 - a1 * x[i] - a2 * x2[i], 2);
        }

        float sigmayAaXaX = isSigma(yAaXaX);

        // PRINT ALL
        System.out.println("======================================================================================================================");
        System.out.printf("%-3s %-5s %-5s %-6s %-6s %-6s %-7s %-8s %-12s %-12s %-17s%n", "No", "X", "Y", "X^2", "X^3", "X^4", "XY", "\u03A3X^2Y","(Y-Ybar)","(Y-Ybar)^2","(Y-a0-a1X-a2-X^2)^2");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-3s %-5.1f %-5.1f %-6.1f %-6.1f %-6.1f %-7.1f %-8.1f %-12.5f %-12.5f %-17.7f%n", (i+1), x[i], y[i], x2[i], x3[i], x4[i], xy[i], x2y[i], yminybar[i], yminybar2[i], yAaXaX[i]);
        }
        System.out.printf("%-3s %-5s %-5s %-6s %-6s %-6s %-7s %-8s %-12s %-12s %-17s%n", " ", "\u03A3", "\u03A3", "\u03A3", "\u03A3", "\u03A3", "\u03A3", "\u03A3", "\u03A3", "\u03A3", "\u03A3");
        System.out.printf("%-3s %-5.1f %-5.1f %-6.1f %-6.1f %-6.1f %-7.1f %-8.1f %-12.5f %-12.5f %-17.7f%n", " ", sigmaX,sigmaY, sigmax2, sigmax3, sigmax4, sigmaxy, sigmax2y, sigmayminybar, sigmayminybar2, sigmayAaXaX);

        System.out.println("Matriks : ");
        printMatrix(matriks);
        System.out.println("Matriks Inverse : ");
        printMatrix(matInverse);
        System.out.println("Matriks Perbandingan : ");
        printMatrix(matPerbandingan);
        System.out.println("Matriks Hasil : ");
        printMatrix(matHasil);
        System.out.println("Persamaan");
        System.out.printf("Y = %.2f + %.2fx %.2fx^2", matHasil[0][0],matHasil[1][0], matHasil[2][0]);
        
    }

    static float[] isInputX(int n){
        Scanner input = new Scanner(System.in);
        float[] x = new float[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan Nilai X ke-" + (i+1) + ": ");
            x[i] = input.nextFloat();
        }
        return x;
    }
    static float[] isInputY(int n){
        Scanner input = new Scanner(System.in);
        float[] y = new float[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan Nilai Y ke-" + (i+1) + ": ");
            y[i] = input.nextFloat();
        }
        return y;
    }

    static float isAverage(float[] arr){
        float sum = 0;
        for(float larik : arr){
            sum += larik;
        }
        return sum/arr.length;
    }
    static float isSigma(float[] arr){
        float sum = 0;
        for(float larik : arr){
            sum += larik;
        }
        return sum;
    }

    static float[] isX2(float[] x){
        float[] sum = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            sum[i] = x[i] * x[i];
        }
        return sum;
    }
    static float[] isX3(float[] x){
        float[] sum = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            sum[i] = x[i] * x[i] * x[i];
        }
        return sum;
    }
    static float[] isX4(float[] x){
        float[] sum = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            sum[i] = x[i] * x[i] * x[i] * x[i];
        }
        return sum;
    }
    static float[] isXY(float[] x, float[] y){
        float[] sum = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            sum[i] = x[i] * y[i];
        }
        return sum;
    }
    static float[] isX2Y(float[] x2, float[] y){
        float[] sum = new float[x2.length];
        for (int i = 0; i < x2.length; i++) {
            sum[i] = x2[i] * y[i];
        }
        return sum;
    }
    static float[] isYminYbar(float[] y, float ybar){
        float[] sum = new float[y.length];
        for (int i = 0; i < y.length; i++) {
            sum[i] = y[i] - ybar;
        }
        return sum;
    }
    static float[] isYminYbar2(float[] y, float ybar){
        float[] sum = new float[y.length];
        for (int i = 0; i < y.length; i++) {
            sum[i] = (float) Math.pow((y[i] - ybar),2);
        }
        return sum;
    }

//MATRIKS
public static float determinant(float[][] matrix) {
    return matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
            - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
            + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
}

    // Fungsi untuk menghitung kofaktor dari matriks 3x3
    public static float[][] cofactor(float[][] matrix) {
        float[][] cofactors = new float[3][3];

        cofactors[0][0] = matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1];
        cofactors[0][1] = -(matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]);
        cofactors[0][2] = matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0];

        cofactors[1][0] = -(matrix[0][1] * matrix[2][2] - matrix[0][2] * matrix[2][1]);
        cofactors[1][1] = matrix[0][0] * matrix[2][2] - matrix[0][2] * matrix[2][0];
        cofactors[1][2] = -(matrix[0][0] * matrix[2][1] - matrix[0][1] * matrix[2][0]);

        cofactors[2][0] = matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1];
        cofactors[2][1] = -(matrix[0][0] * matrix[1][2] - matrix[0][2] * matrix[1][0]);
        cofactors[2][2] = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        return cofactors;
    }

    // Fungsi untuk mentranspos matriks
    public static float[][] transpose(float[][] matrix) {
        float[][] transposed = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                transposed[i][j] = matrix[j][i];
            }
        }
        return transposed;
    }

    // Fungsi untuk menghitung invers matriks 3x3
    public static float[][] isInverse(float[][] matrix) throws IllegalArgumentException {
        float det = determinant(matrix);
        if (det == 0) {
            throw new IllegalArgumentException("Matrix is not invertible (determinant is zero).");
        }

        float[][] cofactors = cofactor(matrix);
        float[][] adjoint = transpose(cofactors);

        // Membagi elemen adjoint dengan determinan untuk mendapatkan invers
        float[][] inverse = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverse[i][j] = adjoint[i][j] / det;
            }
        }

        return inverse;
    }

    public static float[][] isMultiplyMatrices(float[][] matrixA, float[][] matrixB) throws IllegalArgumentException {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;

        // Memastikan jumlah kolom matrixA sama dengan jumlah baris matrixB
        if (colsA != rowsB) {
            throw new IllegalArgumentException("Number of columns in Matrix A must be equal to the number of rows in Matrix B.");
        }

        float[][] result = new float[rowsA][colsB];

        // Melakukan perkalian matriks
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }
    public static void printMatrix(float[][] matrix) {
        for (float[] row : matrix) {
            System.out.print("|");
            for (float value : row) {
                System.out.printf("%8.2f ", value); // Cetak setiap elemen dengan 3 angka di belakang koma
            }
            System.out.print("|");
            System.out.println(); // Pindah ke baris berikutnya setelah selesai dengan satu baris
        }
    }
}






    // static LinkedList<float[]> isInput(int n){
    //     Scanner input = new Scanner(System.in);
    //     LinkedList<float[]> sumLink = new LinkedList<>();
    //     float[] sumArr = new float [2];
    //     for (int i = 0; i < n; i++) {
    //         System.out.print("Masukkan x ke-" + (i+1)+":");
    //         float x = input.nextFloat();
    //         System.out.print("Masukkan y ke-" + (i+1)+":");
    //         float y = input.nextFloat();
    //         sumArr[0] = x;
    //         sumArr[1] = y;
    //         sumLink.add(sumArr);
    //     }
    //     return sumLink;
    // }