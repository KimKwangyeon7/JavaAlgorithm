import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author kwang
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] cnt = new int[4]; // 문자 개수 카운트할 배열
		int c = 0;
		int tmp = 0;
		char[] res = {'w', 'o', 'l', 'f'};
		int idx = 0;
		char[] str = br.readLine().toCharArray();
		int len = str.length;
		int flag = 1;
		if ((len % 4) != 0 || str[0] != 'w' || str[len-1] != 'f') {
			System.out.println(0);
		} else {
			for (int i = 0; i < len-1; i++) {
				if (idx == 0) { // 'w'일 경우 개수 세기
					c++;
				} else { // 다른 문자들은 정해진 개수에서 1씩 빼기
					c--;
					if (c < 0) {
						System.out.println(0);
						flag = 0;
						break;
					}
				}
				if (str[i+1] != res[idx]) { // 다음 문자가 같지 않을 때				
					if (str[i+1] == res[(idx+1)%4]) { // 순서에 맞는 문자가 왔을 때
						if (idx == 0) { // 'w' 문자 수 저장
							tmp = c;
							idx++;
							continue;
						}
						
						if (c > 0) { // 개수가 다를 때
							System.out.println(0);
							flag = 0;
							break;
						} else {
							if (idx+1 < 4) { // 다음 단어가 'w'가 아닌 경우
								c = tmp;
								idx++;
							} else { // 다음 단어가 'w'인 경우
								c = 0;
								idx = 0;
							}
						}
						
					} else { // 순서에 맞지 않는 문자가 왔을 때
						System.out.println(0);
						flag = 0;
						break;
					}
				}
			}
			if (flag == 1) {
				System.out.println(1);
			}
		}
	}

}
