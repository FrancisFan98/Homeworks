let array_sum arr = 
  let rs = ref 0 in
  for i=0 to Array.length arr -1 do 
    rs := !rs + arr.(i)
  done;
  rs.contents
;;



let rec list_sum lst = 
   match lst with 
   | [] -> 0
   | h::t -> h + list_sum t
;;





