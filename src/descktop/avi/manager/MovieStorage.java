package descktop.avi.manager;

import java.util.HashMap;
import java.util.Map;

public class MovieStorage {

	protected static final Map<String,String> STORAGE = new HashMap<String,String>();
	private String[][] storageData;
	

	
	public MovieStorage(String[][] storageData) {
		this.storageData = storageData;
		for(int i = 0;i<storageData.length;i++)
			MovieStorage.STORAGE.put(storageData[i][0], storageData[i][1]);
	}


	public Object[][] getData() {
		
		return storageData;
	}


	public void add(Object[][] objects) {
		// TODO Auto-generated method stub
		
	}


	public Object[][] add(String string, String string2, String string3) {
		MovieStorage.STORAGE.put("etc3", "./etc/");
		return new Object[][]{{string,string2,string3}};
	}	
	
}
