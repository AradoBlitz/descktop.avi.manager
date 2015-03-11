package descktop.avi.manager;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddFolderEmbeddedFileChooser  extends AddFolderDialog{
	private JFileChooser pathToMediaDir;
	private JTextField aliasValue;

	public void submit(Movie movie, DefaultTableModel dataModel) {
		
		File movieDir = pathToMediaDir.getSelectedFile();
		String alias = aliasValue.getText();
		String absolutePath = pathToMediaDir
			.getSelectedFile()
			.getAbsolutePath();
		submitSelectedDirectory(movie, dataModel, movieDir,
				alias, absolutePath);	
		aliasValue.setText(null);
		
	}

	public JPanel createDialogPanel(ActionListener actionListener) {
		JPanel submitPanel = new JPanel();		
		
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
		return submitPanel;
	}	
}
