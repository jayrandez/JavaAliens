# JavaAliens
Directly call the Java Runtime in Newspeak through the Aliens FFI and Java Native Interface (JNI)

## Overview

JavaAliens instantiates a Java runtime using the JNI (Java Native Interface) library. This allows Newspeak to perform any action that Java code could perform from within a static method. You can instantiate and manipulate Java objects and call methods on them. It's not possible to define Java classes, or write Newspeak code that Java can directly call.

**Usage**
1. Have Java.
2. Have in PATH your particular `JRE_DIR\bin` & `JRE_DIR\bin\client`.
3. Compile \*.java into directory `bin` next to the Image (or compile.bat).
4. `[Run]` JavaTests in package JavaAliens.

The default classpath used by tests is `.\bin`

**Implicit and Explicit Lookup**

Blahblahblahblah

**Sugary Calls**

Blahbalbhalbhalbah

**Simple Types**

References to the types of Java primitives can be obtained as such:
- `java boolean` - returns boolean primitive type
- `java float` - returns float primitive type

etc.

Additionally `java String` and `java Object` are also provided for convenience.

Primitives of a specific type can be instantiated easily
- `java double: 3.14`
- `java long: 50124450294800204`

etc.

As a convenience, standard Newspeak integers, floats, bools, and strings are interpreted as the corresponding Java types automatically. 
```
3       (integral number)      int
true    (boolean value)        boolean
3.14    (non-integral number)  double
'text'  (string/char types)    java.lang.String
nil     (undefined)            null
```

## Examples

```
public helloWorld = (
    System:: runtime class: 'java.lang.System'.
    System out println: 'Hello, world.'.
)
```
```
public helloWindow = (
    swing:: runtime package: 'javax.swing'.

    frame:: swing JFrame new.
    frame add: [swing JLabel new: 'Hello, World!'].

    frame pack.
    frame setLocationRelativeTo: nil.
    frame setVisible: true.
)
```

## Class Meta Operations

**Obtain a class by fully qualified name**
```
ClassName:: runtime class: 'com.package.ClassName'
        or,
ClassName:: runtime class: 'com/package/ClassName'
```
**Obtain a package**
```
package:: runtime package: 'com.package'
        or,
package:: runtime package: 'com/package'
```
**Obtain a class within a package**
```
ClassName:: package class: 'ClassName'
```
**Obtain a class within a package (sugary)**
```
ClassName:: package ClassName
```
**Obtain a nested class**
```
NestedClassName:: ClassName class: NestedClassName
```
**Get the superclass**
```
SuperClass:: ClassName super.
```

## Object Meta Operations

**Create new object, empty constructor**
```
object:: ClassName new.
```
**Create new object, implicit constructor**
```
object:: ClassName new: [instA. instB].
```
**Create new object, explicit obtained constructor**
```
constructor:: ClassName constructor: [ClassNameA. ClassNameB].
object:: constructor call: [instA. instB].
        or,
object:: ClassName new: constructor with: [instA. instB].
```
**Getting the real class of an object**
```
RealClass:: object class.
```
**Get the superclass of an object**
```
SuperClass:: object class super.
```
**Getting the type of an object**
```
RealSuperOrInterface:: object type.
```
**Casting the object to another type (must be compatible)**
```
sameObjectDifferentType:: object as: RealSuperOrInterface.
```

## Instance Calls

**Set field on object**
```
object set: 'fieldName' to: newVal.
```
**Set field on object (sugary)**
```
object fieldName: newVal.
```
**Get field from object**
```
fieldVal:: object get: 'fieldName'.
```
**Get field from object (sugary)**
```
fieldVal:: object fieldName.
```
**Call argument-less method on object**
```
object call: 'myMethod'.
```
**Call argument-less method on object (sugary)**
```
object myMethod
```
**Call method with args on object (explicit)**
```
method:: object method: 'myMethod' with: [ClassNameA. ClassNameB].
method call: [instA. instB].
        or,
object call: method with: [instA. instB].
```
**Call method with args on object (implicit)**
```
object call: 'myMethod' with: [instA. instB].
```
**Call method with args on object (implicit, sugary)**
```
object myMethod: [instA. instB].
```
**Getting the class of an object**
```
RealClass:: object class.
```
**Getting the type of an object**
```
RealSuperOrInterface:: object type.
```
**Casting the object to another type**
```
sameObjectDifferentType:: object as: RealSuperOrInterface.
```

## Static Calls

**Call argument-less static method on class**
```
ClassName call: 'theMethod'.
```
**Call argument-less static method on class (sugary)**
```
ClassName theMethod.
```
**Call static method with args on class (explicit)**
```
staticMethod:: ClassName method: 'theMethod' with: [ClassNameA. ClassNameB].
staticMethod call: [instA. instB].
        or,
ClassName call: staticMethod with: [instA. instB].
```
**Call static method with args on class (implicit)**
```
ClassName call: 'staticMethod' with: [instA. instB].
```
**Call static method with args on class (implicit, sugary)**
```
ClassName staticMethod: [instA. instB].
```
**Set static field on class**
```
ClassName set: 'fieldName' to: newVal.
```
**Set static field on class (sugary)**
```
ClassName fieldName: newVal.
```
**Get static field from class**
```
fieldVal:: ClassName get: 'fieldName'.
```
**Get field from object (sugary)**
```
fieldVal:: ClassName fieldName.
```

## Runtime Operations

**Creating the JavaAliens API (Provide classpath as NS files).**
```
java:: JavaAliens usingPlatform: platform classPath: [fileA. dirA. dirB].
runtime:: java runtime.
```
**Reloading the runtime (if classpath contents changes).**
```
runtime reload.
```
**Catching exceptions (no built-in exception type discrimination)**
```
runtime try: [
    object methodName: [argA. argB].
    (* More java calls can follow... *)
]
catch: [:ex |
    (* Exception handler code here. *)
].
```
**Acquiring a monitor**
```
runtime synchronized: [
    object methodName: [argA. argB].
    (* More java calls can follow... *)
]
on: [monitorObj].
```
