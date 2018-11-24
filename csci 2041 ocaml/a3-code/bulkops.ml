(* bulkops.ml: Implement bulk operations on Doccol's of string list
   Documents that are useful for multimanager.  Since the functions in
   this module require access to fields and types of other modules, start
   the file by opening those two modules:

   open Document;; 
   open Doccol;; 
*)
open Document;;
open Doccol;;
open Sortedlist;;
open Util;;

let showall doccol = 
   List.iter (fun (name, doc) -> Printf.printf "--List %s--\n" name;
                                 print doc.current;                                       (*print document's current value one by one*)
                                 Printf.printf"\n" ) doccol.docs 
;;
(* val showall : string list Doccol.doccol -> unit
   Prints all documents in doccol to the screen. For each list,
   prints the list name first and then each element of the list using
   Sortedlist functions. Uses higher-order functions to iterate over
   the doclist. 

   EXAMPLE:
   --List test-data/heros.txt--
   Asami
   Bolin
   Bumi
   Jinora
   Korra
   Kya
   Mako
   Tenzin
   
   --List test-data/villains.txt--
   Amon
   Hiroshi
   Kuvira
   Ming-Hua
   P-li
   Unalaq
   Zaheer
   
   --List default.txt--
   Korra
   Meelo
   Pema
*)

let saveall doccol = 
   List.iter (fun (name, doc) -> strlist_to_file doc.current name) doccol.docs               (*write document's current value into file one by one*)

;;
(* val saveall : string list Doccol.doccol -> unit
   Saves all documents in doccol. Makes use of Util functions to do
   I/O. Makes use of higher-order functions to write each list to
   associated file name. *)

let addall doccol elem = 
   List.iter (fun (name, doc) -> doc.undo_stack <- doc.current::doc.undo_stack;              (*add element to each document's current value one by one*)
                                 doc.current <- insert doc.current elem;
                                 ) doccol.docs
;;
(* val addall : 'a list Doccol.doccol -> 'a -> unit
   Adds the given element to all docs in doccol. Makes use of
   higher-order functions and Sortedlist functions to modify each
   list. Each doc/list can individually undo the addition. *)

let mergeall doccol = 
   List.fold_left (fun rs (name, doc) -> merge rs doc.current) [] doccol.docs                (*merge document's current value one by one*)
;;
(* val mergeall : 'a list Doccol.doccol -> 'a list
   Merges all lists in doccol.docs into a single list and returns
   it. Uses higher-order functions and Sortedlist functions to perform
   the merge. *)