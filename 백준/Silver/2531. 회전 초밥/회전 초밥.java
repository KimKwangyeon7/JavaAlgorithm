
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		//입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        StringTokenizer st = new StringTokenizer(br.readLine());
        //N, d, k, c의 입력값 저장
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] eating = new int[d+1];	//먹은 초밥 종류 관련 배열
        eating[c] = 3001;	//무료 초밥 종류 
        int[] sushi = new int[N];
        int count = 1;	//무료 초밥이 1개 있으므로 default Value = 1
        //회전 벨트 정보 저장
        for (int i = 0; i < N; i++)
            sushi[i] = Integer.parseInt(br.readLine());
        //회전하지 않았을 때 초밥 종류 구하기
        for (int i = 0;i < k; i++){
            int sushiId = sushi[i];
            if(eating[sushiId]==0)
                count++;
            eating[sushiId]++;
        }
        int max = count;
        //(N-1)번 회전을 투 포인터를 이용하여 탐색
        for (int i = 0; i < N-1;i++){
            int s_id = sushi[i];	//처음 먹은 초밥 종류
            int e_id = sushi[i+k<N ? i+k : (i+k) % N];	//마지막 + 1번째 먹을 초밥 종류
            if(--eating[s_id] == 0) { // 처음 먹은 초밥 종류 더 이상 없을 때
                count--;
            }
            if(++eating[e_id] == 1) { // 마지막 + 1번째 먹을 초밥이 처음 먹는 것일 때
                count++;
            }
            max = Math.max(max, count);	// 최대값 비교
        }
        System.out.println(max);
	}
}
