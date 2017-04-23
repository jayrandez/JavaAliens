# JavaAliens
Access the Java Runtime in Newspeak through the Aliens FFI and Java Native Interface (JNI)

**Usage:**
1. Have Java.
2. Have in PATH your particular `JRE_DIR\bin` & `JRE_DIR\bin\client`.
3. Compile \*.java into directory `bin` next to the Image (or compile.bat).
4. `[Run]` JavaTests in package JavaAliens.

The default classpath used by tests is `.\bin`

## Inference Rules
Only JavaAliens are passed to the core JNI library, however inferences can be made. The first choice is the inference made if the Java methods are ambiguous, e.g. an "isInteger" type is provided, but Java provides `void call(short a)` and `void call(int a)`.

| If the Newspeak Object answers: |	It can be inferred as a JavaAlien: |
| - | - |
| isInteger	| JavaInt (int), JavaShort (short), JavaLong (long), JavaByte (byte) |
| isFloat | JavaFloat (float), JavaDouble (double) |
| isCharacter | JavaChar (char) |
| isString | JavaString (String) |
| isBoolean | JavaBoolean (boolean) |
