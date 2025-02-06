import java.io.*;
import java.util.*;

public class Main {
	
	static class Planet implements Comparable<Planet>{
		int value;
		int idx;
		public Planet(int value,int idx) {
			this.value = value;
			this.idx = idx;
		}
		
		public int compareTo(Planet o) {
			return Integer.compare(this.value, o.value);
		}
		public String StringTo() {
			return this.value+" "+this.idx;
		}
	}
	
	static int[][] indexs;
	static int M;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indexs = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int[] space = new int[M];
			for(int j=0;j<M;j++) {
				int n = Integer.parseInt(st.nextToken());
				space[j] = n;
			}
			
			for(int j=0;j<M;j++) {
				indexs[i][j] = Arrays.binarySearch(space, space[j]);
			}

		}
		int ans = 0;
		for(int i=0;i<N-1;i++) {
			for(int j=i+1;j<N;j++) {
				boolean flag = true;
				for(int k=0;k<M;k++) {
					if(indexs[i][k] != indexs[j][k]) {
						flag = false;
						break;
					}
				}
				if(flag)ans++;
			}
		}
		System.out.println(ans);
		
	}
	




}