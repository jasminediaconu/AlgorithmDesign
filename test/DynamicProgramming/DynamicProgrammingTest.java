package DynamicProgramming;

import main.DynamicProgramming.BellmanFord.BusRoutes.BusRoutes;
import main.EndtermPreparation.DynamicProgramming.Knapsack.OnesAndZeros;
import main.EndtermPreparation.DynamicProgramming.Knapsack.StudentBudget;
import main.EndtermPreparation.DynamicProgramming.RNAStructure.WineProfit;
import main.EndtermPreparation.DynamicProgramming.SequenceAlignment.*;
import main.EndtermPreparation.DynamicProgramming.RNAStructure.TightOnTime;
import main.EndtermPreparation.DynamicProgramming.Knapsack.UnboundedKnapsack;
import main.EndtermPreparation.DynamicProgramming.SegmentedLeastSquares.PrettyPrinting;
import main.EndtermPreparation.DynamicProgramming.ShorthestPaths.DijkstraOnSteroids;
import main.EndtermPreparation.DynamicProgramming.ShorthestPaths.FloydWarshall;
import main.EndtermPreparation.DynamicProgramming.ShorthestPaths.LongestPath;
import main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling.*;
import main.Exams.Endterms.Endterm2020.Biology101.Biology101;
import main.Exams.Endterms.Endterm2020.Quest.Edge;
import main.Exams.Endterms.Endterm2020.Quest.Node;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicProgrammingTest {
    @Test
    public void example() {
        int[] s = { 0, 0, 1, 3 };
        int[] f = { 0, 3, 4, 8 };
        int[] v = { 0, 3, 5, 7 };
        int[] p = { 0, -1, -1, 1 };
        int[] solution = IntervalSchedulingPredecessor.solve(3, s, f, v);
        solution[0] = 0;
        assertArrayEquals(p, solution);
    }
    @Test(timeout = 100)
    public void examplePredecessor() {
        int[] s = { 0, 0, 1, 0, 3, 4, 3, 5, 8 };
        int[] f = { 0, 3, 4, 5, 6, 7, 9, 10, 10 };
        int[] v = { 0, 3, 2, 4, 1, 2, 6, 2, 1 };
        int[] p = { 0, -1, -1, -1, 1, 2, 1, 3, 5};
        int[] solution = IntervalSchedulingPredecessor.solve(8, s, f, v);
        assertArrayEquals(p, solution);
    }

    private static class ProblemInstance {

        int n;

        int[] s;

        int[] f;

        int[] v;

        int[] p;

        ProblemInstance(int[][] jobs, int[] p) {
            this.n = jobs.length;
            this.s = new int[this.n + 1];
            this.f = new int[this.n + 1];
            this.v = new int[this.n + 1];
            for (int i = 1; i <= this.n; i++) {
                this.s[i] = jobs[i - 1][0];
                this.f[i] = jobs[i - 1][1];
                this.v[i] = jobs[i - 1][2];
            }
            this.p = p;
        }
    }

    private static ProblemInstance parseInput(String in) {
        // Reading the input through the use of a Scanner.
        Scanner sc = new Scanner(in);
        // Read the amount of jobs.
        int n = sc.nextInt();
        int[][] jobs = new int[n][3];
        // Read the data for every job.
        for (int i = 0; i < n; i++) {
            jobs[i][0] = sc.nextInt();
            jobs[i][1] = sc.nextInt();
            jobs[i][2] = sc.nextInt();
        }
        // Close the scanner.
        sc.close();
        // Sort the jobs on ascending End time order.
        Arrays.sort(jobs, Comparator.comparingInt((int[] o) -> o[1]));
        // Find the predecessor for every job. If a job j has no predecessor then p[j] = -1
        int[] p = new int[n + 1];
        Arrays.fill(p, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (jobs[i][0] >= jobs[j][1]) {
                    p[i + 1] = j + 1;
                }
            }
        }
        return new ProblemInstance(jobs, p);
    }

    @Test(timeout = 100)
    public void example2() {
        int[] s = { 0, 0, 1, 3 };
        int[] f = { 0, 3, 4, 8 };
        int[] v = { 0, 3, 5, 7 };
        int[] p = { 0, -1, -1, 1 };
        assertEquals(10, WeightedIntervalScheduling.solve(3, s, f, v, p));

        WeightedInterval[] intervals = new WeightedInterval[3];
        intervals[0] = new WeightedInterval(0,3,3);
        intervals[1] = new WeightedInterval(1,4,8);
        intervals[2] = new WeightedInterval(3,5,7);
        assertEquals(10, WeightedIntervalScheduling.findOptimalInterval(intervals));
    }

    @Test
    public void studentBudget() throws IOException {
        InputStream in = new FileInputStream("src/assets/DynamicProgramming/studentBudget.txt");
        Assert.assertEquals("25", StudentBudget.run(in));
    }

    @Test
    public void minSubsetStudentBudget() {
        int weights[] = { 8, 3, 5};
        int values[] = { 25, 4, 9};

        int total = 10;

        int dp[][] = StudentBudget.getMatrix(weights, values, total);

        Set<Integer> result = new HashSet<>();

        result.add(8);

        assertEquals(result, StudentBudget.getSubset(weights, dp, total));
    }

    @Test
    public void lcsTest() {
        String s1 = "abcdaf";
        String s2 = "acbcf";

        assertEquals(4, LongestCommonSubsequence.LCSlength(s1, s2));

        String ss1 = "HELLOM";
        String ss2 = "HMLDL";

        assertEquals(3, LongestCommonSubsequence.LCSlength(ss1, ss2));
    }

    @Test
    public void wineProfitTest() {
        int[] wines = { 1, 4, 2, 3};

        assertEquals(29, WineProfit.wineProfit(wines));

        int[] wines2 = { 2, 3, 5, 1, 4};

        assertEquals(50, WineProfit.wineProfit(wines2));

    }

    @Test
    public void coinsChange() {
        int[] coins = { 1, 2, 3};

        assertEquals(5, UnboundedKnapsack.coinChange(coins, 5));

        int[] coins2 = { 1, 5, 6, 8};

        int dp[][] = UnboundedKnapsack.minimumNumberOfCoins(coins2, 11);
        assertEquals(2, dp[2][11]);

        Set<Integer> result = new HashSet<>();

        result.add(5);
        result.add(6);

        assertEquals(result, UnboundedKnapsack.minimumSubSetOfCoins(coins2, dp,11));
    }

    @Test
    public void fishSalesman() {
        int n = 5;
        int[] P = { 0, 80, 30, 30, 70, 80 };
        int[] Q = { 0, 90, 60, 60, 50, 20 };
        assertEquals(300, FishSalesman.solve(n, P, Q));
    }

    @Test
    public void timeWarping() {
        int[] first = { 0, 1, 1, 2, 2, 3, 5 };
        int[] second = { 0, 1, 2, 3, 5, 5, 5, 6 };
        assertEquals(1, DynamicTimeWarping.findMinDistance(first, second));
    }

    @Test
    public void rodCutting() {
        int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20 };

        assertEquals(22, RodCutting.getMaximumPrice(prices, 8));
    }

    @Test
    public void floydWarshall() {
        int inf = Integer.MAX_VALUE/2;
        int graph[][] = {
                        {0,   3,  6, 15},
                        {inf, 0, -2, inf},
                        {inf, inf, 0, 2 },
                        { 1, inf, inf, 0}
        };

        int distances[][] = { { 0, 3, 1, 3 },
                              { 1, 0, -2, 0 },
                              { 3, 6, 0, 2 },
                              { 1, 4, 2, 0 } };

        assertArrayEquals(distances, FloydWarshall.findMinimumDistance(graph));
    }

    @Test
    public void example3() {
        String a = "kitten";
        String b = "sitting";
        assertEquals(3, AligningSequences.solve(a, b));
    }

    @Test
    public void exampleDC() {
        String a = "kitten";
        String b = "sitting";
        assertEquals(3, AligningSequences.spaceEfficientAlignment(a, b));

        List<Integer> p = AligningSequences.findAlignment(a, b);

        for(int i = 0; i < p.size(); i++) System.out.println(p.get(i));
        //AligningSequences.findAlignment("jasmine", "hasmin");
    }

    @Test
    public void dijkstraOnSteroids() {
        int n = 2;
        int m = 5;
        int[][] graph = { { 2, 10, 1, 3, 4 }, { 2, 2, 2, 10, 2 } };
        assertEquals(2, DijkstraOnSteroids.solve(n, m, graph));
    }

    @Test
    public void prettyPrintingTA() {
        InputStream in = new ByteArrayInputStream(("42\n14\nThe Answer to the Great Question of Life, " + "the Universe and Everything is Forty-two.").getBytes());
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("The Answer to the Great Question of Life,");
        expected.add("the Universe and Everything is Forty-two.");
        assertEquals(expected, PrettyPrinting.solve(in));
    }

    @Test
    public void prettyPrintingTA2() {
        InputStream in = new ByteArrayInputStream(("16\n7\nThis is an example of text justification.").getBytes());
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("This is an");
        expected.add("example of text");
        expected.add("justification.");

        assertEquals(expected, PrettyPrinting.solve(in));
    }

    @Test
    public void prettyPrintingTA3() {
        InputStream in = new ByteArrayInputStream(("16\n6\nWhat must be acknowledgment shall be").getBytes());
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("What must be");
        expected.add("acknowledgment");
        expected.add("shall be");
        assertEquals(expected, PrettyPrinting.solve(in));
    }

    @Test
    public void prettyPrintingTA4() {
        InputStream in = new ByteArrayInputStream(("20\n18\nScience is what we understand well enough to explain to a computer. Art is everything else we do").getBytes());
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Science is what we");
        expected.add("understand well");
        expected.add("enough to explain to");
        expected.add("a computer. Art is");
        expected.add("everything else we");
        expected.add("do");

        assertEquals(expected, PrettyPrinting.solve(in));
    }

    // RNA exercise
    @Test
    public void example_no_compat() {
        int n = 8;
        char[] b = "\0AUCGAUCG".toCharArray();
        boolean[][] p = new boolean[n + 1][n + 1];
        p[1][3] = true;
        /* We can not combine any (due to the p values), so the largest set will have 0 elements.
         * Note that the 1-3 pairing doesn't work as 1 and 3 are less than 4 apart.
         */
        assertEquals(0, Biology101.solve(n, b, p));
    }

    @Test
    public void example_one_compat() {
        int n = 8;
        char[] b = "\0AUCGAUCG".toCharArray();
        boolean[][] p = new boolean[n + 1][n + 1];
        p[1][6] = true;
        /* We can only combine 1 with 6 which should get us a set of size 1.
         */
        assertEquals(1, Biology101.solve(n, b, p));
    }

    @Test
    public void palindromicSubstring() {
        String s = "cbbd";
        assertEquals("bb", PalindromicSubstring.longestPalindrome(s));
    }

    @Test
    public void minimumJumps() {
        int[] arr = { 2, 3, 2, 4, 4 };
        assertEquals(1, JumpsToReachEnd.minimumJumps(arr));
    }

    @Test
    public void wildcardMatching() {
        String s = "aa";
        String p = "*";

        assertEquals(true, WildcardMatching.isMatch(s, p));
        //assertEquals(false, WildcardMatching.isMatch("aa", "a"));
        //assertEquals(false, WildcardMatching.isMatch("cb", "?a"));
        //assertEquals(true, WildcardMatching.isMatch("adceb", "a*b"));
        //assertEquals(false, WildcardMatching.isMatch("acdcb", "a*c?b"));

    }

    @Test
    public void parenthesesMatching() {
        assertEquals(0, LongestValidParentheses.longestValidParentheses(")("));
        assertEquals(2, LongestValidParentheses.longestValidParentheses("()()"));
        assertEquals(0, LongestValidParentheses.longestValidParentheses(""));
        assertEquals(1, LongestValidParentheses.longestValidParentheses("(()"));
    }

    @Test
    public void LIS() {
        int[] first = new int[] { 1, 3, 5, 4, 7 };
        int[] second = new int[] { 5, 2, 8, 6, 3, 6, 9, 5 };

        assertEquals(4, LongestIncreasingSubsequence.LIS(first));
        assertEquals(4, LongestIncreasingSubsequence.LIS(second));

        // Check if the list contains the correct elements
        int memOne[] = { 1, 1, 2, 2, 3};
        int memTwo[] = { 1, 1, 2, 2, 2, 3, 4, 3};

        List<Integer> resOne = new ArrayList<>();
        resOne.add(1);
        resOne.add(2);
        resOne.add(5);

        List<Integer> resTwo = new ArrayList<>();
        resTwo.add(2);
        resTwo.add(3);
        resTwo.add(6);
        resTwo.add(9);

        assertEquals(resOne, LongestIncreasingSubsequence.findElements(first, 3, memOne));
        assertEquals(resTwo, LongestIncreasingSubsequence.findElements(second, 4, memTwo));

    }

    @Test
    public void Subsequence() {
        assertEquals(true, IsSubsequence.isSubsequence("abc", "ahbgdc"));
        assertEquals(false, IsSubsequence.isSubsequence("axc", "ahbgdc"));
    }

    @Test
    public void coins() {
        assertEquals(4, UnboundedKnapsack.change(5, new int[] {1,2,5}));
        assertEquals(717, UnboundedKnapsack.change(500, new int[] {2,7,13}));
    }

    @Test
    public void onesAndZeros() {
        assertEquals(3, OnesAndZeros.findMaxForm(new String[] {"10","0001","111001","1","0"}, 4, 3));
    }

    @Test
    public void maxScore() {
        String[] words = {"dog","cat","dad","good"};
        char[] letters = {'a','a','c','d','d','d','g','o','o'};
        int[] score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};
        assertEquals(23, MaximumScoreWords.maxScoreWords(words, letters, score));
    }

    @Test
    public void maxScore2() {
        String words[] = {"get", "set"};
        char[] letters = {'g', 's', 'e', 't'};
        int[] score = {0,0,0,0,1,0,2,0,0,0,0,0,0,0,0,0,0,0,3,1,0,0,0,0,0,0};

        assertEquals(5, MaximumScoreWords.maxScoreWords(words, letters, score));
    }

    @Test
    public void maxProfit() {
        assertEquals(3, BuySellStocks.maxStockProfit(new int[]{1,2,3,0,2}));
    }

    @Test
    public void testExampleA() {
        int n = 4;
        int h = 6;
        int[][] f = {{0, 0, 0, 0, 0, 0, 0},
                {1, 1, 2, 3, 4, 4, 4},
                {1, 6, 6, 6, 6, 6, 6},
                {1, 2, 2, 2, 2, 2, 2},
                {1, 1, 2, 3, 4, 7, 10}};
        // 6 + 7 + 2 = 15
        assertEquals(15, TightOnTime.maxGradeDP(n, h, f));
    }

    @Test
    public void testExampleB() {
        int n = 3;
        int h = 7;
        int[][] f = {{0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 4, 6, 9, 9, 10, 10},
                {1, 1, 6, 6, 6, 6, 6, 6},
                {1, 3, 3, 3, 3, 3, 3, 3}};
        // 9 + 6 + 3 = 18
        assertEquals(18, TightOnTime.maxGradeDP(n, h, f));
    }

    @Test
    public void testOneAssignment() {
        int n = 1;
        int h = 5;
        int[][] f = {{0, 0, 0, 0, 0, 0}, {1, 2, 4, 6, 8, 10}};
        // Only one assignment to choose from, spend max hours on it
        assertEquals(10, TightOnTime.maxGradeDP(n, h, f));
    }

    @Test
    public void testNoTimeLeft() {
        int n = 2;
        int h = 1;
        int[][] f = {{0, 0}, {1, 2}, {1, 4}};
        // Only one hour to spare, pick the second assignment with max grade
        assertEquals(5, TightOnTime.maxGradeDP(n, h, f));
    }

    @Test
    public void testNoTimeAtAll() {
        int n = 2;
        int h = 0;
        int[][] f = {{0, 0}, {1, 2}, {1, 4}};
        // Only get base grades from the assignments
        assertEquals(2, TightOnTime.maxGrade(n, h, f));
    }

    @Test
    public void testWorkOnEveryAssignment() {
        int n = 8;
        int h = 8;
        int[][] f = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 7, 7, 7, 7, 7, 7, 7, 7},
                {1, 3, 3, 3, 3, 3, 3, 3, 3},
                {1, 2, 2, 2, 2, 2, 2, 2, 2},
                {1, 4, 4, 4, 4, 4, 4, 4, 4},
                {1, 5, 5, 5, 5, 5, 5, 5, 5},
                {1, 10, 10, 10, 10, 10, 10, 10, 10},
                {1, 7, 7, 7, 7, 7, 7, 7, 7},
                {1, 4, 4, 4, 4, 4, 4, 4, 4}};
        // Spend exactly 1 hour per assignment
        assertEquals(42, TightOnTime.maxGradeDP(n, h, f));
    }

    @Test
    public void findDistinctSubsequences() {
        assertEquals(5, DistinctSubsequences.numDistinct("babgbag", "bag"));
        assertEquals(700531452, DistinctSubsequences.numDistinct("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc", "bcddceeeebecbc"));
    }

    @Test
    public void minimumGeneticMutation() {
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {"AACCGATT","AACCGATA","AAACGATA","AAACGGTA"};

        assertEquals(4, MinimumGeneticMutation.minMutation(start, end, bank));
    }

    @Test
    public void findMinimumVideoclips() {
        int[][] clips = {{0,2},{4,6},{8,10},{1,9},{1,5},
                {5,9}};
        int T = 10;

        assertEquals(3, VideoStitching.videoStitching(clips, T));
    }

    @Test
    public void findMinimumVideoclips2() {
        int[][] clips = {{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},
                {1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
        int T = 9;

        assertEquals(3, VideoStitching.videoStitching(clips, T));
    }

    @Test
    public void houseRobber2() {
        assertEquals(3, HouseRobber2.rob(new int[] {2,3,2}));
        assertEquals(4, HouseRobber2.rob(new int[] {1,2,3,1}));
        assertEquals(103, HouseRobber2.rob(new int[] {1,3,1,3,100}));
        assertEquals(3, HouseRobber2.rob(new int[] {2,1,1,2}));
    }

    @Test
    public void decodeWays() {
        assertEquals(4, DecodeWays.numDecodings("2611055971756562"));
    }

    @Test
    public void houseRobber() {
        TreeNode n = new TreeNode(3, new TreeNode(2, null,  new TreeNode(3)),
                new TreeNode(3, null, new TreeNode(1)));

        assertEquals(0, HouseRobber3.rob(n));
    }

    @Test
    public void wordBreak() {
        String s = "leetcode";
        ArrayList<String> dict = new ArrayList<>();
        dict.add("leet");
        dict.add("code");
        assertEquals(WordBreak.wordBreak(s, dict), true);
    }

    @Test
    public void wordBreak2() {
        String s = "applepenapple";
        ArrayList<String> dict = new ArrayList<>();
        dict.add("apple");
        dict.add("pen");
        assertEquals(WordBreak.wordBreak(s, dict), true);
    }

    @Test
    public void wordBreak3() {
        String s = "catsandog";
        ArrayList<String> dict = new ArrayList<>();
        dict.add("cats");
        dict.add("dog");
        dict.add("sand");
        dict.add("and");
        dict.add("cat");

        assertEquals(WordBreak.wordBreak(s, dict), false);
    }


    @Test
    public void wordBreak4() {
        String s = "catsanddog";
        ArrayList<String> dict = new ArrayList<>();
        dict.add("cats");
        dict.add("dog");
        dict.add("sand");
        dict.add("and");
        dict.add("cat");

        assertEquals(WordBreak.findPath(s, dict), null);
    }

    @Test(timeout=100)
    public void wordSegmentation() {
        String text = "lawyersareawesome";
        String[] words = { "lawy", "ersar", "eawe", "so", "me", "lawyers", "are", "awesome" };
        int[] values = { 1, 1, 1, 2, 3, 5, 4, 10};

        assertEquals(19, WordSegmentation.wordSegmentation(text, words, values));
    }

    @Test(timeout=100)
    public void wordSegmentationInvalid() {
        String text = "catsandog";
        String[] words = { "cats","dog","sand","and","cat" };
        int values[] = { 1, 1, 1, 1, 1 };

        assertEquals(-1, WordSegmentation.wordSegmentation(text, words, values));
    }

    @Test(timeout=100)
    public void wordSegmentationBigInvalid() {
        String text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String[] words = { "a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa" };
        int values[] = { 1, 2, 2, 3, 2, 1, 6, 10, 3, 2 };

        assertEquals(-1, WordSegmentation.wordSegmentation(text, words, values));
    }

    @Test(timeout = 100)
    public void wordSegmentationBig() {
        String text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String[] words = { "a", "aaaaaaa","aaaaaaaa"};
        int values[] = { 1, 6, 10 };

        assertEquals(40, WordSegmentation.wordSegmentation(text, words, values));
    }

    @Test
    public void findVitalRoutes() {
        int n = 4;
        int v = 2;
        int w = 4;

        int[][] vital = {
                { 0, 0, 1, 0},
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0},
                { 1, 0, 0, 0} };
        int[][] nonVital = {
                {0, 1, 0, 0},
                { 1, 0, 0, 0},
                { 0, 1, 0, 1},
                {0, 0, 0, 0} };

        assertEquals(1, BusRoutes.findBusRoutes(n, v, w, vital, nonVital));
    }

    @Test
    public void billboardConstructions() {
        int M = 20;
        int n = 4;
        int[] sites = { 0, 12, 7, 6, 14 };
        int[] values = { 0,  5, 6, 5, 1 };

        assertEquals(10, Billboards.billboardConstructions(M, n, sites, values));
    }

    @Test
    public void cuttingEdgeBiotech01() {
        String s = "jasmineisthebest";
        String[] words = { "jasmine", "is", "the", "best" };

        assertEquals(0, CuttingEdgeBiotech.minCost(s, words));
    }

    @Test
    public void cuttingEdgeBiotech02() {
        String s = "jasmineisthebest";
        String[] words = { "jasmin", "is", "the", "best" };

        assertEquals(1, CuttingEdgeBiotech.minCost(s, words));
    }

    @Test
    public void cuttingEdgeBiotech03() {
        String s = "jasminejasminejas";
        String[] words = { "jasmin", "is", "the", "best" };

        assertEquals(5, CuttingEdgeBiotech.minCost(s, words));
    }

    @Test
    public void findOptimalJobs() {
        int n = 4;
        int low[] = { 0, 10, 1, 10, 10 };
        int high[] = { 0, 5, 50, 5, 1 };

        assertEquals(70, StressJobs.findOptimalJobs2(n, low, high));
    }

    @Test
    public void findLongestPath() {
        int n = 5;
        Node[] nodes = new Node[6];
        Edge[] edges = new Edge[6];

        for(int i = 0; i <= n; i++) nodes[i] = new Node(i);

        edges[0] = new Edge(nodes[1], nodes[2], 1);
        edges[1] = new Edge(nodes[1], nodes[4], 1);
        edges[2] = new Edge(nodes[2], nodes[4], 1);
        edges[3] = new Edge(nodes[2], nodes[5], 1);
        edges[4] = new Edge(nodes[3], nodes[4], 1);
        edges[5] = new Edge(nodes[4], nodes[5], 1);

        assertEquals(3, LongestPath.findLongestPath(n, edges));
    }

    @Test
    public void findLongestPath2() {
        int n = 5;
        Node[] nodes = new Node[6];
        Edge[] edges = new Edge[6];

        for(int i = 0; i <= n; i++) nodes[i] = new Node(i);

        edges[0] = new Edge(nodes[1], nodes[2], 1);
        edges[1] = new Edge(nodes[1], nodes[4], 1);
        edges[2] = new Edge(nodes[2], nodes[4], 1);
        edges[3] = new Edge(nodes[2], nodes[5], 1);
        edges[4] = new Edge(nodes[3], nodes[4], 1);
        edges[5] = new Edge(nodes[4], nodes[5], 1);

        assertEquals(3, LongestPath.findLongestPath(n, edges));
    }

    @Test
    public void findMaxKills() {
        int n = 4;

        int[] f = { 0, 1, 2, 4, 8};
        int[] x = { 0, 1, 10, 10, 1 };

        assertEquals(5, KillingRobots.findMaxKills(n, f, x));
    }

    @Test
    public void findTerabytes() {
        int n = 4;

        int x[] = { 0, 10, 1, 7, 7 };
        int s[] = { 0, 8, 4, 2, 1 };

        assertEquals(19, Terabytes.findOptimalTerabytes(n, x, s));
    }

    @Test
    public void findOptimalSupply() {
        int n = 10;
        int[] supply = { 0, 11, 9, 9, 12, 12, 12, 12, 9, 9, 11 };
        int r = 1;
        int c = 10;

        assertEquals(98, PCManufacturers.findPerfectSupply(n, supply, r, c));
    }

    @Test
    public void findOptimalEvents() {
        int n = 9;
        int[] coordinates = { 0, 1, -4, -1, 4, 5, -4, 6, 7, -2 };
        assertEquals(4, AstronomyDepartment.optimalEvents(n, coordinates));
    }

    @Test
    public void findOptimalEvents2() {
        int n = 6;
        int[] coordinates = { 0, 3, 2, -3, -2, -1, 0 };
        assertEquals(4, AstronomyDepartment.optimalEvents(n, coordinates));
    }

    @Test
    public void findOptimalEvents3() {
        int n = 6;
        int[] coordinates = { 0, 3, 2, 3, 4, 5, 0 };
        assertEquals(4, AstronomyDepartment.optimalEvents(n, coordinates));
    }

    @Test
    public void findOptimalEvents4() {
        int n = 6;
        int[] coordinates = { 0, 3, 4, 5, 6, 7, 0 };
        assertEquals(3, AstronomyDepartment.optimalEvents(n, coordinates));
    }

    @Test
    public void findOptimalEvents5() {
        int n = 6;
        int[] coordinates = { 0, 0, 1, 5, 6, 7, 0 };
        assertEquals(3, AstronomyDepartment.optimalEvents(n, coordinates));
    }

    @Test
    public void findOptimalEvents6() {
        int n = 2;
        int[] coordinates = { 0, 0, 1 };
        assertEquals(2, AstronomyDepartment.optimalEvents(n, coordinates));
    }

    @Test
    public void findRisingTrend() {
        int n = 7;
        int[] stocks = { 0, 10, 1, 2, 11, 3, 4, 12};

        assertEquals(3, RisingTrends.risingTrend(n, stocks));
        assertEquals(1, RisingTrends.risingTrend(2, new int[] { 0, 10, 2}));

    }

    @Test
    public void findRisingTrend2() {
        int n = 5;
        int[] stocks = { 0, 1, 4, 2, 3, 2};

        assertEquals(3, RisingTrends.risingTrend(n, stocks));
    }
}

