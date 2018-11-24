(* map_modules.ml: provides two modules for maps.
   1. StringStringMap which maps strings to strings
   2. IntpairBoolMap which maps pairs of ints to bools.
   Both modules are created by creating a short module adhering to the
   Treemap.KEYVAL_SIG signature and then invoking the Treemap.Make
   functor. *)

open Printf;;

(* Interface module for maps of string to string *)
module StringStringKV = struct
	type key_t = string;;
	type value_t = string;;
	let compare_keys = String.compare;;
	let keyval_string key value = sprintf "{%s -> %s}" key value;;
end
;;

(* A map module from string keys to string values. *)
module StringStringMap = Treemap.Make(StringStringKV)
;;

(* Interface module for maps of int pairs to bool *)
module IntpairBoolKV = struct
	type key_t = int * int;;
	type value_t = bool;;
	let compare_keys (k11, k12) (k21, k22) = 
		if k11-k21 <> 0 then k11 - k21
		else k12 - k22;;
	let keyval_string (k1,k2) value = 
		sprintf "{%d > %d : %b}" k1 k2 value;;
end;;


(* A map module from int pair keys to bool values. *)
module IntpairBoolMap = Treemap.Make(IntpairBoolKV);;
