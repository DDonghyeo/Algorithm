# 문제
# 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

# 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

# 입력
# 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

# 출력
# 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
from collections import deque
MAX_NUM = 100000
n, k = map(int, input().split())
distance = [0] * (MAX_NUM + 1)

def bfs(v):
    queue = deque()
    queue.append(v)
    while queue:
        current = queue.popleft()
        if current == k: #도달했다면
            print(distance[current]) #총 걸린 시간 출력하고 종료
            break
        for nextn in (current-1, current+1, current*2): #-1, +1, *2 모두 행해봄
            print("next : ", nextn)
            if (0 <= nextn <= MAX_NUM) and (not distance[nextn]): #범위 안에 있고 방문하지 않았다면
                distance[nextn] = distance[current] + 1
                queue.append(nextn)

bfs(n)



