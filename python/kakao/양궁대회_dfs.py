def solution(n, info):

    answer = []

    def dfs(idx, arrows, score, lion_info):
        nonlocal answer, max_diff

        #마지막으로 왔을 때
        if idx == 11:
            # 남은 화살은 모두 0점
            if arrows:  
                lion_info[10] = arrows

            #어피치와의 점수 차이 구하기            
            diff = score - sum((10-i) for i in range(11) if info[i] and info[i] >= lion_info[i])
            
            #최대 점수 차이보다 크거나 같은 경우
            if diff > 0 and diff >= max_diff:
                #최대 점수 차이보다 더 큰 경우 -> 교체
                if diff > max_diff:
                    max_diff = diff
                    answer = lion_info[:]
                
                #점수 차이가 같은 경우 -> 0점부터 올라가면서 낮은 점수에 라이언이 더 높은 화살을 쏜게 있을 경우 answer로 교체
                elif diff == max_diff:
                    for i in range(10, -1, -1):
                        if lion_info[i] > answer[i]:
                            answer = lion_info[:]
                            break
                        elif lion_info[i] < answer[i]:
                            break
            return

        #현재 점수를 취득할 수 있을 때 -> 취득하고 다음 수를 보는 경우
        if arrows > info[idx]:
            next_lion_info = lion_info[:] # 얕은 복사
            next_lion_info[idx] = info[idx] + 1 # 어피치 화살보다 + 1 더 많게 
            dfs(idx+1, arrows - next_lion_info[idx], score + (10-idx), next_lion_info) # 다음 인덱스로 이동

        #현재 점수를 취득하지 않고 넘어가는 경우
        dfs(idx+1, arrows, score, lion_info)

    
    max_diff = 0
    dfs(0, n, 0, [0] * 11)
    
    return answer if answer else [-1]