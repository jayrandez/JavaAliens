
public class TestObjectArray
{
	// Put value, get value.
	public static Object[] objectArray = new Object[2];
	
	// Put range of values, get range of values.
	public static Integer[] integerObjectArray = new Integer[10];
	
	// Get the type, length, and casted value.
	public static String[] specialTypeArray = new String[] {"1", "2", "3", "4", "5"};
		
	// Create array.
	public static String[] uninitializedArray;
	
	public static boolean checkValuesNull() {
		for(int i = 0; i < 5; i++)
			if(uninitializedArray[i] != null)
				return false;
		return true;
	}
	
	public static boolean checkValuesInOrder() {
		for(int i = 0; i < 5; i++)
			if(Integer.parseInt(uninitializedArray[i]) != i)
				return false;
		return true;
	}
	
	public static boolean checkValuesSame() {
		for(int i = 0; i < 5; i++)
			if(!uninitializedArray[i].equals(" :) "))
				return false;
		return true;
	}
}
