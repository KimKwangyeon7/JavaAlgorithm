


import java.io.*;
import java.util.*;

public class Main {
//	static class Planet implements Comparable<Planet>{
//		int size;
//		int idx;
//		
//		public Planet(int size, int idx) {
//			this.size = size;
//			this.idx = idx;
//		}
//		public int compareTo(Planet o) {
//			if (this.size == o.size) {
//				o.idx = this.idx;
//				return Integer.compare(this.idx, o.idx);
//			}
//			return Integer.compare(this.size, o.size);
//		}
//	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
	
		List<int[]> universeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			int[] universe = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int planet = Integer.parseInt(st.nextToken());
				universe[j] = planet;
			}
			int[] tmp = universe.clone();
			Arrays.sort(tmp);
			int[] sortedPlanet = new int[N];
			for (int k = 0; k < N; k++) {
				int rank = binarySearch(universe[k], tmp);
				sortedPlanet[k] = rank;
			}
			universeList.add(sortedPlanet);
		}
		
		int ans = 0;
		for (int i = 0; i < M-1; i++) {
			for (int j = i+1; j < M; j++) {
				int flag = 1;
				for (int k = 0; k < N; k++) {
					if (universeList.get(i)[k] != universeList.get(j)[k]) {
						flag = 0;
						break;
					}
				}
				if (flag == 1) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
	private static int binarySearch(int target, int[] tmp) {
		int left = 0;
		int right = tmp.length-1;
		
		while (left <= right) {
			int mid = (left+right) / 2;
			if (tmp[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}
}
