package descktop.avi.manager;

import javax.swing.table.DefaultTableModel;

public class MovieManager {

	
	private DefaultTableModel fillDataModel(DefaultTableModel dataModel,
			Object[][] rows) {
		for(int r = 0;r<rows.length;r++){

			dataModel.addRow(rows[r]);
		}

		return dataModel;
	}

}
