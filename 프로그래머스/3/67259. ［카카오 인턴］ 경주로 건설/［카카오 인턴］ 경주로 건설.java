import java.util.*;

public class Solution {
    static int[] dx = {1, 0, -1, 0}; // 아래, 오른쪽, 위, 왼쪽
    static int[] dy = {0, 1, 0, -1};
    static int N;

    public int solution(int[][] board) {
        N = board.length;
        int[][][] cost = new int[N][N][4]; // 각 방향별 최소 비용 저장
        for (int[][] arr : cost) {
            for (int[] row : arr) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }

        // BFS 탐색을 위한 큐 (x, y, 방향, 비용)
        Queue<int[]> queue = new ArrayDeque<>();
        
        // 초기 설정 (첫 이동은 오른쪽(1) 또는 아래(0) 가능)
        queue.offer(new int[]{0, 0, -1, 0});
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0;
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], prevDir = cur[2], curCost = cur[3];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (!isBoundary(nx, ny) || board[nx][ny] == 1) continue;

                int newCost = curCost + 100;
                if (prevDir != -1 && prevDir != k) {
                    newCost += 500; // 코너 비용 추가
                }

                if (cost[nx][ny][k] > newCost) {
                    cost[nx][ny][k] = newCost;
                    queue.offer(new int[]{nx, ny, k, newCost});
                }
            }
        }

        // 도착점에 도달한 최소 비용 찾기
        return Arrays.stream(cost[N - 1][N - 1]).min().getAsInt();
    }

    private static boolean isBoundary(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
