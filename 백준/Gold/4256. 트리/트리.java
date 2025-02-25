

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
	static int[] preOrder;
	static int[] inOrder;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
        	int N = Integer.parseInt(br.readLine());
        	
        	preOrder = new int[N];
        	inOrder = new int[N];
        	
    		st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < N; i++) {
        		preOrder[i] = Integer.parseInt(st.nextToken());
        	}
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < N; i++) {
        		inOrder[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	findRoot(0, 0, N);
        	if (t != T-1) {
        		sb.append("\n");
        	}
        }
        System.out.print(sb);
	}
	private static void findRoot(int preStart, int inStart, int size) {
        if (size == 0) {
        	return; // 서브트리가 없으면 종료
        }

        int root = preOrder[preStart]; // 현재 루트 노드
        int rootIndex = inStart; // 중위 순회에서 루트 위치 찾기

        while (inOrder[rootIndex] != root) {
        	rootIndex++;
        }

        int leftSize = rootIndex - inStart; // 왼쪽 서브트리 크기
        int rightSize = size - (leftSize + 1); // 오른쪽 서브트리 크기

        // 왼쪽 서브트리 먼저 탐색
        findRoot(preStart + 1, inStart, leftSize);
        // 오른쪽 서브트리 탐색
        findRoot(preStart + leftSize + 1, rootIndex + 1, rightSize);
        // 루트 노드 출력
        sb.append(root).append(" ");
    }

}
