

/**
 * @author kwang
 *
 */

import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] board = new int[N];
		for (int i = 0; i < N; i++) {
			board[i] = Integer.parseInt(st.nextToken());
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int tmp = binarySearch(board[i]*(-1), board, i);
			if (Math.abs(ans) > Math.abs(tmp)) {
				ans = tmp;
			}
		}
		System.out.println(ans);
		
	}

	private static int binarySearch(int target, int[] board, int idx) {
		int left = 0;
		int right = board.length-1;
		int N = board.length-1;
		Set<Integer> set = new HashSet<>();
		while (left <= right) {
			int mid = (left+right) / 2;
			if (board[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		int lowerBound = left;
		if (lowerBound >= 0 && lowerBound < N) {
			set.add(lowerBound);
		}
		if (lowerBound+1 >= 0 && lowerBound+1 < N) {
			set.add(lowerBound+1);
		}
		if (lowerBound-1 >= 0 && lowerBound-1 < N) {
			set.add(lowerBound-1);
		}
		//System.out.println("lower " + target + " " + lowerBound);
		
		while (left <= right) {
			int mid = (left+right) / 2;
			if (board[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		int upperBound = right;
		if (upperBound >= 0 && upperBound < N) {
			set.add(upperBound);
		}
		if (upperBound+1 >= 0 && upperBound+1 < N) {
			set.add(upperBound+1);
		}
		if (upperBound-1 >= 0 && upperBound-1 < N) {
			set.add(upperBound-1);
		}
		//System.out.println("upper " + target + " " + upperBound);
		int ans = Integer.MAX_VALUE;
		set.remove(idx);
		for (int s: set) {
			int hap = board[s] + board[idx];
			if (Math.abs(ans) > Math.abs(hap)) {
				ans = hap;
			}
		}
		
		return ans;
	}

}
