

import java.util.*;

class Solution {
    double maxWinRate = 0;
    int[] bestSet;

    public int[] solution(int[][] dice) {
        int N = dice.length;
        bestSet = new int[N/2];
        comb(new ArrayList<>(), 0, N, dice);
        return bestSet;
    }

    private void comb(List<Integer> selected, int start, int N, int[][] dice) {
        if (selected.size() == N / 2) {
            List<Integer> other = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (!selected.contains(i)) other.add(i);
            }
            double winRate = check(selected, other, dice);
            if (winRate > maxWinRate) {
                maxWinRate = winRate;
                for (int i = 0; i < N/2; i++) bestSet[i] = selected.get(i) + 1;
            }
            return;
        }

        for (int i = start; i < N; i++) {
            selected.add(i);
            comb(selected, i + 1, N, dice);
            selected.remove(selected.size() - 1);
        }
    }

    private double check(List<Integer> A, List<Integer> B, int[][] dice) {
        List<Integer> sumA = getAllSums(A, dice);
        List<Integer> sumB = getAllSums(B, dice);

        Collections.sort(sumB);
        int win = 0;

        for (int a : sumA) {
            int lower = lowerBound(sumB, a);   // b < a
            win += lower;
        }

        int total = sumA.size() * sumB.size();
        return (double) win / total;
    }

    private List<Integer> getAllSums(List<Integer> diceIdxList, int[][] dice) {
        List<Integer> sums = new ArrayList<>();
        dfs(0, 0, diceIdxList, dice, sums);
        return sums;
    }

    private void dfs(int depth, int sum, List<Integer> diceIdxList, int[][] dice, List<Integer> result) {
        if (depth == diceIdxList.size()) {
            result.add(sum);
            return;
        }
        int idx = diceIdxList.get(depth);
        for (int face : dice[idx]) {
            dfs(depth + 1, sum + face, diceIdxList, dice, result);
        }
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size()-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
            	left = mid + 1;
            }
            else {
            	right = mid-1;
            }
        }
        return left;
    }
}
