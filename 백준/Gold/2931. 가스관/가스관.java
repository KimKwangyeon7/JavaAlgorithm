import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, ex, ey;
	static char[][] board;
	static int[][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R+2][C+2];
		// 출발지 좌표 (M)
		int sx = 0;
		int sy = 0;
		// 도착지 좌표(Z)
		ex = 0;
		ey = 0;
		visit = new int[R+2][C+2];
		for (int i = 1; i <= R; i++) {
			String tmp = br.readLine();
			for (int j = 1; j <= C; j++) {
				board[i][j] = tmp.charAt(j-1);
				if (board[i][j] == 'M') {
					sx = i;
					sy = j;
				} else if(board[i][j] == 'Z') {
					ex = i;
					ey = j;
				} else if (board[i][j] == '|') {
					visit[i][j] += (1<<0);
					visit[i][j] += (1<<2);
				} else if (board[i][j] == '-') {
					visit[i][j] += (1<<1);
					visit[i][j] += (1<<3);
				} else if (board[i][j] == '+') {
					visit[i][j] += (1<<0);
					visit[i][j] += (1<<1);
					visit[i][j] += (1<<2);
					visit[i][j] += (1<<3);
				} else if (board[i][j] == '1') {
					visit[i][j] += (1<<1);
					visit[i][j] += (1<<2);
				} else if (board[i][j] == '2') {
					visit[i][j] += (1<<0);
					visit[i][j] += (1<<1);
				} else if (board[i][j] == '3') {
					visit[i][j] += (1<<0);
					visit[i][j] += (1<<3);
				} else if (board[i][j] == '4') {
					visit[i][j] += (1<<3);
					visit[i][j] += (1<<2);
				}
			}
		}
		
		
	
		if (((visit[sx-1][sy] >> 2) & 1) == 1) { // 위
			visit[sx][sy] += (1<<0);
		} 
		if (((visit[sx][sy+1] >> 3) & 1) == 1) { // 우
			visit[sx][sy] += (1<<1);
		}
		if (((visit[sx+1][sy] >> 0) & 1) == 1) { // 아래
			visit[sx][sy] += (1<<2);
		}
		if (((visit[sx][sy-1] >> 1) & 1) == 1) { // 좌
			visit[sx][sy] += (1<<3);
		}
	
	

		if (((visit[ex-1][ey] >> 2) & 1) == 1) { // 위
			visit[ex][ey] += (1<<0);
		}
		if (((visit[ex][ey+1] >> 3) & 1) == 1) { // 우
			visit[ex][ey] += (1<<1);
		}
		if (((visit[ex+1][ey] >> 0) & 1) == 1) { // 아래
			visit[ex][ey] += (1<<2);
		}
		if (((visit[ex][ey-1] >> 1) & 1) == 1) { // 좌
			visit[ex][ey] += (1<<3);
		}
		
		int ax = 0;
		int ay = 0;
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (visit[i][j] != 0) {
					if (((visit[i][j] >> 0) & 1) != ((visit[i-1][j] >> 2) & 1) && board[i-1][j] == '.'){
						sb.append(i-1).append(" ").append(j).append(" ");
						i--;
						ax = i;
						ay = j;
						i = R+1;
						break;
					}
					if (((visit[i][j] >> 1) & 1) != ((visit[i][j+1] >> 3) & 1) && board[i][j+1] == '.'){
						sb.append(i).append(" ").append(j+1).append(" ");
						j++;
						ax = i;
						ay = j;
						i = R+1;
						break;
					}
					if (((visit[i][j] >> 2) & 1) != ((visit[i+1][j] >> 0) & 1) && board[i+1][j] == '.'){
						sb.append(i+1).append(" ").append(j).append(" ");
						i++;
						ax = i;
						ay = j;
						i = R+1;
						break;
					}
					if (((visit[i][j] >> 3) & 1) != ((visit[i][j-1] >> 1) & 1) && board[i][j-1] == '.'){
						sb.append(i).append(" ").append(j-1).append(" ");
						j--;
						ax = i;
						ay = j;
						i = R+1;
						break;
					}
				}
			}
		}
		
		if (((visit[ax-1][ay] >> 2) & 1) == 1 && ((visit[ax][ay+1] >> 3) & 1) == 1 && ((visit[ax+1][ay] >> 0) & 1) == 1 && ((visit[ax][ay-1] >> 1) & 1) == 1) { // 십자
			sb.append("+").append("\n");
		}
		else if (((visit[ax][ay-1] >> 1) & 1) == 1 && ((visit[ax][ay+1] >> 3) & 1) == 1) { // 좌우
			sb.append("-").append("\n");
		}
		else if (((visit[ax-1][ay] >> 2) & 1) == 1 && ((visit[ax+1][ay] >> 0) & 1) == 1) { // 위 아래
			sb.append("|").append("\n");
		}
		else if (((visit[ax][ay+1] >> 3) & 1) == 1 && ((visit[ax+1][ay] >> 0) & 1) == 1) { // 우 아래
			sb.append(1).append("\n");
		}
		else if (((visit[ax][ay+1] >> 3) & 1) == 1 && ((visit[ax-1][ay] >> 2) & 1) == 1) { // 우 위
			sb.append(2).append("\n");
		}
		else if (((visit[ax][ay-1] >> 1) & 1) == 1 && ((visit[ax-1][ay] >> 2) & 1) == 1) { // 좌 위
			sb.append(3).append("\n");
		}
		else if (((visit[ax][ay-1] >> 1) & 1) == 1 && ((visit[ax+1][ay] >> 0) & 1) == 1) { // 좌 아래
			sb.append(4).append("\n");
		}
		System.out.println(sb);	
	}

}

