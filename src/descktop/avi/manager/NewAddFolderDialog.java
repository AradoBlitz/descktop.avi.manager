package descktop.avi.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NewAddFolderDialog extends AddFolderDialog{

	
	private JFileChooser pathToMediaDir;
	private JTextField aliasValue;
	private JTextField pathValue;

	public NewAddFolderDialog() {
				
	}

	

	public void submit(Movie movie, DefaultTableModel dataModel) {
		
		File movieDir = new File(pathValue.getText());
		String alias = aliasValue.getText();
		String absolutePath = movieDir.getAbsolutePath();
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
		JLabel labelPath = new JLabel("Path");
		pathValue = new JTextField(26);
		JButton selectPath = new JButton("Select Path");
		selectPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pathToMediaDir = new JFileChooser();
				pathToMediaDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int res = pathToMediaDir.showOpenDialog(submitPanel);
				if(JFileChooser.APPROVE_OPTION == res)
					pathValue.setText(pathToMediaDir.getSelectedFile().getAbsolutePath());
			}
		});
					
		
		
		submitPanel.add(labelPath);
		submitPanel.add(pathValue);
		submitPanel.add(selectPath);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(actionListener);
		submitPanel.add(submit);
		return submitPanel;
	}
	
}
