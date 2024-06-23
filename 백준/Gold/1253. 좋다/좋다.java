
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    int N = Integer.parseInt(br.readLine());
	    int[] num = new int[N];
	    int idx = 0;
	    int res = 0;

	    st = new StringTokenizer(br.readLine());
	    for (int i = 0; i < N; ++i) {
	      num[i] = Integer.parseInt(st.nextToken());
	    }

	    Arrays.sort(num); // 이분탐색을 위해 우선 정렬
	    for (int i = 0; i < N; ++i) {
	      int tar = num[i]; // 목표
	      int left = 0; // 왼쪽 포인터
	      int right = N - 1; // 오른쪽 포인터
	      int sum = 0; // 합

	      while (left < right) {
	        sum = num[left] + num[right]; // 왼쪽 포인터와 오른쪽 포인터의 합
	        if (sum == tar) { // 합이 목표 수와 같으면
	          if (i == left) { // 찾는 수와 왼쪽 포인터 인덱스와 같으면
	            left++;
	          } else if (right == i) { // 찾는 수와 오른쪽 포인터 인덱스와 같으면
	            right--;
	          } else { // 인덱스 다 다르면 좋은 수로 카운트
	            res++;
	            break;
	          }
	        }

	        if (num[left] + num[right] > tar) { // 목표보다 합이 더 크면
	          right--; // 크기를 줄여야하므로 오른쪽 포인터 감소
	        } else if (num[left] + num[right] < tar) { // 목표보다 합이 더 작으면
	          left++; // 더 크게 하기 위해 왼쪽 포인터 증가
	        }
	      }
	    }
	    System.out.println(res);
	}

}
