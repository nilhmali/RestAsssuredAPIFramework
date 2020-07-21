package Utility;

import org.json.JSONArray;
import org.json.JSONObject;

public class Readjsondata {

	public static JSONObject responsejson;

	// This method use for read json data(Json Parcing)
	public static String getvalueByJpath(JSONObject responsejson, String jpath) {
		Object obj = responsejson;

		for (String s1 : jpath.split("/"))
			if (!s1.isEmpty())
				if (!(s1.contains("[") || s1.contains("]")))
					obj = ((JSONObject) obj).get(s1);
				else if ((s1.contains("[") || s1.contains("]")))
					obj = ((JSONArray) ((JSONObject) obj).get(s1.split("\\[")[0]))
							.get(Integer.parseInt(s1.split("\\[")[1].replace("]", "")));

		return obj.toString();

	}

}