import math

def solution(brown, yellow):
    
    if yellow == 1:
        return [3, 3]

    for i in range(yellow):
        current = yellow - i
        if(yellow % current == 0):
            width = current+2
            height = int(yellow/current) + 2
            if(width-2)*2 + (height-2)*2 + 4 == brown:
                break
    
    return [width, height]