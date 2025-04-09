
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static class Box implements Comparable<Box>{
		int from;
		int to;
		int cnt;
		
		public Box(int from, int to, int cnt) {
			this.from = from;
			this.to = to;
			this.cnt = cnt;
		}
		
		public int compareTo(Box o1) {
			if (o1.to == this.to) {
				return Integer.compare(this.from, o1.from);
			}
			return Integer.compare(this.to, o1.to);
		}
	}
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        List<Box> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int box = Integer.parseInt(st.nextToken());
        	list.add(new Box(from, to, box));
        }
        int answer = 0;
        // i번째 마을에 도착할때 갖고 있는 택배 상자의 총 개수
        int[] visited = new int[N+1];
        Collections.sort(list);
        
        for (Box box: list) {
        	int max = 0;
        	for (int i = box.from; i < box.to; i++) {
        		max = Math.max(max, visited[i]);
        	}
        	
        	int avail = Math.min(box.cnt, C - max);
        	
        	if (avail > 0) {
        		for (int i = box.from; i < box.to; i++) {
                    visited[i] += avail;
                }
                answer += avail;
        	}
        }
    
        
        System.out.println(answer);
	}

}
