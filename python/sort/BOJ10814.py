n = int(input())
member = []

for i in range(n):
    age, name = map(str, input().split())
    age = int(age)
    member.append((age, name))

#stable(안정) 정렬 : 입력 받은 값 중 같은 값이 있는 경우 순서를 그대로 유지
member.sort(key = lambda x : x[0])

for i in member:
    print(i[0], i[1])
