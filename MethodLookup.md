## Example Invocation (Class Map Lookup)

The syntax for method, field, constructor invocation is the same regardless of whether the fields and methods were explicitly declared or loaded using reflection.

```
function: MyClass = (
    | obj |
    obj:: MyClass new: {arg1. arg2}.
    obj call: 'method' args: {arg1. arg2}.
    obj set: 'field' to: arg1.
    MyClass call: 'staticMethod'.
)
```

## Lookup Rules

1. Method must be loaded into the class map beforehand.

2. Type checking is mandatory to prevent segfaults, stack issues, and undefined operation. It verifies all non-JavaAliens can be coerced to matching arguments, every JavaObject is InstanceOf each argument class, and the number of arguments matches.

3. Type inference follows Java's rules, but is based on the data available in the map. The inferred method may be incorrect per Java if the map is not completely loaded - user's responsibility.

4. To overcome an undersireable inference, exactly typed arguments can be used, as is the case in the Java language.

5. Another way to avoid inference is to directly invoke the JavaMethods returned by the explicit method loaders (below). This construct does not exist in the Java langauge. Arguments are still verified.

## Explicit Class Map Loading

```
| MyClass | 

MyClass:: JavaClass find: 'com/me/MyClass'.

MyClass constructor sig: '()V'.
MyClass static method: 'method' sig: '(II)Z'.
MyClass instance field: 'field' sig: 'Ljava/util/ArrayList;'.
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
name: { return_signature, [ { arg_signature, methodID} ] }
		  	    { arg_signature, methodID}
			    ...
name: {	return_Signature, [ { arg_signature, methodID} ] }
			    { arg_signature, methodID} 
			    ...
...
```

Static/Instance Field Maps

```
name: { signature, fieldID }
name: { signature, fieldID }
...
```
