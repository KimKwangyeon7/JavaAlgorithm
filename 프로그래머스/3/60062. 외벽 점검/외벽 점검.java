import java.util.*;
class Solution {
    static int num;
    static int min = -1;
    static boolean[] visit;
    static int N;

    public int solution(int n, int[] weak, int[] dist) {
		N = n;
		num = weak.length; // 취약 지점 갯수
		visit = new boolean[num];

		// 오름차순 정렬
		Arrays.sort(dist);

		DFS(dist.length - 1, 0, 1, weak, dist);
		return min;
	}

	// 시작 인덱스, 점검 완료 갯수, 보낸 친구 수
	static void DFS(int start, int cnt, int f, int[] weak, int[] dist) {
		if(cnt == num) { // 취약 정점 갯수만큼 점검했다면
			f--;
			min = min == -1 ? f : Math.min(f, min);
			return;
		}

		// visit를 되돌리기 위해 방문한 인덱스 저장
		ArrayList<Integer> back = new ArrayList<>();
		
		if(start < 0)
			return;
		
		for(int i=0;i<num;i++) {
			if(visit[i])
				continue;
			
			back.add(i);
			visit[i] = true;
			cnt++;
			
			int dis = dist[start]; // 친구가 이동할 수 있는 거리
			int temp = 1; // cnt를 되돌리기 위해 cnt가 얼마나 증가했는지 저장
			int idx = i;
			while(dis > 0 && cnt < num) { // 친구가 이동할 수 없을 때까지
				if(visit[(idx + 1) % num]) 
					break;
				
				int disDiff = weak[(idx + 1) % num] - weak[idx];
				if(disDiff < 0)
					disDiff += N;

				dis -= disDiff;

				if(dis >= 0) {
					temp++;
					cnt++;
					
					idx = (idx + 1) % num;
					back.add(idx);
					visit[idx] = true;
				}
			}

			DFS(start - 1, cnt, f + 1, weak, dist);
			cnt -= temp;
			for(int b : back) 
				visit[b] = false;
			
			back.clear();
		}
	}
}