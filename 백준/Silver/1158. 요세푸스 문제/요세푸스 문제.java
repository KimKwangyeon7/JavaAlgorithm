import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
	/**
	 * 순환 
	 * 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		Deque<Integer> ad = new ArrayDeque<>(); // 숫자를 다룰 arraydeque 자료구조
		
		sb.append("<");
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		for (int i = 1; i <= N; i++) { // 1부터 N까지 숫자를 어레이 데크에 저장
			ad.add(i);
		}
		while (!ad.isEmpty()) {
			for (int i = 0; i < K-1; i++) { // K-1번 앞의 원소들을 뒤로 
				ad.offerLast(ad.pollFirst()); 
			}
			sb.append(ad.removeFirst()); // K번째 원소는 삭제
			if (ad.size() > 0) {
				sb.append(", ");
			}
		}
		sb.append(">");
		System.out.println(sb);
	}
}
