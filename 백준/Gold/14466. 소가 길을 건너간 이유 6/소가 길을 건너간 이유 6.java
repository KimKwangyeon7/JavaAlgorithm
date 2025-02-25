import java.io.*;
import java.util.*;

public class Main {
    static class Cow {
        int x, y;
        public Cow(int x, int y) { this.x = x; this.y = y; }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static List<int[]>[][] board;
    static List<Cow> cows;
    static int[][] group;
    static int N, K, R;
    static int groupNum = 1;  // 그룹 번호 시작 (0은 미방문 상태)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 길 정보를 저장할 2D 리스트 배열
        board = new ArrayList[N+1][N+1];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                board[i][j] = new ArrayList<>();

        // 길 입력 받기
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            board[x1][y1].add(new int[]{x2, y2});
            board[x2][y2].add(new int[]{x1, y1});
        }

        // 소 위치 저장
        cows = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cows.add(new Cow(x, y));
        }

        // **각 칸이 속한 그룹을 BFS로 구하기**
        group = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (group[i][j] == 0) { // 방문하지 않은 지역이면 BFS 탐색 시작
                    bfs(i, j, groupNum++);
                }
            }
        }

        // **각 소의 그룹을 확인하여 만날 수 없는 쌍 계산**
        int ans = 0;
        for (int i = 0; i < K-1; i++) {
            for (int j = i+1; j < K; j++) {
                if (group[cows.get(i).x][cows.get(i).y] != group[cows.get(j).x][cows.get(j).y]) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

    // **BFS를 이용하여 그룹을 나누는 함수**
    private static void bfs(int sx, int sy, int gNum) {
        Queue<Cow> qu = new ArrayDeque<>();
        qu.offer(new Cow(sx, sy));
        group[sx][sy] = gNum;  // 현재 위치 그룹 할당

        while (!qu.isEmpty()) {
            Cow now = qu.poll();
            int x = now.x, y = now.y;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (!isBoundary(nx, ny) || group[nx][ny] > 0) continue;

                // 길이 있는지 체크
                boolean hasRoad = false;
                for (int[] road : board[x][y]) {
                    if (road[0] == nx && road[1] == ny) {
                        hasRoad = true;
                        break;
                    }
                }
                if (hasRoad) continue; // 길이 있으면 넘어감

                group[nx][ny] = gNum; // 같은 그룹으로 할당
                qu.offer(new Cow(nx, ny));
            }
        }
    }

    private static boolean isBoundary(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }
}
