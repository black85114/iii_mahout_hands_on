package mahout.exercise;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

public class Exercise2 {

	/*
	 * Create DataModel that can store multiple user's preferences
	 */
	public static void main(String[] args) throws Exception{
		
		FastByIDMap<PreferenceArray> prefs = new FastByIDMap<PreferenceArray>(); 
		
		PreferenceArray pf1 = new GenericUserPreferenceArray(10);
		
		pf1.setUserID(0, 1L);

		pf1.setItemID(0, 101L);
		pf1.setValue(0, 2.0f);

		pf1.setItemID(1, 102L);
		pf1.setValue(1, 3.0f);
		
		prefs.put(1L, pf1);
		
		
		PreferenceArray pf2 = new GenericUserPreferenceArray(2);
		
		pf2.setUserID(0, 2L);
		
		pf2.setItemID(0, 101L);
		pf2.setValue(0, 1.0f);
		
		pf2.setItemID(1, 102L);
		pf2.setValue(1, 5.0f);
		
		prefs.put(2L, pf2);
		
		DataModel dm = new GenericDataModel(prefs);

		System.out.println(dm);

	}
}
