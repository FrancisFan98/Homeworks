let array_above thresh arr = 
  let rs = ref [||] in 
  for i = 0 to Array.length arr - 1 do 
    if arr.(i) > thresh then
      rs := (Array.append !rs [|arr.(i)|])
     done;
   rs
;;


let rec list_above thresh lst = 
   match lst with 
   | [] -> []
   | h::t -> if h > thresh then h::(list_above thresh t) else list_above thresh t
;;



;;
(* val list_above : 'a -> 'a list -> 'a list

   Create a list which has only elements from lst that are larger than
   thresh.  Uses recursion to accomplish this in a single pass over
   the original list. Does not modify the original list lst.

   REPL EXAMPLES:
   # list_above 0 [0; 1; 2];;
   - : int list = [1; 2]
   # list_above 0 [0; 1; 2; 0];;
   - : int list = [1; 2]
   # list_above 0 [4; -2; -1; 7; 0; 3];;
   - : int list = [4; 7; 3]
   # list_above 3 [4; -2; -1; 7; 0; 3];;
   - : int list = [4; 7]
   # list_above 1.5 [4.2; 0.5; 1.2; 7.6; 8.9; 0.8; 8.5];;
   - : float list = [4.2; 7.6; 8.9; 8.5]
   # list_above 0.0 [4.2; 0.5; 1.2; 7.6; 8.9; 0.8; 8.5];;
   - : float list = [4.2; 0.5; 1.2; 7.6; 8.9; 0.8; 8.5]
   # list_above 9.0 [4.2; 0.5; 1.2; 7.6; 8.9; 0.8; 8.5];;
   - : float list = []
   # list_above false [false; true; false; true; true;];;
   - : bool list = [true; true; true]
*)
