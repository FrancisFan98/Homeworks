let rec fibonacci i =
if i = 0 then 1
else if i = 1 then 1
else fibonacci(i-1) + fibonacci(i-2);;

let i = int_of_string Sys.argv.(1) in 
let j = fibonacci i in
print_int j;
print_string "\n";;


