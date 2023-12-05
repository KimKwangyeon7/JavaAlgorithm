import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int n, int k) {
        List<Integer> list = new ArrayList<>();
		
		while (n > 0) {
			list.add(n%k);
			n = n / k;
		}
		String tmp = "";
		for (int i = list.size()-1; i >= 0; i--) {
			tmp += list.get(i);
		}
		String[] primes = tmp.split("0"); // 0을 구분자로 숫자 나눔
		int cnt = 0;
		for (int i = 0; i < primes.length; i++) {
			if (primes[i].equals("")) {
				continue;
			}
			if (isPrime(Long.parseLong(primes[i]))) {
				cnt++;
			}
		}
		return cnt;
    }
    
    static boolean isPrime(long x) { // 소수 판별 메서드
		if (x < 2) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(x); i++) {
			if ((x % i) == 0) {
				return false;
			}
		}
		return true;
	}
}