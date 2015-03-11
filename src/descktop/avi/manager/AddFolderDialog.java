package descktop.avi.manager;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public abstract class AddFolderDialog {	

	public abstract void submit(Movie movie, DefaultTableModel dataModel);
	
	public abstract JPanel createDialogPanel(ActionListener actionListener);
	
}
