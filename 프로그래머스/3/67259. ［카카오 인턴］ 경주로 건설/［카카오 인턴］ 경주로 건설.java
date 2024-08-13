import java.util.*;
public class Solution {
	static class Dot{
		int x;
		int y;
		int sum;
		Dot prev;
		public Dot(int x, int y, int sum, Dot prev) {
			super();
			this.x = x;
			this.y = y;
			this.sum = sum;
			this.prev = prev;
		}
	}
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N;
	public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        N = board.length;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                	continue;
                }
                board[i][j] = Integer.MAX_VALUE;
            }
        }
        board[0][0] = 0;
        
        Queue<Dot> qu = new ArrayDeque<>();
        qu.offer(new Dot(0, 0, 0, null));
        
        while (!qu.isEmpty()) {
        	Dot tmp = qu.poll();
        	if (tmp.x == N-1 && tmp.y == N-1) {
        		answer = Math.min(answer, tmp.sum);
        		continue;
        	}
        	
        	for (int k = 0; k < 4; k++) {
        		int x = tmp.x + dx[k];
        		int y = tmp.y + dy[k];
        		int sum = tmp.sum + 100;
        		
        		if (!isBoundary(x, y) || board[x][y] == 1) {
        			continue;
        		}
        		if (tmp.prev == null) {
        			qu.offer(new Dot(x, y, sum, tmp));
        			continue;
        		}
        		if (tmp.prev.x == x && tmp.prev.y == y) {
        			continue;
        		}
        		if (tmp.prev.x != x && tmp.prev.y != y) { // 커브
        			sum += 500;
        		}
        		if (board[x][y] >= sum) {
        			board[x][y] = sum;
        			qu.offer(new Dot(x, y, sum, tmp));
        		} else if (board[x][y] + 500 >= sum) {
        			qu.offer(new Dot(x, y, sum, tmp));
        		}	
        	}
        }
        
        return answer;
    }
	static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
