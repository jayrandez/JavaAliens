
public class TestObjectArray
{
	// Put value, get value.
	public static Object[] objectArray = new Object[2];
	
	// Put range of values, get range of values.
	public static Integer[] integerObjectArray = new Integer[10];
	
	// Get the type.
	public static Integer[] specialTypeArray = new Integer[] {5, 4, 3, 2, 1};
		
	// Create array and read length.
	public static String[] uninitializedArray;
	
	public static boolean checkValues() {
		for(int i = 0; i < 5; i++)
			if(Integer.parseInt(uninitializedArray[i]) != i)
				return false;
		return true;
	}
}
