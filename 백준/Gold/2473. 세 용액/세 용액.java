import java.io.*;
import java.util.*;

public class Main {
    static long[] board;
    static long comp = Long.MAX_VALUE;
    static long[] result = new long[3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            board[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(board); // **오름차순 정렬 필수**
        
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long tar = -(board[i] + board[j]);  // 목표 값
                int left = j + 1;
                int right = N - 1;
                
                // 이진 탐색을 통해 `tar`과 가장 가까운 값 찾기
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (board[mid] < tar) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                // 최적의 값을 찾기 위해 `left-1`, `left`, `left+1` 비교
                int bestIdx = left;
                if (left - 1 > j && Math.abs(board[left - 1] - tar) < Math.abs(board[bestIdx] - tar)) {
                    bestIdx = left - 1;
                }
                if (left + 1 < N && Math.abs(board[left + 1] - tar) < Math.abs(board[bestIdx] - tar)) {
                    bestIdx = left + 1;
                }

                // 결과 갱신
                long sum = Math.abs(board[i] + board[j] + board[bestIdx]);
                if (sum < comp) {
                    comp = sum;
                    result[0] = board[i];
                    result[1] = board[j];
                    result[2] = board[bestIdx];
                }
            }
        }

        Arrays.sort(result);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}
