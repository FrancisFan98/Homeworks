let array_above thresh arr = 
  let len = ref 0 in 
  for i = 0 to Array.length arr - 1 do
    if arr.(i) > thresh then
      len := !len + 1
  done;
  let rs = ref (Array.make !len thresh) and index = ref 0 in
  for i = 0 to Array.length arr - 1 do
    if arr.(i) > thresh then begin
      !rs.(!index) <- arr.(i);
      index := !index + 1
    end
  done;
  !rs
;; 


  (*let rs = ref [||] in 
  for i = 0 to Array.length arr - 1 do 
    if arr.(i) > thresh then
      rs := (Array.append !rs [|arr.(i)|])
     done;
   !rs*)
;;


let rec list_above thresh lst = 
   match lst with 
   | [] -> []
   | h::t -> if h > thresh then h::(list_above thresh t) else list_above thresh t
;;



