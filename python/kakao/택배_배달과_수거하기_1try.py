def solution(cap, n, deliveries, pickups):

    answer = 0
    
    while any(deliveries) or any(pickups):
        longest = find_longest(deliveries, pickups)
        deliveries, pickups = start(deliveries, pickups, cap)
        answer += (longest+1)*2
        
    return answer

def find_longest(deliveries, pickups):
    longest = 0
    for i in range(len(deliveries) - 1, -1, -1):
        if deliveries[i] > 0:
            longest = i
            break
    
    for i in range(len(pickups) - 1, -1, -1):
        if pickups[i] > 0:
            longest = max(i, longest)
            break
    return longest

def start(deliveries, pickups, cap):
    complete_delivery = 0
    for i in range(len(deliveries) - 1, -1, -1):
        if complete_delivery == cap:
            break
            
        if deliveries[i] > 0:
            current_cap = cap-complete_delivery
            # 배달 필요한 수가 용량보다 클 경우
            if(deliveries[i] > current_cap):
                deliveries[i] -= current_cap
                complete_delivery = cap
            # 배달 필요한 수가 용량보다 작거나 같을 경우
            else:
                complete_delivery += deliveries[i]
                deliveries[i] = 0
                
    
    complete_pickups = 0
    for i in range(len(pickups) - 1, -1, -1):
        if complete_pickups == cap:
            break
            
        if pickups[i] > 0:
            current_cap = cap - complete_pickups
            if(pickups[i] > current_cap):
                pickups[i] -= current_cap
                complete_pickups = cap
            else:
                complete_pickups += pickups[i]
                pickups[i] = 0

            
    return deliveries, pickups


#결과 시간초과