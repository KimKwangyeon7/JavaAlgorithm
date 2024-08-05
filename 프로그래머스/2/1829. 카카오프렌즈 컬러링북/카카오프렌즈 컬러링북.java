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
		// picture를 직접 수정하면 문제에서 오류를 일으킴
		int[][] copy = picture.clone();
		
		boolean[][] visited = new boolean[m][n];
		int p = copy.length;
        int q = copy[0].length;
		for (int i = 0; i < p; i++){
			for (int j = 0; j < q; j++){
				if (copy[i][j] > 0 && !visited[i][j]){
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
                            if (copy[xx][yy] == copy[tmp[0]][tmp[1]] && !visited[xx][yy]){
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