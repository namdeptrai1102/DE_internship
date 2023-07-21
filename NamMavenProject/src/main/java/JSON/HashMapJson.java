package JSON;
import java.util.*;

import org.json.JSONObject;


public class HashMapJson {

	@SuppressWarnings({ "unchecked", "removal" })
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		// Convert a map having list of values.
		String[] value1 = new String[] { "value1.1", "value1.2", "value1.3" };
		String[] value2 = new String[] { "value2.1", "value2.2", "value2.3" };
		map.put("key1", value1);
		map.put("key2", value2);

		JSONObject json = new JSONObject(map);
		System.out.println(json);
	}

}
