## Example Invocation

The syntax for method, field, constructor invocation is the same regardless of whether the field and method signatures are provided by the user or loaded through reflection.

```
public useClass: MyClass explicitMethod: aMethod = (
    | obj fieldVal |
    
    obj:: MyClass new: {arg1. arg2}.
    
    obj call: 'methodName' args: {arg1. arg2}.	   (* Name call, type inference if method name is ambiguous *)
    obj call: aMethod args: {arg1. arg2}.	   (* Explicit call, type validated but not inferred *)
    obj set: 'fieldName' to: arg1.
    
    fieldVal:: MyClass get: 'staticFieldName'.
    obj fieldName: fieldVal.			   (* Forwarded to obj set: 'fieldName' to: fieldVal. *)
)
```

## Lookup Rules

1. Method must be found beforehand, either through explicit or reflective class loading.

2. Type checking is mandatory to prevent segfaults, stack issues, and undefined operation. It verifies all non-JavaAliens can be coerced to matching arguments, every JavaObject is InstanceOf each argument class, and the number of arguments matches.

3. Name calls use type inference as needed following Java's rules, based on the data available in the map. The inferred method may be incorrect per Java if the map is not completely loaded - user's responsibility.

5. Explicit calls using JavaMethod objects do not use inference. JavaMethods are returned by the explicit method loaders (below) and require a signature. Arguments will still be verified.

## Explicit Class Map Loading

```
| MyClass aMethod | 

MyClass:: JavaClass find: 'com/me/MyClass'.

MyClass constructor: '()V'.
aMethod:: MyClass method: 'methodName' sig: '(II)Z'.
MyClass staticField: 'fieldName' sig: 'Ljava/util/ArrayList;'.
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
