## Example Invocation

The syntax for method, field, constructor invocation is the same regardless of whether the fields and methods were explicitly declared or loaded using reflection.

```
useClass: MyClass declaredMethod: aMethod = (
    | obj |
    obj:: MyClass new: {arg1. arg2}.
    obj call: 'method' args: {arg1. arg2}.		Map lookup by name, type inference if name is ambiguous
    obj call: aMethod args: {arg1. arg2}.		Directly call a declared method, type validated but not inferred
    obj set: 'field' to: arg1.
    MyClass get: 'staticField'.
)
```

## Lookup Rules

1. Method must be found beforehand through either explicit or reflective class loading.

2. Type checking is mandatory to prevent segfaults, stack issues, and undefined operation. It verifies all non-JavaAliens can be coerced to matching arguments, every JavaObject is InstanceOf each argument class, and the number of arguments matches.

3. Call by name uses type inference following Java's rules, but is based on the data available in the map. The inferred method may be incorrect per Java if the map is not completely loaded - user's responsibility.

4. To overcome an undersireable inference, exactly typed arguments can be used, as is the case in the Java language.

5. Inference is not used when directly calling a method object, as returned by the explicit method loaders (below). Arguments will still be verified.

## Explicit Class Map Loading

```
| MyClass aMethod | 

MyClass:: JavaClass find: 'com/me/MyClass'.

MyClass constructor: '()V'.
MyClass staticField: 'field' sig: 'Ljava/util/ArrayList;'.
aMethod:: MyClass method: 'method' sig: '(II)Z'.

```

## Reflective Class Map Loading

```
| MyClass | 

MyClass:: JavaClass find: 'com/me/MyClass'.
MyClass load.
```

or simply,

```
| MyClass |

MyClass:: (JavaClass find: 'com/me/MyClass') load.
```

## Class Map Construction

Constructor List

```
[ { arg_signature, methodID } ]
  { arg_signature, methodID }
  ...
```

Static/Instance Method Maps

```
name: { return_signature, [ { arg_signature, methodID } ] }
		  	    { arg_signature, methodID }
			    ...
name: {	return_Signature, [ { arg_signature, methodID } ] }
			    { arg_signature, methodID } 
			    ...
...
```

Static/Instance Field Maps

```
name: { signature, fieldID }
name: { signature, fieldID }
...
```
