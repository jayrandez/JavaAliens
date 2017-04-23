
public class TestInstance
{
	public Object objectField;
	public boolean booleanField;
	public byte byteField;
	public char charField;
	public short shortField;
	public int intField;
	public long longField;
	public float floatField;
	public double doubleField;
	
	public TestInstance() {}

	public void setAll(Object anObject, boolean aBoolean, byte aByte, char aChar,
			short aShort, int anInt, long aLong/*, float aFloat, double aDouble*/) {
		objectField = anObject;
		booleanField = aBoolean;
		byteField = aByte;
		charField = aChar;
		shortField = aShort;
		intField = anInt;
		longField = aLong;
		/*floatField = aFloat;
		doubleField = aDouble;*/
	}
	
	public Object getObject() { return objectField; }
	public boolean getBoolean() { return booleanField; }
	public byte getByte() { return byteField; }
	public char getChar() { return charField; }
	public short getShort() { return shortField; }
	public int getInt() { return intField; }
	public long getLong() { return longField; }
	public float getFloat() { return floatField; }
	public double getDouble() { return doubleField; }
}