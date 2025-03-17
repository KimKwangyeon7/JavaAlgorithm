import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        // 최대 힙 (중간값 이하의 값들을 저장)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // 최소 힙 (중간값 이상의 값들을 저장)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            // 1. 항상 maxHeap에 먼저 삽입
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            // 2. maxHeap의 최대값이 minHeap의 최소값보다 크다면 swap
            if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int tempMax = maxHeap.poll();
                int tempMin = minHeap.poll();
                maxHeap.add(tempMin);
                minHeap.add(tempMax);
            }

            // 3. 중간값 출력 (maxHeap의 루트)
            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.print(sb);
    }
}
