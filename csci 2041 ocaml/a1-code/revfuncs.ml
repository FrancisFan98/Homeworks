let array_rev arr = 
  let rs = ref [||] in 
  for i = 0 to Array.length arr - 1 do
    rs := Array.append [|arr.(i)|] !rs 
  done;
  for i = 0 to Array.length arr - 1 do
    arr.(i) <- !rs.(i)
  done;
;;


let list_rev lst = 
  let rec lll lst = 
    match lst with 
    | [] -> []
    | h::t -> lll t @ [h]
 in
  lll lst
;;
