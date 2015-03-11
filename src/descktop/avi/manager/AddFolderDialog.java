package descktop.avi.manager;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public abstract class AddFolderDialog {	

	public abstract void submit(Movie movie, DefaultTableModel dataModel);
	
	public abstract JPanel createDialogPanel(ActionListener actionListener);
	
	public void submitSelectedDirectory(Movie movie,
			DefaultTableModel dataModel, File movieDir,
			String alias, String absolutePath) {
		for(File movieFile : movieDir.listFiles()){			
			movie.add(dataModel
					,alias
					,absolutePath + "/"
						,movieFile.getName());
		}
	}
}
