# JavaAliens
Access to the Java Runtime in Newspeak via the Aliens FFI and Java Native Interface (JNI)

**Usage:**
1. Have Java.
2. Have in PATH env. var: `JRE_DIR\bin` & `JRE_DIR\bin\client`. Otherwise it'll crash. Also, it will probably crash anyway.
3. Compile \*.java and place class files in directory `bin` next to the Image. The classpath used by tests is `.\bin`

**Note:** Currently NSVM crashes if you provide a bad Java classpath. Not sure yet how to trap this condition.
