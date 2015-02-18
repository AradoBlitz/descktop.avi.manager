package descktop.avi.manager;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MoveNotesUI {

	public static void main(String args[]){
		JFrame frame = new JFrame("Movie Notes");				
		JPanel buttonPanel = new JPanel();
		JButton addFolderButton = new JButton("Add Folder");
		buttonPanel.add(addFolderButton);

		frame.getContentPane().add(buttonPanel,BorderLayout.NORTH);
		
		JTable mediaFiles = new JTable();
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.addColumn("№");
		dataModel.addColumn("Folder");
		dataModel.addColumn("Files");
		int i = 1;
		dataModel.addRow(new Object[]{i++,"etc","CIMG2197.MOV"});
		dataModel.addRow(new Object[]{i++,"etc2","CIMG2220.MOV"});

		mediaFiles.setModel(dataModel);		//frame.pack();
		mediaFiles.createDefaultColumnsFromModel();

		JScrollPane comp = new JScrollPane(mediaFiles);
		//frame.pack();
		frame.getContentPane().add(comp,BorderLayout.CENTER);
				
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}