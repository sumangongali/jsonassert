package org.skyscreamer.jsonassert.comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

public class MaskComparator extends DefaultComparator {

	public MaskComparator(JSONCompareMode mode) {
		super(mode);
		// TODO Auto-generated constructor stub
	}

	public void compareValues(String prefix, Object expectedValue,
			Object actualValue, JSONCompareResult result, String maskWord, String splitChars) throws JSONException {
		if ( (expectedValue instanceof String && ((String) expectedValue).contains(maskWord))) {
			
			String splitWords = "[" + splitChars + "]+";
			String [] expectedWords= ((String)expectedValue).split(splitWords);
			String [] actualWords= ((String)actualValue).split(splitWords);
			if ( (expectedWords.length == 1 && expectedWords[0].compareTo(maskWord)!=0) && expectedWords.length != actualWords.length) {
				
                System.out.println("Length not matching.");
                result.fail(prefix, expectedValue, actualValue);
                return;
            }

            for (int i = 0; i < expectedWords.length; i++) {
                //System.out.println("Compare "+s1[i]+ " and "+ s2[i]);
                if (expectedWords[i].contains(maskWord) || expectedWords[i].compareTo(actualWords[i])==0) {
                    continue;
                }
                result.fail(prefix, expectedValue, actualValue);
                return;
            }

		}
		else if (expectedValue instanceof Number
					&& actualValue instanceof Number) {
			if(((Number)expectedValue).doubleValue()!= Double.MAX_VALUE){
				if (((Number) expectedValue).doubleValue() != ((Number) actualValue)
						.doubleValue()) {
					result.fail(prefix, expectedValue, actualValue);
				}
				}
			} else if (expectedValue.getClass().isAssignableFrom(
					actualValue.getClass())) {
				if (expectedValue instanceof JSONArray) {
					compareJSONArray(prefix, (JSONArray) expectedValue,
							(JSONArray) actualValue, result,maskWord,splitChars);
				} else if (expectedValue instanceof JSONObject) {
					compareJSON(prefix, (JSONObject) expectedValue,
							(JSONObject) actualValue, result,maskWord,splitChars);
				} else if (!expectedValue.equals(actualValue)) {
					result.fail(prefix, expectedValue, actualValue);
				}
			} else {
				result.fail(prefix, expectedValue, actualValue);
			}
		}

}
