def solution(cap, n, deliveries, pickups):

    answer = 0
    
    
    idx = n-1 # 인덱스 가장 끝부터 시작 

    while idx != 0:
        #끝에서부터 첫 번째로 0이 아닌 index를 찾기
        while True:
            if (deliveries[idx] != 0) or (pickups[idx] != 0):
                start_idx = idx
                break
            else:
                idx -= 1
        
        answer += (start_idx+1) * 2
        #총 4번 깎으면서 내려가기
        deliver_cnt = 0
        pickup_cnt = 0

        cur_deliver_idx = start_idx
        while deliver_cnt != cap:
            if(cur_deliver_idx == 0):
                break
            # 만약 비어있다면 내려가기
            if(deliveries[cur_deliver_idx] == 0):
                cur_deliver_idx -= 1
            #비어있지 않다면 하나 깎기
            else:
                deliveries[cur_deliver_idx] -= 1
                deliver_cnt += 1
        
        cur_pickup_idx = start_idx
        while pickup_cnt != cap:
            if(cur_pickup_idx == 0):
                break

            if(pickups[cur_pickup_idx] == 0):
                cur_pickup_idx -= 1
            else:
                pickups[cur_pickup_idx] -= 1
                pickup_cnt += 1
        idx = max(cur_deliver_idx, cur_pickup_idx)
        
    return answer


