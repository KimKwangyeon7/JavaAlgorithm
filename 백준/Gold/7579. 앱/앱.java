
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
	static class Node implements Comparable<Node>{
		int mem;
		int cost;
		
		public Node(int mem, int cost) {
			this.mem = mem;
			this.cost = cost;
		}
		public int compareTo(Node o) {
			if (this.cost == o.cost) {
				return Integer.compare(o.mem, this.mem);
			}
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] memory = new int[N];  
        int[] cost = new int[N]; 

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int totalCost = 0;  
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];  
        }

        // 2. DP 배열 초기화 (최대 비용 범위)
        int[] dp = new int[totalCost + 1];  

        // 3. DP 진행 (0/1 Knapsack DP)
        for (int i = 0; i < N; i++) {
            for (int j = totalCost; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
            }
        }

        // 4. 최소 비용 찾기
        for (int j = 0; j <= totalCost; j++) {
            if (dp[j] >= M) {
                System.out.println(j);  
                break;
            }
        }
        
	}

}
