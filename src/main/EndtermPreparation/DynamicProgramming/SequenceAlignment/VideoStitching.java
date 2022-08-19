package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

import java.util.ArrayList;
import java.util.Arrays;

public class VideoStitching {
    public static int videoStitching(int[][] clips, int T) {
        if(T == 0) return 0;

        // 1. Sort clips
        Arrays.sort(clips, (a, b) -> (a[0] - b[0]));

        // Base case
        if(clips[0][0] != 0) return -1;

        // 2. Remove overlapping clips, take the ones with highest length
        int n = clips.length;

        ArrayList<Clip> clipsList = new ArrayList<>();

        clipsList.add(new Clip(clips[0][0], clips[0][1]));

        for(int i = 1; i < n; i++) {
            int start = clips[i][0];
            int end = clips[i][1];

            int prevStart = clipsList.get(clipsList.size() - 1).startTime;
            int prevEnd = clipsList.get(clipsList.size() - 1).endTime;

            // If the clip starts at the same index and has longest duration, take the one with highest length
            if(prevStart == start) {
                if(end > prevEnd) {
                    clipsList.remove(clipsList.size() - 1);
                    clipsList.add(new Clip(clips[i][0], clips[i][1]));
                }
            }

            // If the clip doesn't cover a smaller part of the previous clip
            else if (!(start <= prevEnd && end <= prevEnd)) {
                clipsList.add(new Clip(clips[i][0], clips[i][1]));
            }
        }

        // Create jump array
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int[] jump = new int[T + 1];
        Arrays.fill(jump, 0);

        // Fill in jump array with the max index it can reach
        for(Clip current : clipsList) {
            if(current.startTime < T) jump[current.startTime] = current.endTime;
        }

        // Now check the minimum overlapping clips and store in dp
        for(int i = 0; i < T; i++) {
            if(jump[i] != 0) {
                for(int j = i + 1; j <= jump[i] && j <= T; j++)
                    dp[j] = Math.min(dp[j], 1 + dp[i]);
            }
        }

        for(int i = 0; i <= T; i++) {
            if(dp[i] == Integer.MAX_VALUE) return -1;
        }

        return dp[T];
    }
}

class Clip {
    int startTime;
    int endTime;

    public Clip(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}