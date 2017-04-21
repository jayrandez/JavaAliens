import java.util.*;

import javax.swing.JOptionPane;

public class JavaStaticTest
{
	public static Object objectField;
	public static boolean booleanField;
	public static byte byteField;
	public static char charField;
	public static short shortField;
	public static int intField;
	public static long longField;
	public static float floatField;
	public static double doubleField;

	public static void setAll(Object anObject, boolean aBoolean, byte aByte, char aChar,
			short aShort, int anInt, long aLong, float aFloat, double aDouble) {
		objectField = anObject;
		booleanField = aBoolean;
		byteField = aByte;
		charField = aChar;
		shortField = aShort;
		intField = anInt;
		longField = aLong;
		floatField = aFloat;
		doubleField = aDouble;
	}
	
	public static Object getObject() { return objectField; }
	public static boolean getBoolean() { return booleanField; }
	public static byte getByte() { return byteField; }
	public static char getChar() { return charField; }
	public static short getShort() { return shortField; }
	public static int getInt() { return intField; }
	public static long getLong() { return longField; }
	public static float getFloat() { return floatField; }
	public static double getDouble() { return doubleField; }
}