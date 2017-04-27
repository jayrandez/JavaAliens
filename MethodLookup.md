## Example Class Map Lookup (Invocation):
```
function: MyClass = (

    | obj |

    obj:: MyClass new: {arg1. arg2}.

    obj call: 'method' args: {arg1. arg2}.

    obj set: 'field' to: arg1

    MyClass call: 'staticMethod'.

)
```


## Lookup Rules:

1. Method must be in map, regardless of loading approach used.
2. Type checking is mandatory to prevent segfaults and undefined operation, so
  a. Exactly typed JavaAliens are needed if the class map is incomplete.
	b. Type inference can be used if the class map is complete.

** Exact Type Validation: **
Map is scanned for a method in which all JavaPrimitive arguments match the signature exactly (i.e. JavaInt for each int, JavaChar for each char), and where all of the JavaObject classes match the method signature exactly. JavaObjects can be cast beforehand.

** Type Inference: **
Type inference requires a fully loaded class map, because the most suitable method for a set of arguments might be unknown otherwise.

E.x. The user sends: obj call: 'method' args: {1}.
And java provides: void method(int a), and void method(short a).

Here, the first option is the most correct, but if we weren't aware of it, the second option would be improperly chosen, causing undefined operation.

E.x. The user sends: obj call: 'method' args: {ArrayList new}
And java provides: void method(Object a), and void method(ArrayList a).

Here, the second option is the most correct, but if the second option weren't in the map, the first option would be improperly chosen.

## Explicit (Incomplete) Class Map Loading:
```
| MyClass | 

MyClass:: JavaClass find: 'com/me/MyClass'.

MyClass constructor sig: '(II)V'.

MyClass static method: 'method' sig: '(II)J'.

MyClass instance field: 'field' sig: 'I'
```

## Reflection-Driven (Complete) Map Loading:
```
| MyClass | 

MyClass:: JavaClass find: 'com/me/MyClass'

MyClass load.

	or simply,

| MyClass |

MyClass:: (JavaClass find: 'com/me/MyClass') load.
```

## Class Map Construction

- Constructor List
```
	[ arg_signature, methodID ]
	  arg_signature, methodID
```
- Static/Instance Method Maps:
```
	name: { return_signature, [ arg_signature methodID ] }
				    arg_signature methodID 
	name: {	return_Signature, [ arg_signature methodID ] }
				    arg_signature methodID 
```
- Static/Instance Field Maps:
```
	name: { signature, fieldID }
	name: { signature, fieldID }
```
