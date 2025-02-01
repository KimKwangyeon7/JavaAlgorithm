import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] A = new long[N];
        long[] B = new long[N];
        long[] C = new long[N];
        long[] D = new long[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        // 부분합 배열 만들기
        long[] cand1 = new long[N * N];
        long[] cand2 = new long[N * N];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cand1[idx] = A[i] + B[j];
                cand2[idx] = C[i] + D[j];
                idx++;
            }
        }

        // 정렬 (이진 탐색을 위해)
        Arrays.sort(cand1);
        Arrays.sort(cand2);

        long ans = 0L;
        int right = cand2.length - 1;

        // cand1을 순회하며 cand2에서 -cand1을 찾음
        for (long c : cand1) {
            long target = -c;
            int left = 0, tmp = right;

            // Lower Bound 찾기
            while (left <= right) {
                int mid = (left + right) / 2;
                if (cand2[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            int lowerBound = left;

            left = 0;
            right = tmp; // right를 유지하여 탐색 범위 줄이기

            // Upper Bound 찾기
            while (left <= right) {
                int mid = (left + right) / 2;
                if (cand2[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            int upperBound = right;

            // 값이 존재하는 경우 정답에 추가
            if (lowerBound <= upperBound) {
                ans += (upperBound - lowerBound + 1);
            }

            // 탐색 범위를 줄이기 위해 upperBound 갱신
            if (upperBound < 0) break;
            else right = upperBound;
        }

        System.out.println(ans);
    }
}
