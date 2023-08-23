import sys
from collections import deque

def finding(i, j):
    Q = deque()

    Q.append((i, j))
    visit[i][j] = 1
    cand = [(i, j)]
    sum = a[i][j]

    while Q:
        x, y = Q.popleft()

        for k in range(4):
            xx = x + dx[k]
            yy = y + dy[k]

            if 0 <= xx < n and 0 <= yy < n and visit[xx][yy] == 0:
                if L <= abs(a[x][y]-a[xx][yy]) <= R:
                    visit[xx][yy] = 1
                    Q.append((xx, yy))
                    cand.append((xx, yy))
                    sum += a[xx][yy]

    if len(cand) != 1:
        for x in cand:
            a[x[0]][x[1]] = sum // len(cand)
    return cand

 

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

n, L, R = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]
cnt = 0

while True:
    b = []
    visit = [[0]*n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if visit[i][j] == 0:
                country = finding(i, j)
                if len(country) != 1:
                    b.append(country) 
    if len(b) == 0:
        break
    cnt += 1
print(cnt)