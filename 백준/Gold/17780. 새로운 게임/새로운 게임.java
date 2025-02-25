
import java.io.*;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static int[] dx = new int[] {0, 0, 0, -1, 1};
	static int[] dy = new int[] {0, 1, -1, 0, 0};
	static class Node{
		int x;
		int y;
		int dir;
		
		public Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] board = new int[N+1][N+1];
        List<Integer>[][] list = new ArrayList[N+1][N+1];
        for (int i = 1; i<= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 1; j <= N; j++) {
        		board[i][j] = Integer.parseInt(st.nextToken());
        		list[i][j] = new ArrayList<>();
        	}
        }
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	int dir = Integer.parseInt(st.nextToken());
        	
        	map.put(i+1, new Node(x, y, dir));
        	list[x][y].add(i+1);
        }
        
        for (int T = 0; T < 1000; T++) {
        	for (int num = 1; num <= K; num++) {
        		Node cur = map.get(num);
        		int x = cur.x;
        		int y = cur.y;
        		int dir = cur.dir;
        		if (list[cur.x][cur.y].get(0) == num) { // 해당 칸에서 가장 아래에 있는 경우
        			int nx = x + dx[dir];
        			int ny = y + dy[dir];
        			
        			if (!isBoundary(nx, ny, N) || board[nx][ny] == 2) { // 범위에서 벗어나거나 파란색인 경우
        				if (dir == 1 || dir == 3) { // 방향 바꾸기
        					dir++; // gpt로 값 바꾸는거 확인하기
        				} else if (dir == 2 || dir == 4) {
        					dir--;
        				}
        				int nnx = x + dx[dir];
            			int nny = y + dy[dir];
            			
            			if (!isBoundary(nnx, nny, N) || board[nnx][nny] == 2) {
            				map.put(num, new Node(x, y, dir));
            			} else if (board[nnx][nny] == 1) { // 빨간색인 경우
            				for (int i = list[x][y].size()-1; i >= 0; i--) { // 순서 바꿔주기 + 이동
            					list[nnx][nny].add(list[x][y].get(i));
            					map.put(list[x][y].get(i), new Node(nnx, nny, map.get(list[x][y].get(i)).dir));
            				}
            				map.put(num, new Node(nnx, nny, dir)); // 해당 말 방향 바꿔주기
            				list[x][y].clear();
            				
            				if (list[nnx][nny].size() >= 4) {
            					System.out.println(T+1);
            					return;
            				}
            			} else { // 흰색인 경우
            				for (int i = 0; i < list[x][y].size(); i++) { // 이동
            					list[nnx][nny].add(list[x][y].get(i));
            					map.put(list[x][y].get(i), new Node(nnx, nny, map.get(list[x][y].get(i)).dir));
            				}
            				map.put(num, new Node(nnx, nny, dir)); // 해당 말 방향 바꿔주기
            				list[x][y].clear();
            				
            				if (list[nnx][nny].size() >= 4) {
            					System.out.println(T+1);
            					return;
            				}
            			}
        			} else if (board[nx][ny] == 1) { // 빨간색인 경우
        				for (int i = list[x][y].size()-1; i >= 0; i--) { // 순서 바꿔주기 + 이동
        					list[nx][ny].add(list[x][y].get(i));
        					map.put(list[x][y].get(i), new Node(nx, ny, map.get(list[x][y].get(i)).dir));
        				}
        				list[x][y].clear();
        				
        				if (list[nx][ny].size() >= 4) {
        					System.out.println(T+1);
        					return;
        				}
        			} else { // 흰색인 경우
        				for (int i = 0; i < list[x][y].size(); i++) { // 이동
        					list[nx][ny].add(list[x][y].get(i));
        					map.put(list[x][y].get(i), new Node(nx, ny, map.get(list[x][y].get(i)).dir));
        				}
        				list[x][y].clear();
        				
        				if (list[nx][ny].size() >= 4) {
        					System.out.println(T+1);
        					return;
        				}
        			}
        		}
//        		System.out.println(T+1);
//            	for (int m: map.keySet()) {
//            		Node node = map.get(m);
//            		System.out.println(node.x + " " + node.y + " " + node.dir);
//            	}
//            	System.out.println();
        	}
        	
        }
        System.out.println(-1);
	}
	private static boolean isBoundary(int x, int y, int N) {
		return x >= 1 && x <= N && y >= 1 && y <= N;
	}

}
