

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	static long[] board;
    static long comp = Long.MAX_VALUE;
    static long[] result = new long[3];
    static StringBuilder sb =  new StringBuilder();
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
                    print(i, j, bestIdx);
                }
            }
        }

//        Arrays.sort(result);
//        System.out.println(result[0] + " " + result[1] + " " + result[2]);
        System.out.println(sb);
    }
	private static void print(int x, int y, int z) {
		sb = new StringBuilder();
		long[] tmp = new long[3];
		tmp[0] = board[x];
		tmp[1] = board[y];
		tmp[2] = board[z];
		Arrays.sort(tmp);
		sb.append(tmp[0]).append(" ").append(tmp[1]).append(" ").append(tmp[2]);
	}
//	private static void combi(int L, int start, int N) {
//		if (L == 3) {
//			long sum = 0;
//			for (int r: res) {
//				sum += r;
//				//System.out.print(r + " ");
//			}
//			//System.out.println();
//			if (Math.abs(sum) < Math.abs(val)) {
//				val = sum;
//				
//				// res 배열의 복사본을 만들어 정렬
//		        int[] tmp = res.clone();
//		        Arrays.sort(tmp);
//		        
//				sb = new StringBuilder();
//				for (int r: tmp) {
//					sb.append(r).append(" ");
//				}
//			}
//			return;
//		}
//		for (int i = start; i < N; i++) {
//			res[L] = board[i];
//			combi(L+1, i+1, N);
//		}
//	}

}
