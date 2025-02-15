

import java.io.*;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수

        List<int[]> jewels = new ArrayList<>();
        int[] bags = new int[K];

        // 보석 정보 입력 (무게, 가격)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels.add(new int[]{m, v});
        }

        // 가방 정보 입력 (무게 한도)
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 보석을 무게 기준 오름차순 정렬
        jewels.sort(Comparator.comparingInt(a -> a[0]));

        // 가방을 용량 기준 오름차순 정렬
        Arrays.sort(bags);

        // TreeMap을 사용하여 가격을 관리 (가격 기준 내림차순)
        TreeMap<Integer, Integer> valueMap = new TreeMap<>(Collections.reverseOrder());

        long maxProfit = 0;
        int jewelIndex = 0;

        // 가방을 하나씩 처리하면서, 현재 가방에 넣을 수 있는 보석을 관리
        for (int capacity : bags) {
            // 현재 가방에 들어갈 수 있는 보석을 TreeMap에 추가
            while (jewelIndex < N && jewels.get(jewelIndex)[0] <= capacity) {
                int value = jewels.get(jewelIndex)[1];
                valueMap.put(value, valueMap.getOrDefault(value, 0) + 1);
                jewelIndex++;
            }

            // TreeMap에서 가장 비싼 보석을 선택
            if (!valueMap.isEmpty()) {
                int maxValue = valueMap.firstKey();
                maxProfit += maxValue;

                // 해당 가격의 보석 개수를 하나 줄인다.
                if (valueMap.get(maxValue) == 1) {
                    valueMap.remove(maxValue);
                } else {
                    valueMap.put(maxValue, valueMap.get(maxValue) - 1);
                }
            }
        }

        System.out.println(maxProfit);
    }
}
