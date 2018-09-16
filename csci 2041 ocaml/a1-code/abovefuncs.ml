let array_above thresh arr = 
  let rs = ref [||] in 
  for i = 0 to Array.length arr - 1 do 
    if arr.(i) > thresh then
      rs := (Array.append !rs [|arr.(i)|])
     done;
   !rs
;;


let rec list_above thresh lst = 
   match lst with 
   | [] -> []
   | h::t -> if h > thresh then h::(list_above thresh t) else list_above thresh t
;;



