def solution(cap, n, deliveries, pickups):
    #배열 뒤에서부터 시작
    deliveries = deliveries[::-1]
    pickups = pickups[::-1]
    answer = 0

    delivery_need = 0
    pickup_need = 0

    for i in range(n):
        delivery_need += deliveries[i]
        pickup_need += pickups[i]
        
        while delivery_need > 0 or pickup_need > 0:
            delivery_need -= cap
            pickup_need -= cap
            answer += (n - i) * 2
    return answer
