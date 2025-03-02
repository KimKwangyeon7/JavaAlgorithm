import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 1. 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] board = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        // 2. DP 배열 초기화
        boolean[][] dp = new boolean[N+1][N+1];

        // 3. 길이 1인 구간은 항상 팰린드롬
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        // 4. 길이 2인 구간 (연속된 두 수가 같으면 팰린드롬)
        for (int i = 1; i < N; i++) {
            if (board[i] == board[i+1]) {
                dp[i][i+1] = true;
            }
        }

        // 5. 길이 3 이상인 구간의 팰린드롬 여부 판단
        for (int length = 3; length <= N; length++) {  // 길이 3부터 N까지
            for (int i = 1; i + length - 1 <= N; i++) {
                int j = i + length - 1;  // 끝점
                if (board[i] == board[j] && dp[i+1][j-1]) {
                    dp[i][j] = true;
                }
            }
        }

        // 6. 질의 처리 (O(1)로 바로 결과 반환 가능)
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(dp[S][E] ? "1\n" : "0\n");  // O(1)로 답변 가능
        }

        // 7. 출력
        System.out.print(sb);
    }
}
