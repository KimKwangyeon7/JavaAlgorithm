
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	
	static class Nation implements Comparable<Nation>{
		int nation; // 국가 번호
		int gold; // 금메달 수
		int silver; // 은메달 수
		int bronze; // 동메달 수
		int same; // 동순위
		
		public Nation(int nation, int gold, int silver, int bronze, int same) {
			this.nation = nation;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
			this.same = same;
		}
		
		@Override
		public int compareTo(Nation o) {
			if (this.gold == o.gold) {
				if (this.silver == o.silver) {
					if (this.bronze == o.bronze) {
						this.same = 1;
						o.same = 1;
					}
					return Integer.compare(o.bronze, this.bronze);
				}
				return Integer.compare(o.silver, this.silver);
			}
			return Integer.compare(o.gold, this.gold);
		}	
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 국가의 수
		int K = Integer.parseInt(st.nextToken()); // 등수를 알고 싶은 국가
		
		PriorityQueue<Nation> pq = new PriorityQueue<>();
		//int[][] board = new int[N][3]; // 국가별 금, 은, 동 수
		int nation = 0;
		int g = 0;
		int s = 0;
		int b = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nation = Integer.parseInt(st.nextToken()); // 국가 번호
			g = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			pq.offer(new Nation(nation, g, s, b, 0));
		}
		
		int rank = 1;
		int eq = 1;
		Nation past = pq.poll();
		if (past.nation == K) { // 등수를 알아낼 국가면
			System.out.println(rank);
		} else {
			while (!pq.isEmpty()) {
				Nation cur = pq.poll();

				if (past.gold == cur.gold && past.silver == cur.silver && past.bronze == cur.bronze) {
					eq++;
				} else {
					if (eq == 0) {
						rank++;
					} else {
						rank += eq;
						eq = 1;
					}
				}	
				
				//System.out.println(cur.nation + " " + cur.same + " " + eq + " " + rank);
				if (cur.nation == K) { // 등수를 알아낼 국가면
					System.out.println(rank);
					break;
				}
				past = cur;
			}
		}
	}

}
