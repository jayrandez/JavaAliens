# JavaAliens
Directly call the Java Runtime in Newspeak through the Aliens FFI and Java Native Interface (JNI)

**Usage:**
1. Have Java.
2. Have in PATH your particular `JRE_DIR\bin` & `JRE_DIR\bin\client`.
3. Compile \*.java into directory `bin` next to the Image (or compile.bat).
4. `[Run]` JavaTests in package JavaAliens.

The default classpath used by tests is `.\bin`

## Inference Rules
Inferences can be made to cast arguments to JavaAlien types. The first option is chosen if the Java methods are ambiguous, e.g. an "isInteger" type is provided, but Java defines `void call(short a)` and `void call(int a)`. Explicit JavaAlien types can be passed to resolve ambiguities.

| If the Newspeak Object answers: |	It can be inferred as a JavaAlien: |
| - | - |
| isInteger	| JavaInt (int), JavaShort (short), JavaLong (long), JavaByte (byte) |
| isFraction, isFloat, isNumber | JavaFloat (float), JavaDouble (double) |
| isCharacter | JavaChar (char) |
| isString | JavaString (String) |
| isBoolean | JavaBoolean (boolean) |
