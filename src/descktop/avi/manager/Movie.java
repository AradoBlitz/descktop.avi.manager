package descktop.avi.manager;

import javax.swing.table.DefaultTableModel;

public class Movie {

    private int i = 1;

    public DefaultTableModel initTableModel(String[][] strings, DefaultTableModel createTableModel) {
		
		add(strings);
		Object[][] dataForTable = prepare(strings);
		fillDataModel(createTableModel, dataForTable);	
		return createTableModel;
	} 
	private Object[][] prepare(Object[][] data) {
		
		Object[][] objects = new Object[data.length][3];
		for(int r = 0;r < data.length;r++)
			objects[r] = new Object[] {i++,data[r][0],data[r][2]};
		return objects;
	}
	private DefaultTableModel fillDataModel(DefaultTableModel dataModel, Object[][] rows) {
		for(int r = 0;r<rows.length;r++){

			dataModel.addRow(rows[r]);
		}

		return dataModel;
	}
	public void add(DefaultTableModel dataModel, String text, String text2, String name) {
		Object[] add = new Object[]{ text, text2, name};
		add(new Object[][]{add});
		fillDataModel(dataModel,prepare(new Object[][]{add}));
		
	}
	
	public void add(Object[][] storageData) {
		for(int i = 0;i<storageData.length;i++)
			MovieStorage.STORAGE.put((String)storageData[i][0], (String)storageData[i][1]);
		
	}
	
	public String getPathByAlias(Object valueAt) {
		// TODO Auto-generated method stub
		return MovieStorage.STORAGE.get(valueAt);
	}

}
