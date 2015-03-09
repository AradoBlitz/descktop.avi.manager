package descktop.avi.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddFolderDialog {

	
	private JFileChooser pathToMediaDir;
	private JTextField aliasValue;

	public AddFolderDialog() {
				
	}

	

	protected void submit(JDialog addFolderDialog, Movie movie, DefaultTableModel dataModel, String pathToMovieDb) {
		addFolderDialog.setVisible(false);
		File movieDir = pathToMediaDir.getSelectedFile();
		
		for(File movieFile : movieDir.listFiles()){
			movie.add(pathToMovieDb
					,dataModel
					,aliasValue.getText()
					,pathToMediaDir
						.getSelectedFile()
						.getAbsolutePath() + "/"
						,movieFile.getName());
		}	
		aliasValue.setText(null);
		
	}

	public void createDialog(JDialog addFolderDialog, ActionListener actionListener) {
		addFolderDialog.setSize(600, 400);
		 
		JPanel submitPanel = new JPanel();
		addFolderDialog.getContentPane().add(submitPanel);
		
		JLabel labelAlias = new JLabel("Alias");
		aliasValue = new JTextField(26);
		submitPanel.add(labelAlias);
		submitPanel.add(aliasValue);
					
		pathToMediaDir = new JFileChooser();
		pathToMediaDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		submitPanel.add(pathToMediaDir);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(actionListener);
		submitPanel.add(submit);
		addFolderDialog.setVisible(true);
		
	}

	
}
