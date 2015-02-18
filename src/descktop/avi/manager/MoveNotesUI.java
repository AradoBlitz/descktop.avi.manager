package descktop.avi.manager;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		MovieStorage.STORAGE.put("etc", "./etc/");
		MovieStorage.STORAGE.put("etc2", "./etc/");

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
}