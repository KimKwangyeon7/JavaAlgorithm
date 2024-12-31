

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * @author kwang
 *
 */
public class Main {
	static HashSet<Integer> set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[][] board = new char[3][3];
		while (true) {
			String str = br.readLine();
			if (str.equals("end")) {	
				break;
			}
			int countX = 0;
			int countO = 0;
			
			for (int i = 0; i < 9; i++) {
				board[i/3][i%3] = str.charAt(i);
				if (str.charAt(i) == 'X') {
					countX++;
				} else if (str.charAt(i) == 'O') {
					countO++;
				}
			}
			
			if (countX+countO == 9) {
				if (countX != 5) {
					sb.append("invalid").append("\n");
					continue;
				}
				if (check(board) == 2 || check(board) == 0) {
					sb.append("valid").append("\n");
				} else if (check(board) == 1) {
					for (int a: set) {
						if (board[a/3][a%3] == 'X') {
							sb.append("valid").append("\n");
							break;
						} else {
							sb.append("invalid").append("\n");
							break;
						}
					}
				} else {
					sb.append("invalid").append("\n");
				}
				continue;
			} else {
				if (countX == countO) {
					int res = check(board);
					if (res == 1) {
						for (int a: set) {
							if (board[a/3][a%3] == 'O') {
								sb.append("valid").append("\n");
								break;
							} else {
								sb.append("invalid").append("\n");
								break;
							}
						}
						continue;
					} else {
						sb.append("invalid").append("\n");
						continue;
					}
				} else if (countX == countO+1) {
					int res = check(board);
					if (res == 1) {
						for (int a: set) {
							if (board[a/3][a%3] == 'X') {
								sb.append("valid").append("\n");
								break;
							} else {
								sb.append("invalid").append("\n");
								break;
							}
						}
						continue;
					} else {
						sb.append("invalid").append("\n");
						continue;
					}
				} else {
					sb.append("invalid").append("\n");
					continue;
				}
			}
		}
		System.out.println(sb);
	}
	private static int check(char[][] board) {
		set = new HashSet<>();
		int cnt = 0;
		// 가로 세로 체크
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != '.' && (board[i][0] == board[i][1]) && (board[i][1] == board[i][2])) {
				cnt++;
				if (set.contains(i*3)) {
					int num = i*3;
					if (board[num/3][num%3] == 'X') {
						return cnt;
					} else {
						return -1;
					}
				} else if (set.contains(i*3+1)) {
					int num = i*3+1;
					if (board[num/3][num%3] == 'X') {
						return cnt;
					} else {
						return -1;
					}
				} else if (set.contains(i*3+2)) {
					int num = i*3+2;
					if (board[num/3][num%3] == 'X') {
						return cnt;
					} else {
						return -1;
					}
				} else {
					set.add(i*3);
					set.add(i*3+1);
					set.add(i*3+2);
				}
			}
			
			if (board[0][i] != '.' && (board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) {
				cnt++;
				if (set.contains(i)) {
					int num = i;
					if (board[num/3][num%3] == 'X') {
						return cnt;
					} else {
						return -1;
					}
				} else if (set.contains(i+3)) {
					int num = i+3;
					if (board[num/3][num%3] == 'X') {
						return cnt;
					} else {
						return -1;
					}
				} else if (set.contains(i+6)) {
					int num = i+6;
					if (board[num/3][num%3] == 'X') {
						return cnt;
					} else {
						return -1;
					}
				} else {
					set.add(i);
					set.add(i+3);
					set.add(i+6);
				}
			}
		}
		
		// 대각 체크
		if (board[0][0] != '.' && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) {
			cnt++;
			if (set.contains(0)) {
				int num = 0;
				if (board[num/3][num%3] == 'X') {
					return cnt;
				} else {
					return -1;
				}
			} else if (set.contains(4)) {
				int num = 4;
				if (board[num/3][num%3] == 'X') {
					return cnt;
				} else {
					return -1;
				}
			} else if (set.contains(8)) {
				int num = 8;
				if (board[num/3][num%3] == 'X') {
					return cnt;
				} else {
					return -1;
				}
			} else {
				set.add(0);
				set.add(4);
				set.add(8);
			}
		}
		if (board[0][2] != '.' && (board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) {
			cnt++;
			if (set.contains(2)) {
				int num = 2;
				if (board[num/3][num%3] == 'X') {
					return cnt;
				} else {
					return -1;
				}
			} else if (set.contains(4)) {
				int num = 4;
				if (board[num/3][num%3] == 'X') {
					return cnt;
				} else {
					return -1;
				}
			} else if (set.contains(6)) {
				int num = 6;
				if (board[num/3][num%3] == 'X') {
					return cnt;
				} else {
					return -1;
				}
			} else {
				set.add(2);
				set.add(4);
				set.add(6);
			}
		}
		
		if (cnt > 1) {
			return -1;
		}
		return cnt;
	}
}
