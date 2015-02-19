package descktop.avi.manager;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MoveNotesUI {

	public static void main(String args[]){
		JFrame frame = new JFrame("Movie Notes");				
		JPanel buttonPanel = new JPanel();
		JDialog addFolderDialog = new JDialog(frame);
		JButton addFolderButton = new JButton("Add Folder");
		MovieManager movieManager = new MovieManager();
		int i = 1;
		MovieStorage storage = new MovieStorage(new String[][]{{"etc", "./etc/","CIMG2197.MOV"},{"etc2", "./etc/","CIMG2220.MOV"}});
		Object[][] dataForTable = movieManager.prepare(storage.getData());
		DefaultTableModel dataModel = movieManager.fillDataModel(
			createTableModel(new String[]{"№","Folder","Files"})
			, dataForTable);
	
		
		addFolderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel label = new JLabel("Dialog");
				addFolderDialog.setSize(400, 100);
				addFolderDialog.getContentPane().add(label);
				JPanel submitPanel = new JPanel();
				addFolderDialog.getContentPane().add(submitPanel,BorderLayout.SOUTH);
				JButton submit = new JButton("Submit");
				submit.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						addFolderDialog.setVisible(false);
						int i = 3;
						//movieManager.fillDataModel(dataModel, new Object[][]{{null, "etc3","CIMG2220.MOV"}});
						movieManager.fillDataModel(dataModel,movieManager.prepare(storage.add("etc3","./etc/","CIMG2220.MOV")));
						
					}
				});
				submitPanel.add(submit);
				addFolderDialog.setVisible(true);
				
			}
		});
		buttonPanel.add(addFolderButton);

		frame.getContentPane().add(buttonPanel,BorderLayout.NORTH);
		
		JTable mediaFiles = new JTable();
		

		mediaFiles.setModel(dataModel);
		mediaFiles.createDefaultColumnsFromModel();
		mediaFiles.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("mousePressed" + e.getClickCount());
				if(e.getClickCount() == 2){
				try {
					JTable table  = (JTable)e.getSource();
					Point p = e.getPoint();
					int rowAtPoint = table.rowAtPoint(p);
					System.out.println("Clicked row: " + rowAtPoint);
					System.out.println("File name:" + table.getValueAt(rowAtPoint, 2));
					String movieFile = MovieStorage.STORAGE.get(table.getValueAt(rowAtPoint, 1)) + table.getValueAt(rowAtPoint, 2);
					if(movieFile == null)
						throw new RuntimeException("Movie file is not provided.");
					Runtime.getRuntime().exec("totem " + movieFile);
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		
		JScrollPane comp = new JScrollPane(mediaFiles);

		frame.getContentPane().add(comp,BorderLayout.CENTER);
				
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static DefaultTableModel createTableModel() {
		int i = 1;
		return fillDataModel(
				createTableModel(new String[]{"№","Folder","Files"})
				, new Object[][]{
				{i++,"etc","CIMG2197.MOV"}
				,{i++,"etc2","CIMG2220.MOV"}
			});	
	}

	public static DefaultTableModel fillDataModel(DefaultTableModel dataModel,
			Object[][] rows) {
		dataModel.addRow(rows[0]);
		dataModel.addRow(rows[1]);
		return dataModel;
	}

	private static DefaultTableModel createTableModel(String[] columns) {
		DefaultTableModel dataModel = new DefaultTableModel();
		for(int c = 0;c<columns.length;c++)
			dataModel.addColumn(columns[c]);
		return dataModel;
	}
}