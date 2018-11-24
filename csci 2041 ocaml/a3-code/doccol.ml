(* doccol.ml: Type and functions for a collection of named documents.
   Tracks a current document and its name along with an association
   list of all docs in the collection.  Preserves uniqueness of names
   in the collection. Makes use of built-in List functions to
   ad/remove/get docs from the association list. *)

(* Type to track a collection of named documents in an association
   list. *)
type 'a doccol = {
  mutable count   : int;                                  (* count of docs in list *)
  mutable curdoc  : 'a Document.document;                 (* current list being edited *)
  mutable curname : string;                               (* name of current list *)
  mutable docs    : (string * 'a Document.document) list; (* association list of names/docs *)
};;

let make name doc = 
   {
   count = 1;
   curdoc = doc;                                           (*create a new document*)
   curname = name;
   docs = [(name, doc)];
   }

;;
(* val make : string -> 'a Document.document -> 'a doccol
   Create a doccol. The parameters name and doc become the current
   doc and the only pair in the docs association list. *)

let add doccol name doc =  
   let exist = List.assoc_opt name doccol.docs in                    (*if already exist, do nothing and return false*)
   if exist <> None then (
      
      false;            
   )
   else (                                                            (*if not already exist, add it and return true*)
      doccol.count <- doccol.count + 1;
      doccol.docs <- (name, doc) :: doccol.docs;
      true;
   )
;;
(* val add : 'a doccol -> string -> 'a Document.document -> bool
   If there is already a doc with name in doccol, do nothing and
   return false.  Otherwise, add the given doc to doccol with the
   given name, update the count of docs and return true. Uses
   association list functions from the List module. *)

let remove doccol name = 
   if name = doccol.curname then false                                  (*if it is current doc, do nothing and return false*)
   else(
      let exist = List.assoc_opt name doccol.docs in                    (*if not already exist, do nothing and return false*)
      if exist = None then (
         false;
      )
      else(                                                             (*if already exist, remove it and return true*)
         doccol.count <- doccol.count - 1;
         doccol.docs <- List.remove_assoc name doccol.docs;
         true;
      )
   )
;;

(* val remove : 'a doccol -> string -> bool
   If name is equal to curname for the doccol, do nothing and return
   false.  If there is no doc with name in doccol, do nothing and
   return false.  Otherwise, remove the named doc from doccol,
   decrement the count of docs, and return true. Uses association list
   functions from the List module. *)

let has doccol name =
   let exist = List.assoc_opt name doccol.docs in                             (*if exist return true else return false*)
   if exist <> None then true
   else false

;;
(* val has : 'a doccol -> string -> bool
   Returns true if the named doc is in the doccol and false otherwise. *)

let switch doccol name = 
   if has doccol name then(
      doccol.curname <- name;
      doccol.curdoc <- List.assoc name doccol.docs;                  (*if doc exists switch it to the current else return false*)
      true;
   )
   else false;
;;
(* val switch : 'a doccol -> string -> bool
   Change the current document/name to the named document and return
   true. If the named document does not exist, return false and make
   no changes to doccol. *)

let string_of_doccol doccol = 
   let rs = ref ((string_of_int doccol.count) ^ " docs\n") in                             (*build the result string document by document*)
   List.iter (fun (name, doc) -> rs := !rs ^ "- " ^ name ^ "\n") doccol.docs;
   String.sub !rs 0 (String.length !rs -1)                                                (*return the result string with the last \n removed*)
;;
(* val string_of_col : 'a doccol -> string
   Creates a string representation of doccol showing the count of
   docs and the names of all docs. Each doc is listed on its own
   line. It has the following format:

   4 docs
   - test-dir/heros.txt
   - places.txt
   - stuff.txt
   - default.txt

   Does not define any helper functions. Makes use of higher order
   functions such as List.map and/or List.fold. May also use string 
   processing functions such asString.concat and/or Printf.sprintf *)