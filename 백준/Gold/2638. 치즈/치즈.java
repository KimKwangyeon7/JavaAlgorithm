
import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        int pie = 0;
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                	pie++;
                }
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
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[] {0, 0});
        visited[0][0] = true;

        // 외부 공기를 표시
        while (!qu.isEmpty()) {
            int[] tmp = qu.poll();
            for (int k = 0; k < 4; k++) {
                int x = tmp[0] + dx[k];
                int y = tmp[1] + dy[k];
                if (isBoundary(x, y) && !visited[x][y] && board[x][y] != 1) {
                    visited[x][y] = true;
                    board[x][y] = 2; // 외부 공기 표시
                    qu.offer(new int[] {x, y});
                }
            }
        }
    }

    private static int meltCheese(int pie) {
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
                        board[i][j] = 0;
                        pie--;
                    }
                }
            }
        }
        return pie;
    }

    private static boolean isBoundary(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
