import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
class Shark{
	int x;
	int y;
	int s; // 속도
	int d; // 방향
	int z; // 크기
	public Shark(int x, int y, int s, int d, int z) {
		super();
		this.x = x;
		this.y = y;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}
public class Main {
	static int[] dx = {0, -1, 1, 0, 0}; // 위 아래 오른쪽 왼쪽 
	static int[] dy = {0, 0, 0, 1, -1};
	static int R, C, M;
	static List<Shark>[][] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행수
		C = Integer.parseInt(st.nextToken()); // 열수
		M = Integer.parseInt(st.nextToken()); // 상어수
		
		int x; // 좌표
		int y;
		int s; // 속도
		int d; // 방향
		int z; // 크기
		int sum = 0;
		
		list = new ArrayList[R+1][C+1]; // 상어 정보 저장할 2차원 어레이리스트
		for (int i = 0; i < R+1; i++) {
			for (int j = 0; j < C+1; j++) {
				list[i][j] = new ArrayList<Shark>();
			}
		}

		for (int i = 0; i < M; i++) { // 상어 정보 입력 받기
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			
			list[x][y].add(new Shark(x, y, s, d, z));
		}

		int time = 0;
		List<Shark> check;
		
		while (true) {
			time++;
			// 땅에서 가장 가까운 상어 잡기
			for (int i = 1; i < R+1; i++) {
				if (list[i][time].size() != 0) {
					sum += list[i][time].get(0).z;
					list[i][time].remove(0);
					break;
				}
			}
			if (time == C) { // 배열 범위를 넘어서면
				break;
			}
			// 상어 움직이기
			check = new ArrayList<>(); // 이동한 상어 정보 담을 어레이리스트		
			Shark tmp;
			int dis = 0;
			int tx, ty = 0;
			for (int i = 1; i < R+1; i++) {
				for (int j = 1; j < C+1; j++) {
					if (list[i][j].size() != 0) { // 상어가 있으면
						tmp = list[i][j].get(0);
						dis = tmp.s;
						tx = i;
						ty = j;
						while (dis > 0) {
							if (isBoundary(tx+dx[tmp.d], ty+dy[tmp.d])) {
								tx += dx[tmp.d];
								ty += dy[tmp.d];
								dis--;
							} else { // 방향 전환
								if (tmp.d == 1) {
									tmp.d = 2;
								} else if (tmp.d == 2) {
									tmp.d = 1;
								} else if (tmp.d == 3) {
									tmp.d = 4;
								} else {
									tmp.d = 3;
								}
							}
						}
						check.add(new Shark(tx, ty, tmp.s, tmp.d, tmp.z)); // 이동한 상어 정보 저장
						list[i][j].clear(); // 이동 전 좌표 초기화
					}
				}
			}

			for (Shark sh: check) {
				list[sh.x][sh.y].add(sh);
			}
			for (int i = 1; i < R+1; i++) {
				for (int j = 1; j < C+1; j++) {
					if (list[i][j].size() > 1) {
						int max = 0;
						s = 0;
						d = 0;
						for (Shark sh: list[i][j]) {
							if (sh.z > max) {
								max = sh.z;
								s = sh.s;
								d = sh.d;
							}
						}
						list[i][j].clear();
						list[i][j].add(new Shark(i, j, s, d, max));
					}
				}
			}
		}
		System.out.println(sum);	
	}
	static boolean isBoundary(int x, int y) {
		return x >= 1 && x < R+1 && y >= 1 && y < C+1;
	}
}