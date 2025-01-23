
import java.io.*;
import java.util.*;

public class Main {
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] pizzaA = new int[N];
        int[] pizzaB = new int[M];
        answer = 0;

        for (int i = 0; i < N; i++) {
            pizzaA[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            pizzaB[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        // 피자 A와 B의 모든 부분합 계산
        calculateSubSums(pizzaA, N, T, a);
        calculateSubSums(pizzaB, M, T, b);

        // 피자 B 정렬 후 이진 탐색
        Collections.sort(b);
        for (int sumA : a) {
            binarySearch(T - sumA, b);
        }

        System.out.println(answer);
    }

    // 부분합 계산 메서드
    private static void calculateSubSums(int[] pizza, int size, int T, List<Integer> list) {
        for (int i = 1; i <= size; i++) { // 부분합 길이
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += pizza[j];
            }
            list.add(sum); // 초기 부분합 추가

            for (int j = 1; j < size; j++) {
                sum -= pizza[j - 1];
                sum += pizza[(j + i - 1) % size];

                if (j - 1 == (j + i - 1) % size) break; // 순환 종료
                list.add(sum); // 모든 부분합 추가
            }
        }
        list.add(0); // 부분합이 없는 경우 추가
    }

    // 이진 탐색 메서드
    private static void binarySearch(int target, List<Integer> b) {
        int left = 0, right = b.size() - 1;

        // Lower bound
        while (left <= right) {
            int mid = (left + right) / 2;
            if (b.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int lowerBound = left;

        right = b.size() - 1; // 초기화

        // Upper bound
        while (left <= right) {
            int mid = (left + right) / 2;
            if (b.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int upperBound = right;

        // 범위 내의 값의 개수를 정답에 추가
        if (lowerBound <= upperBound) {
            answer += (upperBound - lowerBound + 1);
        }
    }
}
