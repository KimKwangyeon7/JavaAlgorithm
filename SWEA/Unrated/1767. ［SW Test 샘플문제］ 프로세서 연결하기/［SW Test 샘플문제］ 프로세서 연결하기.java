import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SW_1767_프로세서연결하기
 * 
 * 아이디어 
 * 고민하다가 결국 어떤 식으로 풀어나가는지 아이디어를 참고함. 아이디어만 보고 코드는 혼자 구현함
 * 전원을 연결할 코어의 개수를 최대로 해야 하는데 그 개수를 M에서 시작해서 하나씩 줄여나가며 조합을 구함
 * 그 조합일 때 상하좌우를 DFS해서 가능한지 여부를 판별 -> 가능하면 그 개수를 저장해 전선의 최소값 구하기
 * 일단 답과 달라서 디버깅을 해봤는데 아직 정확한 이유를 찾지 못했습니다
 * 최대한 다시 고쳐보겠습니다.
 * @author 김광연
 *
 */
public class Solution {

	static int[][] board, map;
	static List<int[]> core;
	static int len, ans, comp, N;
	static int[] res;
	static int[] dx = {1, 0, -1, 0}; // 상, 우, 하, 좌
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			core = new ArrayList<>();
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i != 0 && i != N-1 && j != 0 && j != N-1) {
							core.add(new int[] {i, j});
						} else {
							cnt++; // 모서리에 있는 core들 (이미 연결)
						}
					}
				}
			}
			len = core.size();
			for (int i = len; i > 0; i--) { // 이미 전원이 연결된 가장자리에 있는 코어를 제외한 나머지 코어들
				res = new int[i];
				ans = 2147000000;
				combi(i, 0, 0);
				if (ans == 2147000000) {
					continue;
				} else {
					sb.append(ans).append("\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}
	private static void combi(int size, int cnt, int start) {
		if (cnt == size) {
			board = new int[N][N];
			comp = 2147000000;
			for (int i = 0; i < N; i++) {
				board[i] = map[i].clone();
			}
			powerUp(size, 0, 0); // 해당 조합에 전원 연결이 가능할 때
			if (comp != 2147000000) {
				ans = Math.min(ans, comp);
			}
			return;
		}
		for (int i = start; i < len; i++) {
			res[cnt] = i;
			combi(size, cnt+1, i+1);
		}
		
	}
	private static void powerUp(int size, int lv, int sum) { // dfs -> 전선의 합이 최소인 경우 구하기
		if (sum > comp) {
			return;
		}
		if (lv == size) {
			comp = Math.min(comp, sum);
			return;
		}
		for (int i = 0; i < 4; i++) { // 상, 우, 하, 좌
			int flag = 0;
			int x = core.get(res[lv])[0];
			int y = core.get(res[lv])[1];
			int cnt = 0;
			while (isBoundary(x+dx[i], y+dy[i])) { // 해당 방향으로 한칸전진하면 범위 안에 들때
				if (board[x+dx[i]][y+dy[i]] != 0) { // 해당 방향에 걸림돌 존재
					break; // 반복문 탈출
				}
				x += dx[i];
				y += dy[i];
				cnt++;
				board[x][y] = 2; // 방문 표시
				if (!isBoundary(x+dx[i], y+dy[i])) {
					// 한 칸 더 갔을 때 범위를 벗어난다는 것은 현 위치가 가장자리 -> 전원 연결 성공
					flag = 1;
					break;
				}
			}
			int tmpx = x;
			int tmpy = y;
			int tcnt = cnt;
			if (flag == 1) { // 전원 연결 가능
				powerUp(size, lv+1, sum+cnt);
			} 
			// 전원 연결 못하거나 다시 돌아온 경우를 위해 더해준거 다시 되돌려 주기 
			x = tmpx;
			y = tmpy;
			while (tcnt > 0) {
				board[x][y] = 0;
				x -= dx[i];
				y -= dy[i];
				tcnt--;
			}
		}
	}
	static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}