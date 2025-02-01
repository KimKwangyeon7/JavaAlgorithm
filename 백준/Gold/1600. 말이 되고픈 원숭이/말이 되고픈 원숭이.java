
import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};
    static int[] cx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] cy = {-2, -1, 1, 2, -2, -1, 1, 2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] board = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS 탐색을 위한 큐 (x, y, 사용한 말 점프 횟수, 이동 거리)
        Queue<int[]> qu = new ArrayDeque<>();
        boolean[][][] visited = new boolean[H][W][K + 1];

        qu.offer(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;
        
        int ans = -1;

        while (!qu.isEmpty()) {
            int[] tmp = qu.poll();
            int x = tmp[0], y = tmp[1], jump = tmp[2], dist = tmp[3];

            // 도착 지점에 도달하면 종료
            if (x == H - 1 && y == W - 1) {
                ans = dist;
                break;
            }

            // 말처럼 점프할 수 있는 경우
            if (jump < K) {
                for (int k = 0; k < 8; k++) {
                    int nx = x + cx[k];
                    int ny = y + cy[k];

                    if (isBoundary(nx, ny, W, H) && board[nx][ny] == 0 && !visited[nx][ny][jump + 1]) {
                        visited[nx][ny][jump + 1] = true;
                        qu.offer(new int[]{nx, ny, jump+1, dist+1});
                    }
                }
            }

            // 일반적인 상하좌우 이동
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (isBoundary(nx, ny, W, H) && board[nx][ny] == 0 && !visited[nx][ny][jump]) {
                    visited[nx][ny][jump] = true;
                    qu.offer(new int[]{nx, ny, jump, dist+1});
                }
            }
        }
        System.out.println(ans);
    }

    private static boolean isBoundary(int x, int y, int W, int H) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }
}
