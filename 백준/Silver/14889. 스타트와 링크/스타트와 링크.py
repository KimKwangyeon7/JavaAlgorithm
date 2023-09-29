import sys

n = int(input())
s = [list(map(int, input().split())) for _ in range(n)]
cha = 1e9
vis = [0] * n

def DFS(L, cnt):
    global cha
    if L == n//2:
        ssum = 0
        lsum = 0
        
        for p in range(n):
            for q in range(n):
                if vis[p] == 1 and vis[q] == 1:
                    ssum += s[p][q]
                if vis[p] == 0 and vis[q] == 0:
                    lsum += s[p][q]
        res = abs(ssum - lsum)
        cha = min(res, cha)
    else:
        for i in range(cnt, n):
            if vis[i] == 0:
                vis[i] = 1
                DFS(L+1, i+1)
                vis[i] = 0

DFS(0, 0)
print(cha)