package descktop.avi.manager;

import java.util.HashMap;
import java.util.Map;

public class MovieStorage {

	protected static final Map<String,String> STORAGE = new HashMap<String,String>();
	
	
	public MovieStorage(String[][] storageData) {
		for(int i = 0;i<storageData.length;i++)
			MovieStorage.STORAGE.put(storageData[i][0], storageData[i][1]);
	}	
	
}
