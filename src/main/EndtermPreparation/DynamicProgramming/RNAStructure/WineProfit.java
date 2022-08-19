package main.EndtermPreparation.DynamicProgramming.RNAStructure;

/**
 * Let's say, you have N (1, 2, 3, ...., N) wines placed next to each other on a shelf. The price of ith
 * wine is p[i]. The price of the wines increase every year. Suppose this is year 1, on year y the price
 * of the ith wine will be: year * price of the wine = y*p[i]. You want to sell the wines you have, but
 * you have to sell exactly one wine per year, starting from this year. Again, on each year, you are
 * allowed to sell only the leftmost or the rightmost wine and you can't rearrange the wines, that
 * means they must stay in same order as they are in the beginning.
 */
public class WineProfit {
    public static int wineProfit(int[] wines) {
        int n = wines.length;
        int[][] result = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++)
                result[i][j] = -1;
        }

        return findProfit(wines, result, 0, n-1);
    }

    public static int findProfit(int[] wines, int[][] result, int leftMost, int rightMost) {
        if(leftMost > rightMost) return 0;

        if(result[leftMost][rightMost] != -1) return result[leftMost][rightMost];

        int year = result.length - (rightMost - leftMost + 1) + 1;

        result[leftMost][rightMost] =
                Math.max(findProfit(wines, result, leftMost+1, rightMost) + year * wines[leftMost],
                            findProfit(wines, result, leftMost, rightMost-1) + year * wines[rightMost]);

        return result[leftMost][rightMost];
    }
}
