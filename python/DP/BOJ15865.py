#Knapsack Problem
n, W = map(int, input().split())

wt = []
val = []

for _ in range(n):
    w, v = map(int, input().split())
    wt.append(w)
    val.append(v)

def knapsack(W, wt, val, n):
    # DP 테이블 초기화
    K = [[0 for _ in range(W + 1)] for _ in range(n + 1)]
    
    # DP 테이블을 채워나갑니다
    for i in range(n + 1):
        for w in range(W + 1):
            if i == 0 or w == 0:
                K[i][w] = 0
            elif wt[i-1] <= w:
                K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w])
            else:
                K[i][w] = K[i-1][w]
    
    return K[n][W]

# 결과 출력
print(knapsack(W, wt, val, n))
