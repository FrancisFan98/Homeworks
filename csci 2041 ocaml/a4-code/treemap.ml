(* treemap.ml: Provides a functor to create a map from an arbitrary
   key type to an arbitrary value type. Functor Make requires a module
   input matching the KEYVAL_SIG signature.  The resulting module
   has the same functions as treemap but that works for maps with the
   specified key/value types.  As the name implies, a BST is the
   underlying data structure for the map.
*)
open Printf;;
(* Type of key/vals that go into Treemaps. Includes key and value
   type, key comparison function, and a string function for easy
   display. *)
module type KEYVAL_SIG =
sig
  type key_t ;;
  type value_t ;;
  val compare_keys : key_t -> key_t -> int;;
  val keyval_string : key_t -> value_t -> string;;
end;;

(* Functor which creates a module for maps with key/value types
   specified by the parameter module KVMod. *)
module Make (KVMod : KEYVAL_SIG) = struct 
  (* treemap.ml: Provides types and functions for a map from string keys
   to string values. Internally uses a binary search tree sorted on
   the keys. Some functions must be completed. *)
(* Algebraic type for a persistent binary search tree key/value
   mappings. 
*)
  type treemap =
    | Empty               (* no data: bottom of tree   *)
    | Node of {           (* node of anonymous record  *)
      key   : KVMod.key_t;   (* key for this node         *)
      value : KVMod.value_t;   (* value associated with key *)
      left  : treemap;    (* left branch               *)
      right : treemap;    (* right branch              *)
    }
  ;;

(* Empty map value. Could use treemap.Empty but it is standard libary
   convention to provide a binding called "empty" for this. *)
let empty = Empty;;

(* val add : treemap -> string-> string -> treemap

   let newmap = add map key value in ...

   Returns a new map with key bound to value. If key is already
   present, its binding is re-assigned to the given value.  
*)
let rec add map key value =
  match map with
  | Empty ->                                             (* bottom of tree: didn't find *)
     Node{key=key; value=value;                          (* make a new node with key/val binding *)
          left=Empty; right=Empty}
  | Node(node) ->                                        (* at a node *)
     let diff = KVMod.compare_keys key node.key in           (* compute a difference *)
     if diff = 0 then                                    (* 0 indicates equal *)
       Node{node with value=value}                       (* replace value binding with new value *)
     else if diff < 0 then                               (* negative indicates str less than data *)
       Node{node with left=(add node.left key value)}    (* create a new node with new left branch *)
     else                                                (* positive indicates str greater than data *)
       Node{node with right=(add node.right key value)}  (* create new node with new right branch *)
;;

(* val tree_string : treemap -> string

   let s = tree_string map in ...

   Use a Buffer (extensible string) and a right-to-left in-order
   traversal of the internal tree to construct a string version of the
   map. Nodes are indented according to their depth: root is left-most
   with children farther to the right. Each data element is preceded
   by its integer depth. Right-most elements appear at the top while
   left-most elements appear at the bottom. This means elements read
   in REVERSE order from top to bottom and that mentally rotating the
   tree clockwise gives appropriate left/right branch positions.  
*)
let tree_string map =
  let buf = Buffer.create 256 in                    (* extensibel character buffer *)

  let rec build tree depth =                        (* recursive helper *)
    match tree with
    | Empty -> ()                                   (* out of tree, done with this branch *)
    | Node(node) ->                                 (* have a node *)
       build node.right (depth+1);                  (* recurse on right branch *)
       for i=1 to depth do                          (* indent according to depth of this node *)
         Buffer.add_string buf "  ";
       done;
       let datastr =                                (* string with depth and data  *)
         sprintf "%2d: %s\n" depth (KVMod.keyval_string node.key node.value)
       in
       Buffer.add_string buf datastr;               (* add to buffer *)
       build node.left (depth+1);                   (* recurse on left branch *)
  in                                                (* end helper *)

  build map 0;                                      (* recurse from root *)
  Buffer.contents buf                               (* return string from Buffer *)
;;

(* IMPLEMENT

   val getopt : treemap -> string -> string option

   let opt = getopt map key in ...

   Search the map for given key. If found, return the Some of the
   associated value. Otherwise return None.
*)
let rec getopt map key =
  match map with
  | Empty -> None
  | Node(node) -> 
    let diff = KVMod.compare_keys key node.key in     (*check the difference of the two keys *)
      if diff = 0 then                                (*if they are equal, return the value*)
        Some node.value                               
      else if diff < 0 then                           (*if diff is negative ,go left*)
        getopt node.left key
      else getopt node.right key                      (*else go right*)
;;

(* IMPLEMENT

   val contains_key : treemap -> string -> bool

   let present = contains_key map key in ...

   Returns true if key is present in the map and false
   otherwise. Uses function getopt.
*)
let contains_key map value =
  getopt map value != None                    (*if getopt is not None, the value exist*)
;;


(* IMPLEMENT

   val iter : (string -> string -> unit) -> treemap -> unit

   let func key value = ... in

   fold func map;

   Apply func to all elements key/value pairs in the map for
   side-effects. Keys are visited in sorted order from minimum to
   maximum. Works as other iter-like functions such as List.iter
   or Array.iter.  
*)
let rec iter func map =
  match map with 
  | Empty -> ()
  | Node(node) ->
    iter func node.left;
    func node.key node.value;
    iter func node.right;
    ()
;;

(* IMPLEMENT

   val fold : ('a -> string -> string -> 'a) -> 'a -> treemap -> 'a

   let func cur key value = ... in

   let reduction = fold func init map in ...

   Apply func to all elements key/value pairs in the map to produce a
   single value at the end. Keys are visited in sorted order from
   minimum to maximum. Works as other fold-like functions such as
   List.fold_left or Array.fold_left.  
*)

let rec fold func cur map =
  match map with 
  | Empty -> cur
  | Node(node) ->
    let n = fold func cur node.left in                (*go left *)
    let nn = func n node.key node.value in            (*do the calculations*)
    fold func nn node.right                           (*go right and do the above recursively*)
;;

(* IMPLEMENT

   to_string : treemap -> string

   let displaystr = to_string map in ...

   Produces a string representation of the map. The format is

   "[{k1 -> v1}, {k2 -> v2}, {k3 -> v4}]"

   Key/vals appear from minimum key to maximum key in the output
   string. Functionality from the Buffer module is used with an
   in-order traversal to produce the string efficiently. May make use
   of a higher-order map function such as iter or fold.
*)
let to_string map =                                 (* verbose version: no use of iter *)
  let buf = Buffer.create 256 in
  let rec build map  = 
    match map with 
    | Empty -> ()
    | Node(node) -> 
      build node.left;                                                            (*go left *)  
      Buffer.add_string buf ((KVMod.keyval_string node.key node.value) ^ ", ");   (*do the calculations*)      
      build node.right                                                            (*go right and do the above recursively*)
    in
    build map;
    if String.length (Buffer.contents buf) < 2 then "[]"
    else "[" ^ (String.sub (Buffer.contents buf) 0 (String.length (Buffer.contents buf) -2) ) ^ "]"       (*remove the last 2 ", " characters *)
;;  

(* IMPLEMENT

   val findmin_keyval : treemap -> (string * string)

   let (minkey,minval) = findmin treemap in ...

   Find the "minimum" key in the given map and return it and its value
   as a pair. If used with an empty map, fails with exception message
   "No minimum in an empty tree".  Since the map is based on a BST,
   the minimum key is at the left-most node.  
*)

let rec findmin_keyval map =
  match map with 
  | Empty -> failwith "No minimum in an empty tree"
  | Node(node) -> 
    if node.left <> Empty then findmin_keyval node.left             (*find the minumun value in the right branch*)
    else (node.key, node.value)
;;

(* IMPLEMENT

   val remove_key : treemap -> string -> treemap

   let newmap = remove map key in ...

   Returns a new map without key in it.  The internal tree is
   re-arranged according to standard BST conventions: (1) If key is a
   leaf, it is replaced with Empty, (2) If key is an internal node
   with one child, it's left or right branch replaces it, (3) If key
   is an internal node with both left/right children, it successor
   replaces it. Makes use of findmin_keyval to locate a succesor as
   the minimum of on the right child branch.
*)
let rec remove_key map key =
  match map with 
  | Empty -> Empty
  | Node(node) -> 
  let diff = KVMod.compare_keys key node.key in
  if diff = 0 then (                                                        (*if find the target *)
    if node.left = Empty && node.right =Empty then Empty                    (*no children available *)
    else if node.left = Empty then node.right                               (*no left branch, so move right branch up*)
    else if node.right = Empty then node.left                               (*no left branch, so move right branch up*)
    else 
      (let (k, v) = findmin_keyval node.right in
      Node{key = k; value = v; left = node.left; right = remove_key node.right k}
      )
    )
  else if diff < 0 then 
    Node{key = node.key; value = node.value; left = remove_key node.left key; right = node.right}     (*replace the left branch with the target node removed*)
  else 
    Node{key = node.key; value = node.value; left = node.left ; right = remove_key node.right key}     (*replace the right branch with the target node removed*)
  
;;

end;;
