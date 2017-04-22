# JavaAliens
Access the Java Runtime in Newspeak through the Aliens FFI and Java Native Interface (JNI)

**Usage:**
1. Have Java.
2. Have in PATH your particular `JRE_DIR\bin` & `JRE_DIR\bin\client`.
3. Compile \*.java into directory `bin` next to the Image (or compile.bat).
4. `[Run]` JavaTests in package JavaAliens.

The default classpath used by tests is `.\bin`

## Inference Rules
Only JavaAliens are passed to the core JNI library, however inferences can be made.

| If the Newspeak Object answers: |	It can be inferred as a JavaAlien: |
| - | - |
| isInteger	| JavaInt (int), JavaShort (short), JavaLong (long), JavaByte (byte) |
| isFloat | JavaFloat (float), JavaDouble (double) |
| isCharacter | JavaChar (char) |
| isString | JavaString (String) |
| isBoolean | JavaBoolean (boolean) |

## Method Lookup by Example

**8.** If an argument can not be inferred as any JavaAlien type the call can not complete.
```
obj call: 'method' args: {self class}.
```
**7.** If the arguments are complete ambiguous the call can not complete. E.g. Java provides:  `void method(int a) {}` and `void method(short a) {}`
```
obj call: 'method' args: {101}.
```
**6.** Arguments can be inferred multiple ways. Reflection will determine if the arguments can be inferred to match one (and-only-one) method. E.g. Java provides: `void method(int a, boolean b)` and `void method(String a, boolean b)`
```
obj call: 'method' args: {101. true}
```
**5.** Arguments can only be inferred one way. However, reflection must be used to get the return type.
```
obj call: 'method args: {true. 'Hello, world!'}
```
**4.** Arguments can only be inferred one way, and the return type is explicitly provided.
```
obj call: 'method args: {true. 'Hello, world!'} return: 'Z'.
```
**3.** All arguments are JavaAliens, so no inference is needed. However, reflection must be used to get the return type.
```
obj call: 'method' args: {Prim boolean: true. Prim int: 1}
```
**2.** All arguments are JavaAliens and the return type is explicitly provided.
```
obj call: 'method' args: {Prim boolean: true. Prim int: 1} return: 'V'.
```
**1.** A fully-qualified signature is provided by the user. All arguments will be cast to JavaAlien types, according to the above rules.
```
obj call: 'method' args: {101. true} sig: '(IZ)V'
```
