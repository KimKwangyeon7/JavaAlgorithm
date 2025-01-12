import java.util.*;
class Solution {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int M;
    static int N;
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = { 0, 0 };
		M = m;
        N = n;
		boolean[][] visited = new boolean[m][n];
		int p = picture.length;
        int q = picture[0].length;
		for (int i = 0; i < p; i++){
			for (int j = 0; j < q; j++){
				if (picture[i][j] > 0 && !visited[i][j]){
					answer[0]++;
					int size = 1;
		
                    visited[i][j] = true;

                    Queue<int[]> qu = new ArrayDeque<>();
                    qu.offer(new int[] {i, j});

                    while (!qu.isEmpty()){
                        int[] tmp = qu.poll();

                        for (int k = 0; k < 4; k++){
                            int xx = tmp[0] + dx[k];
                            int yy = tmp[1] + dy[k];

                            if (!isBoundary(xx, yy)){
                                continue;
                            }
                            if (picture[xx][yy] == picture[tmp[0]][tmp[1]] && !visited[xx][yy]){
                                qu.offer(new int[] {xx, yy});
                                visited[xx][yy] = true;
                                size++;
                            }
                        }
		            }
                    answer[1] = Math.max(answer[1], size);
				}
			}
		}
		
		return answer;
    }
    static boolean isBoundary(int x, int y){
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}