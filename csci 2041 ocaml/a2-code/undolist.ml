(* undolist.ml : This module manages a sorted list strings. add,
   remove, and merge operations are provided. undo/redo operations are
   provided to alter list. Type annotaions are required on the
   module-level values as refs to 'a list are not allowed due to weak
   typing. 

   All functions in this file pertain to PROBLEM 3
*)

let curr_list : string list ref = ref [] ;;
(* The current list of strings. *)

let undo_stack : string list list ref = ref [] ;;
(* List of previous curr_lists to enable undo. *)

let redo_stack : string list list ref = ref [] ;;
(* List of undone curr_lists to enable redo. *)

let reset_all () = 
   curr_list := [];
   undo_stack := [];
   redo_stack := []
;;
(* Reset curr_list, undo_stack, redo_stack to all be empty lists. Used
   only in testing, not in main programs. *)

let set_to_list new_list = 
   undo_stack := [!curr_list]@(!undo_stack);             (*append the value of curr_list to the front of undo_stack*)
   curr_list := new_list                                 (*set curr_list to the new list*)
;;
(* curr_list is moved to the top of the undo_Stack. Then curr_list is
   set to the new_list. Empties redo_stack. *)

let add_elem elem = 
   set_to_list (Sortedlist.insert !curr_list elem);         (*use insert from sortedlist.ml*)
   redo_stack := []
;;
(* Add the given elem to curr_list producing a new_list.  Calls
   set_to_list on result to unable undoing. *)

let remove_elem elem = 
   set_to_list (Sortedlist.remove !curr_list elem);      (*use remove from sortedlist.ml*)
   redo_stack := []

;;
   (*let rec helper list elem =
         match list with 
         | [] -> []
         | h::t -> if h = elem then t else h :: helper t elem
      in
   set_to_list (helper !curr_list elem) *)

(* Remove the given elem from curr_list producing a new list. Calls
   set_to_list on result to unable undoing.  *)

let merge_with_list list = 
   set_to_list (Sortedlist.merge list !curr_list);          (*use merge from sortedlist.ml*)
   redo_stack := []
;;
(* Merge the param list with the current list. Calls set_to_list on
   the result to enable undoing. *)

let undo () = 
   let rec helper list =
      match list with                  (*helper function modifies the value of curr_list and return the undo_stack with the first element removed*)
      |[] -> []
      | h::t -> 
         curr_list := h;
         t
           
   in
   if !undo_stack <> [] then (
      redo_stack := [!curr_list]@(!redo_stack);          (*add the curr_list to redo_stack*)
      undo_stack := helper !undo_stack;                  (*update undo_stack to the result of helper function*)
      true
   )
   else(
      false
   )
;;
(* If the undo_stack is not empty, undo the last operation. curr_list
   is moved to the redo_stack and the top element of undo_stack is
   removed and becomes curr_list.  Returns true if changes are made
   and false if undo_stack is empty and no changes are made. Operates
   in constant time. *)

let redo () = 
   let rec helper list =      (*helper function modifies the value of curr_list and return the redo_stack with the first element removed*)
      match list with 
      |[] -> []
      | h::t -> 
      curr_list := h;
      t

   in
   if !redo_stack <> [] then (
      undo_stack := [!curr_list]@(!undo_stack);          (*add the curr_list to undo_stack*)
      redo_stack := (helper !redo_stack);                (*update redo_stack to the result of helper function*)
      true
   )
   else(
      false
   )
;;
(* If the redo_stack is not empty, redo the last operation. curr_list
   is moved to the undo_stack and the top element of redo_stack is
   removed and becomes curr_list.  Returns true if changes are made
   and false if redo_stack is empty and no changes are made. Operates
   in constant time. *)