

import java.io.*;
import java.util.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] board = new int[N];

        for (int i = 0; i < N; i++) {
            board[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(board);

        Set<Integer> twoSumSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                twoSumSet.add(board[i] + board[j]); // 모든 가능한 (x + y)를 저장
            }
        }

        for (int k = N - 1; k >= 0; k--) { // k를 큰 값부터 확인
            for (int i = 0; i < k; i++) {
                int target = board[k] - board[i];
                if (twoSumSet.contains(target)) { // O(1) 탐색
                    System.out.println(board[k]);
                    return;
                }
            }
        }
    }
}
