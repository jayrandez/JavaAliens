## Example Invocation

The syntax for method, field, constructor invocation is the same regardless of whether the field and method signatures are manually provided by the user or loaded through reflection.

```
public useClass: MyClass explicitMethod: aMethod = (
    | obj fieldVal |
    
    obj:: MyClass new: {arg1. arg2}.
    
    obj call: 'methodName' args: {arg1. arg2}.         (* Name call, type inference if method name is ambiguous *)
    obj call: aMethod args: {arg1. arg2}.              (* Explicit call, type validated but not inferred *)
    obj set: 'fieldName' to: arg1.
    
    fieldVal:: MyClass get: 'staticFieldName'.
)
```

## Lookup Rules

1. Method must be found beforehand, either through manual or reflective class loading.

2. Type checking is mandatory to prevent segfaults, stack issues, and undefined operation. It verifies all non-JavaAliens can be coerced to matching arguments, every JavaObject is InstanceOf each argument class, and the number of arguments matches.

3. Name calls use type inference as needed following Java's rules, based on the data available in the map. The inferred method may be incorrect per Java if the map is not completely loaded - user's responsibility.

5. Explicit calls using JavaMethod objects do not use inference. JavaMethods are returned by the explicit method loaders (below) and require a signature. Arguments will still be verified.

## Manual Class Map Loading

```
| MyClass aMethod | 

MyClass:: JavaClass find: 'com/me/MyClass'.

MyClass constructor: '(Ljava/util/ArrayList;)V'.
aMethod:: MyClass method: 'methodName' sig: '(II)Z'.  (* Retain a JavaMethod to perform an explicit call. *)
MyClass staticField: 'fieldName' sig: 'J'.
```

## Reflective Class Map Loading

JavaAliens can use the Java "Trails" reflection API to load all method and field signatures from the class. The actual MethodIDs are obtained from JNI only if they are used, which transfers some overhead from class lookup to first-use of a method.

Naturally, if a method name has many type signatures, name calls will be lengthier than explicit calls due to the need to perform type inference at run time.

```
| MyClass |                                                | MyClass |

MyClass:: JavaClass find: 'com/me/MyClass'.                MyClass:: (JavaClass find: 'com/me/MyClass') load.
MyClass load.                                               
```

## Sugary Sends

Rather than retaining a method and calling it explicitly in two separate steps, you can call an explicit method by providing its name and signature at the same time. The following are equivalent:

```
aMethod:: MyClass method: 'methodName' sig: '(I)V'.
obj call: aMethod args: {1}.                               obj call: 'methodName' sig: '(I)V' args: {1}.
```

In both cases, the method and signature are manually loaded into the class map if not already present.

Rather than using `call:`, `call:args:`, `get:`, or `set:to:` with a method/field name, you can send a Newspeak-style message to the JavaClass or JavaObject. The following are equivalent:

```
MyClass call: 'doSomething' args: {1}.                     MyClass doSomething: 1.
fieldValue:: obj get: 'someField'.                         fieldValue:: obj someField.				
obj set: 'anotherField' to: (ArrayList new).               obj anotherField: (ArrayList new).			
obj call: 'myMethod' args: {'hello'. 'world'}.             obj myMethod: 'hello' et: 'world'.			
```

The `doesNotUnderstand` implementation uses the first selector component as a method/field name. The remaining selector component's names are discarded, only their arguments are used. Newspeak-style message sends always use name calling.
