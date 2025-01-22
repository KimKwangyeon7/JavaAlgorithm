

/**
 * @author kwang
 *
 */
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        board = new int[N][M];

        int pie = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
                if (board[i][j] == 1) pie++;
            }
        }

        int time = 0;
        while (pie > 0) {
            // 외부 공기 업데이트
            updateAir();

            // 치즈 녹이기
            pie = meltCheese(pie);

            time++;
        }
        System.out.println(time);
    }

    private static void updateAir() {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;

        // 외부 공기를 표시
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = cur[0] + dx[k];
                int y = cur[1] + dy[k];
                if (isBoundary(x, y) && !visited[x][y] && board[x][y] != 1) {
                    visited[x][y] = true;
                    board[x][y] = 2; // 외부 공기 표시
                    queue.offer(new int[] {x, y});
                }
            }
        }
    }

    private static int meltCheese(int pie) {
        List<int[]> meltList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    int airContact = 0;
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (isBoundary(x, y) && board[x][y] == 2) {
                            airContact++;
                        }
                    }
                    if (airContact >= 2) {
                        meltList.add(new int[] {i, j});
                    }
                }
            }
        }

        // 치즈 녹이기
        for (int[] pos : meltList) {
            board[pos[0]][pos[1]] = 0;
            pie--;
        }
        return pie;
    }

    private static boolean isBoundary(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
