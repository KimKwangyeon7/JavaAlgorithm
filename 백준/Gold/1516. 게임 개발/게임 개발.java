/**
 * 
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *
 */
public class Main {

    /**
     * @param args
     */
    static int[] check;
    static List<Integer>[] list;
    static int[] sum, time;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        list = new ArrayList[N];
        for (int i =- 0; i < N; i++) {
            list[i] = new ArrayList<Integer>();
        }
        sum = new int[N];
        time = new int[N];
        check = new int[N];
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            sum[i] = time[i];
            while (true) {
                tmp = Integer.parseInt(st.nextToken());
                if (tmp == -1) {
                    break;
                } else {
                    list[tmp-1].add(i);
                    check[i]++;
                }
            }
        }
        
        bfs();
        for (int i: sum) {
        	System.out.println(i);
        }

    }
    static void bfs() {
        Queue<Integer> qu = new ArrayDeque<>();
        for (int x = 0; x < check.length; x++) {
            if (check[x] == 0) {
                qu.offer(x);
            }
        }
        
        while (!qu.isEmpty()) {
        	int tmp = qu.poll();
        	for (int x: list[tmp]) {
        		sum[x] = Math.max(sum[x], sum[tmp]+time[x]);
        		check[x]--;
        		
        		if (check[x] == 0) {
        			qu.offer(x);
        		}
        	}
        }     
    }
}
