n =  int(input())

maps = [list(map(int,input().split())) for _ in range(n) ]

x = n

result = [0, 0]

def divide(startX, startY, endX, endY):
    boxLen = (endX - startX)
    # 1X1 박스일 경우
    if(boxLen == 1):
        color = maps[startX][startY]
        result[color] += 1
        return
    
    #색종이 완성 여부 검사
    isComplete = True
    color = maps[startX][startY]
    for i in range(endX - startX):
        for j in range(endY - startY):
            if(maps[startX + i][startY + j] != color):
                isComplete = False
                break
    
    if(isComplete):
        #범위 내에 다른 숫자가 없었다면 -> 하나 완성, 더이상 쪼개지 않아도 됨.
        result[color] += 1
        return
    else:
        #완성되지 않음 -> 4부분 나누어서 실행
        divide(startX, startY, startX + int(boxLen/2), startY + int(boxLen/2))
        divide(startX + int(boxLen/2), startY, startX + boxLen, startY + int(boxLen/2))
        divide(startX, startY + int(boxLen/2), startX + int(boxLen/2), startY + boxLen)
        divide(startX + int(boxLen/2), startY + int(boxLen/2), startX + boxLen, startY + boxLen)
        


divide(0, 0, n, n)
print(result[0])
print(result[1])