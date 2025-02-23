

import java.io.*;
import java.util.*;

public class Main {
    static int N, ansBlack, ansWhite;
    static int[][] board;
    static List<int[]> blackList, whiteList;
    static boolean[] leftDiagonal, rightDiagonal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        blackList = new ArrayList<>();
        whiteList = new ArrayList<>();
        leftDiagonal = new boolean[2 * N];
        rightDiagonal = new boolean[2 * N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    if ((i + j) % 2 == 0) blackList.add(new int[]{i, j});
                    else whiteList.add(new int[]{i, j});
                }
            }
        }

        // 흑과 백을 따로 탐색
        ansBlack = 0;
        ansWhite = 0;
        dfs(0, 0, blackList, true);  // 흑
        dfs(0, 0, whiteList, false); // 백

        System.out.println(ansBlack + ansWhite);
    }

    private static void dfs(int L, int cnt, List<int[]> list, boolean isBlack) {
        if (L == list.size()) {
            if (isBlack) ansBlack = Math.max(ansBlack, cnt);
            else ansWhite = Math.max(ansWhite, cnt);
            return;
        }

        int x = list.get(L)[0];
        int y = list.get(L)[1];
        int leftIdx = x + y;
        int rightIdx = x - y + (N - 1);

        if (!leftDiagonal[leftIdx] && !rightDiagonal[rightIdx]) {
            leftDiagonal[leftIdx] = true;
            rightDiagonal[rightIdx] = true;
            dfs(L + 1, cnt + 1, list, isBlack);
            leftDiagonal[leftIdx] = false;
            rightDiagonal[rightIdx] = false;
        }

        // 해당 위치에 비숍을 놓지 않고 진행
        dfs(L + 1, cnt, list, isBlack);
    }
}
