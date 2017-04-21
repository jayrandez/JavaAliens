import java.util.*;

import javax.swing.JOptionPane;

public class JavaStaticTest
{
	public static Object objectField = new ArrayList<Object>();
	public static boolean booleanField = true;
	public static byte byteField = 2;
	public static char charField = 'A';
	public static short shortField = 3;
	public static int intField = 7;
	public static long longField = 5;
	public static float floatField = 5.5f;
	public static double doubleField = 5.75;

	public static void setAll(Object anObject, boolean aBoolean, byte aByte, char aChar,
			short aShort, int anInt, long aLong, float aFloat, double aDouble) {
		booleanField = aBoolean;
		byteField = aByte;
		charField = aChar;
		shortField = aShort;
		intField = anInt;
		longField = aLong;
		floatField = aFloat;
		doubleField = aDouble;
		String message = "" + aBoolean +  " " + aByte +  " " + aChar +  " " + aShort +  " " + anInt +  " " + aLong +  " " + aFloat +  " " + aDouble;
		JOptionPane.showMessageDialog(null, message);
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