let rec insert list elem = 
  match list with
  | [] -> [elem]
  | h :: t -> if (elem = h) then h::t                         (*if elem equals to the current value in the list return the original list *)
              else(
                if elem < h then elem :: h :: t               (* if elem is smaller than the current value then add elem before current position *)
                else h :: (insert t elem)                     (*if not check the rest of the list *)
              )
;;

let rec remove list elem = 
  match list with
  | [] -> []
  | h :: t -> if h <> elem then h :: remove t elem       (*if h not equal elem check the rest of the list for removal*)
              else t
;;

let rec print list = 
  match list with
  |[] -> ()
  |h :: t -> if true then (Printf.printf "%s\n" h; print t)    (*recurse through the list and print*)
;;


let rec merge lista listb = 
  match lista, listb with 
  | [], [] -> []      
  | [], (listbh :: listbt) -> listbh :: listbt            (*if the first list is empty, return all of second list*)
  | (listah :: listat), [] -> listah :: listat            (*if the second list is empty, return all of first list*)
  | (listah :: listat), (listbh :: listbt) ->                         (* if they are both not empty*)
    if listah = listbh then listah :: merge listat listbt             (* if the first elements are the same, add the element once and check the rest of lista and listb*)
    else if listah < listbh then listah :: merge listat listb         (* else add the element that is smaller first and check the rest of lista and listb*)
    else listbh :: merge lista listbt
;;
(* val merge : 'a list -> 'a list -> 'a list
   PROBLEM 2: Merge two sorted lists: lista and listb.  Elemetns that
   appear in both lists appear only once in the result.  Operates in
   linear time on the length of lists: does not do repeated
   insertion. Not tail recursive. May use pattern matching OR if/else
   clauses. Runs in linear time on combined length of lists.

   REPL EXAMPLES  
   # merge [] [2;4;6];;
   - : int list = [2; 4; 6]
   # merge [1;3;5] [2;4;6];;
   - : int list = [1; 2; 3; 4; 5; 6]
   # merge [1;3;5] [];;
   - : int list = [1; 3; 5]
   # merge [1;3;5] [2;3;4;6;8];;
   - : int list = [1; 2; 3; 4; 5; 6; 8]
   # merge ["a";"c";"e"] ["b";"d"];;
   - : string list = ["a"; "b"; "c"; "d"; "e"]
   # merge ["a";"c";"e"] ["b";"c";"d";"e"];;
   - : string list = ["a"; "b"; "c"; "d"; "e"]
*)


(* val merge : 'a list -> 'a list -> 'a list
   PROBLEM 2: Merge two sorted lists: lista and listb.  Elemetns that
   appear in both lists appear only once in the result.  Operates in
   linear time on the length of lists: does not do repeated
   insertion. Not tail recursive. May use pattern matching OR if/else
   clauses. Runs in linear time on combined length of lists.

   REPL EXAMPLES  
   # merge [] [2;4;6];;
   - : int list = [2; 4; 6]
   # merge [1;3;5] [2;4;6];;
   - : int list = [1; 2; 3; 4; 5; 6]
   # merge [1;3;5] [];;
   - : int list = [1; 3; 5]
   # merge [1;3;5] [2;3;4;6;8];;
   - : int list = [1; 2; 3; 4; 5; 6; 8]
   # merge ["a";"c";"e"] ["b";"d"];;
   - : string list = ["a"; "b"; "c"; "d"; "e"]
   # merge ["a";"c";"e"] ["b";"c";"d";"e"];;
   - : string list = ["a"; "b"; "c"; "d"; "e"]
*)
