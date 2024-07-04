# 문제
# 지도가 주어지면 모든 지점에 대해서 목표지점까지의 거리를 구하여라.

# 문제를 쉽게 만들기 위해 오직 가로와 세로로만 움직일 수 있다고 하자.

# 입력
# 지도의 크기 n과 m이 주어진다. n은 세로의 크기, m은 가로의 크기다.(2 ≤ n ≤ 1000, 2 ≤ m ≤ 1000)

# 다음 n개의 줄에 m개의 숫자가 주어진다. 0은 갈 수 없는 땅이고 1은 갈 수 있는 땅, 2는 목표지점이다. 입력에서 2는 단 한개이다.

# 출력
# 각 지점에서 목표지점까지의 거리를 출력한다. 원래 갈 수 없는 땅인 위치는 0을 출력하고, 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.
from collections import deque

n, m = map(int, input().split())

#2차원 배열 맵
field = [list(map(int, input().split()))  for _ in range(n) ]

#방문기록
visited = [[-1] * m for _ in range(n)] #visited를 -1로 기록함으로써 도달할 수 없는 (방문하지 않은) 곳은 -1로 둘 수 있다

#방향배열
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def bfs(x, y):
    queue = deque()
    queue.append((x, y))
    visited[x][y] = 0 #시작 지점을 0으로 두고 시작
    while queue:
        currentX, currentY = queue.popleft()
        for i in range(4):
            nextX, nextY = currentX + dx[i], currentY + dy[i]
            # 다음 길 탐색 : 범위 안에 있는지, 방문하지 않았는지, 갈 수 있는 길인지(1인지)
            if( 0<= nextX < n) and (0 <= nextY < m) and (visited[nextX][nextY] == -1 ):
                if(field[nextX][nextY] == 0):
                    #갈 수 없는 곳이라면 0으로 표시
                    visited[nextX][nextY] = 0
                else:
                    visited[nextX][nextY] = visited[currentX][currentY] +1 #원래 위치보다 +1 count 
                    queue.append((nextX, nextY)) #갈 수 있는 길만 추가

for i in range(n):
    for j in range(m):
        if(field[i][j] == 2):
            startX, startY = i, j
            break

bfs(startX, startY)

for i in range(n):
    for j in range(m):
        if (field[i][j] == 0):
            print(0, end=" ") # 원래 갈 수 없는 지역이었는지 0을 검사 -> 아예 도달하지 못한 곳이라면 0도 -1이 되어있음
        else:
            print(visited[i][j], end=" ")
    print()