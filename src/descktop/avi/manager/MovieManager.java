package descktop.avi.manager;

import javax.swing.table.DefaultTableModel;

public class MovieManager {

	int i = 1;
	
	public DefaultTableModel fillDataModel(DefaultTableModel dataModel,
			Object[][] rows) {
		for(int r = 0;r<rows.length;r++){
			//rows[r][0] = i++;
			dataModel.addRow(rows[r]);
		}

		return dataModel;
	}

	public Object[][] prepare(Object[][] data) {
		Object[][] objects = new Object[data.length][3];
		for(int r = 0;r<data.length;r++)
			objects[r] = new Object[] {i++,data[r][0],data[r][2]};
		return objects;
	}

	public void addMovies(DefaultTableModel dataModel, Object[][] add) {
		fillDataModel(dataModel,prepare(add));
		
	}

}
