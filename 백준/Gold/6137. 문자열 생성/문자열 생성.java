import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 문자열의 길이
		char[] arr = new char[N]; // 문자열을 담을 char 배열
		for (int i = 0; i < N; i++) { // char배열에 주어진 문자열 담기
			arr[i] = br.readLine().charAt(0); // String으로 받고 첫번째 char를 저장
		}
		
		int start = 0; // 0에서 시작하는 포인터
		int end = N-1; // N-1에서 시작하는 포인터
		int si = 0; // 중간에 임시로 왼쪽 포인터 값을 저장할 변수
		int ei = 0; // 중간에 임시로 오른쪽 포인터 값을 저장할 변수
		int cnt = 0;
		while (start <= end) {
			if (arr[start] < arr[end]) { // 왼쪽 포인터에 있는 문자가 오른쪽 포인터에 있는 문자보다 먼저 오는 경우 
				sb.append(arr[start++]); // 출력양식에 추가하고 왼쪽 포인터 1 증가
				cnt++; // 개수도 1 증가
			} else if (arr[start] > arr[end]) { // 오른쪽 포인터가 더 먼저 오는 경우
				sb.append(arr[end--]); // 출력양식에 추가하고 오른쪽 포인터 1 감소
				cnt++; // 개수 1 증가
			} else { // 두 문자가 같은 경우
				si = start;
				ei = end;
				int flag = 0;
				while (si <= ei) {
					if (arr[si] < arr[ei]) {
						sb.append(arr[start++]);
                        cnt++;
						flag = 1;
						break;
					} else if (arr[si] > arr[ei]) {
						sb.append(arr[end--]);
                        cnt++;
						flag = 1;
						break;
					} else { // 두 문자가 같으면 +1, -1 해주기
						si++;
						ei--;
					}
				}
				if (flag == 0) {
					sb.append(arr[start++]);
					cnt++;
				}				
			}
			if ((cnt%80) == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
		
	}

}
