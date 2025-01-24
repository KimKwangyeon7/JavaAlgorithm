

/**
 * @author kwang
 *
 */
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		int[] b = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		List<Long> candA = new ArrayList<>();
		List<Long> candB = new ArrayList<>();
		
		getSub(N, a, candA);
		getSub(M, b, candB);
		
		Collections.sort(candB);
//		for (int aa: candA) {
//			System.out.print(aa + " ");
//		}
//		System.out.println();
//		for (int bb: candB) {
//			System.out.print(bb + " ");
//		}
//		System.out.println();
		
		long answer = 0;
		for (long tmp: candA) {
			answer += binarySearch(T, tmp, candB);
		}
		System.out.println(answer);
	}

	// 이진 탐색 메서드
    private static long binarySearch(int t, long tar, List<Long> b) {
        int left = 0, right = b.size() - 1;
        long target = t - tar;
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
        long answer = 0;
//        System.out.println(t + " " + tar + " ");
//        System.out.println(lowerBound + " ");        
//        System.out.println(upperBound + " ");
//        System.out.println();
        // 범위 내의 값의 개수를 정답에 추가
        if (lowerBound <= upperBound) {
            answer += (upperBound - lowerBound + 1);
        }
        return answer;
    }

	private static void getSub(int L, int[] arr, List<Long> cand) {
		int size = arr.length;
		for (int i = 1; i <= size; i++) { // 개수
			long sum = 0;
			// 첫 부분집합 구하기
			for (int j = 0; j < i; j++) {
				sum += arr[j];
			}
			cand.add(sum);
			for (int j = 1; j <= size-i; j++) {
				sum -= arr[j-1];
				sum += arr[j+i-1];
				cand.add(sum);
			}
		}
	}

}
