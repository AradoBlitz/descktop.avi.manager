package descktop.avi.manager;

import static org.junit.Assert.*;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

public class AddFolderDialogTest {

	@Test
	public void collectFoldersPathAndAlias() throws Exception {
		JFrame testFrame = new JFrame("Test Frame");
		
		DefaultTableModel model = new DefaultTableModel();
		Movie movie = new Movie();
		AddFolderDialog addFolderDialog = new AddFolderDialog();
		JPanel createDialogPanel = addFolderDialog.createDialogPanel(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addFolderDialog.submit(movie, model);
				assertEquals(model.getValueAt(0, 1), "Test alias");
				assertEquals(model.getValueAt(0, 2), "TCIMG2197.MOV");
				assertEquals(movie.getPathByAlias("Test alias"), "./etc");
				testFrame.setVisible(false);
				
			}
		});
		testFrame.getContentPane().add(createDialogPanel);
		testFrame.setSize(300, 300);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setVisible(true);
		Thread.sleep(1000);
	}
}
