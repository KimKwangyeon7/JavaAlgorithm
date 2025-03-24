import java.util.*;

class Solution {
    static class Point implements Comparable<Point> {
        int x, y, cost;
        public Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int[][] land, int height) {
        int N = land.length;
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Point> pq = new PriorityQueue<>();

        pq.offer(new Point(0, 0, 0));
        int totalCost = 0;

        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;
            totalCost += cur.cost;

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

                int diff = Math.abs(land[cur.x][cur.y] - land[nx][ny]);
                int nextCost = diff <= height ? 0 : diff;
                pq.offer(new Point(nx, ny, nextCost));
            }
        }

        return totalCost;
    }
}
