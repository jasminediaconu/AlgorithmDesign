package main.MidtermPreparation.Tutorials.DivideAndConquer;

/**
 * Implement the function computeC(int[] a, int[] b), which takes an array a of length n
 * and an array b of length m as input and returns a n x m matrix.
 * This matrix contains the values abji, where ai) is the i-th value in array a and bj
 * is the j-th value in array b.
 *
 * In this exercise you are not allowed to use Math.pow() and should implement fast binary
 * exponentiation by yourself.
 */
public class FastBinaryExponentiation {
    /**
     * Computes the matrix C, containing the values for a^b, for all values in a and b.
     *
     * @param a array containing the bases
     * @param b array containing the exponents
     * @return matrix C
     */
    public static int[][] computeC(int[] a, int[] b) {
        int result [][] = new int[a.length][b.length];

        for(int i = 0; i < a.length; i ++) {
            int value = a[i];
            for(int j = 0; j < b.length; j++) {
                result[i][j] = binaryExponentiation(value, b[j]);
            }
        }

        return result;
    }

    public static int binaryExponentiation(int a, int b) {
        if( b == 0) return 1;

        int temp = binaryExponentiation(a, b/2);

        if (b%2 == 0) return temp * temp;

        else
        {
            if(b > 0) return a * temp * temp;
            else return (temp * temp) / a;
        }
    }
}
