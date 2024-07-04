#Knapsack Problem
n, W = map(int, input().split())

wt = []
val = []

for _ in range(n):
    w, v = map(int, input().split())
    wt.append(w)
    val.append(v)

def knapsack(W, wt, val, n):

    # DP 테이블 : 각 물건마다 그 용량에 최대로 가질 수 있는 가치를 구해나감
    K = [[0 for _ in range(W + 1)] for _ in range(n + 1)]
    
    for i in range(n + 1):
        for w in range(W + 1):
            # i : 물건 번호
            # w : 현재 용량
            if i == 0 or w == 0:
                K[i][w] = 0
            #만약 i번째 물건의 무게가 현재 최대 용량 보다 작은경우
            elif wt[i-1] <= w:
                # 비교 : i번째 물건의 가치 + i 번째 물건을 담고 남은 용량 중 i-1번째에서 최대로 가질 수 있는 가치 vs i번째 물건을 담지 않고 i-1번째 때 현재 용량의 가치
                K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w])
            else:
                #만약 i번째 물건이 현재 용량보다 높음 -> 테이블에 들어갈 수 없으므로 i-1번째 현재 용량을 가져옴
                K[i][w] = K[i-1][w]
    for i in K:
        print(i)
    return K[n][W]


# 결과 출력
print(knapsack(W, wt, val, n))
