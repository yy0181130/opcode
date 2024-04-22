package util;

import java.util.HashMap;

public class NoChangeMap extends HashMap{
	
	@Override
	public Object get(Object key) {
		
		Object obj=super.get(key);
		
		return super.get(key);
	}

}
