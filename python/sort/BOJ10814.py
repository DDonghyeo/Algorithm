n = int(input())

arrays = []
for _ in range(n):
    age, name = input().split()
    arrays.append([age, name])

arrays.sort()
arrays.sort()

for i in range(n):
    print(arrays[i][0],arrays[i][1])