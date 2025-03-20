import java.util.*;

class Solution {
    static int maxSheep;
    static List<Integer>[] tree;
    static int[] nodeInfo;

    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        nodeInfo = info;
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }

        maxSheep = 0;

        // DFS 탐색 (현재 노드, 양 개수, 늑대 개수, 방문 가능한 노드 리스트)
        List<Integer> possibleNodes = new ArrayList<>();
        possibleNodes.add(0);  // 루트 노드부터 시작
        dfs(0, 1, 0, possibleNodes);

        return maxSheep;
    }

    private void dfs(int current, int sheep, int wolf, List<Integer> possibleNodes) {
        // 최대 양 개수 갱신
        maxSheep = Math.max(maxSheep, sheep);

        // 현재 노드가 늑대라면 추가
        if (nodeInfo[current] == 1) {
            wolf++;
        }

        // 늑대 개수가 양보다 많으면 탐색 종료
        if (wolf >= sheep) {
            return;
        }

        // 방문 가능한 노드 리스트에서 현재 노드 제외
        List<Integer> nextNodes = new ArrayList<>(possibleNodes);
        nextNodes.remove(Integer.valueOf(current));
        
        // 현재 노드의 자식 노드 추가
        nextNodes.addAll(tree[current]);

        // 가능한 모든 다음 노드 탐색 (백트래킹)
        for (int next : nextNodes) {
            dfs(next, sheep + (nodeInfo[next] == 0 ? 1 : 0), wolf, nextNodes);
        }
    }
}
