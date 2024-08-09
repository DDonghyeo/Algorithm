#라이언이 가장 큰 점수차로 우승하는 방법 구하기.
# n : 화살의 개수
# info : 어피치가 쏜 화살
# 1~10점 중 더 많은 화살을 맞춘 사람에게 점수.
# 같은 개수를 맞췄다면 어피치가 가져감.

# 가장 큰 점수차로 승리하는 방법 List를 return,승리할 수 없으면 [-1]

def solution(n, info):
    answer = [0 for _ in range(11)] 

    #최대 점수를 내는 방법
    #한 발당 가치를 구해서 투자하는 방식 ??
    values = [[0, 0] for _ in range(11)]
    
    for i in range(10):
        #어피치가 맞춘 화살 수
        arrows = info[i]

        #투자 점수
        invest_score = 10-i

        # 만약 어피치가 맞춘 화살이라면 가치는 2배가 됨
        if(info[i] != 0):
            invest_score *= 2
        # 해당 점수를 획득하려면 arrows+1 만큼 투자해야 함
        # 1발당 투자 가치는 score/(arrows+1)
        # [1발당 투자 가치 , 투자해야 하는 화살 수]
        values[i] = [invest_score/(arrows+1), arrows+1]
        
    print("values : ", values)

    result_score = 0
    while (n > 0):
        max_idx = -1
        for i in range(11):
            if(values[i][0] > values[max_idx][0]):
                if(values[i][1] <= n):
                    max_idx = i

            if(values[i][0] == values[max_idx][0]):
                if(values[i][1] < values[max_idx][1]):
                    max_idx = i
        
        if(max_idx == -1):
            answer[10] += n
            break
        answer[max_idx] = values[max_idx][1]
        result_score += 10-max_idx
        n -= values[max_idx][1]

        values[max_idx][0] = -1

    lion_score = 0
    appeach_score = 0
    for i in range (10):

        lion = answer[i]
        appeach = info[i]

        if(lion==appeach):
            continue

        if(lion>appeach):
            lion_score += 10-i
        else:
            appeach_score += 10-i
    

    if(appeach_score >= lion_score):
        return [-1]
    else:
        return answer