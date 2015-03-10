package descktop.avi.manager;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MoveNotesUI {

	public static void main(String args[]){
		String[][] loadedMovies = new String[][]{{"etc", "./etc/","CIMG2197.MOV"},{"etc2", "./etc/","CIMG2220.MOV"}};
		
		try {
			movieNotes(new FileMoviesDataBase().loadMelodies("./etc/movie.db.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}

	private static void movieNotes(String[][] loadedMovies) {
		JFrame frame = new JFrame("Movie Notes");				
		JPanel buttonPanel = new JPanel();
		JButton addFolderButton = new JButton("Add Folder");
		Movie movie = new Movie();
		
		
		
		DefaultTableModel dataModel = movie.initTableModel(loadedMovies,createTableModel(new String[]{"№","Folder","Files"}));

	
		String pathToMovieDb = "./etc/movie.db.txt";
		ActionListener listener = createAddFolderListener(new JDialog(frame), movie, dataModel,pathToMovieDb);
		addFolderButton.addActionListener(listener);
		buttonPanel.add(addFolderButton);

		frame.getContentPane().add(buttonPanel,BorderLayout.NORTH);
		
		JTable mediaFiles = new JTable(){

			@Override
			public String getToolTipText(MouseEvent event) {
				JTable table = (JTable)event.getSource();				
				int selectedRow = getRowNumber(event, (JTable)event.getSource());
				return movie.getPathByAlias(table.getModel().getValueAt(selectedRow, 1)) + table.getModel().getValueAt(selectedRow, 2);
			}
			
		};
		

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
					int rowAtPoint = getRowNumber(e, table);
					System.out.println("Clicked row: " + rowAtPoint);
					System.out.println("File name:" + table.getValueAt(rowAtPoint, 2));
					
					String movieFile = movie.getPathByAlias(table.getValueAt(rowAtPoint, 1))  + table.getValueAt(rowAtPoint, 2);
					if(movieFile == null)
						throw new RuntimeException("Movie file is not provided.");
					System.out.println(movieFile);
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

	private static ActionListener createAddFolderListener(
			JDialog addFolderDialog, Movie movie, DefaultTableModel dataModel, String pathToMovieDb) {
		AddFolderDialog dialog = new AddFolderDialog();
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addFolderDialog.setSize(600, 400);
				
				JPanel submitPanel = dialog.createDialogPanel(new ActionListener(){
				
						@Override
						public void actionPerformed(ActionEvent e) {
							addFolderDialog.setVisible(false);
							dialog.submit(movie,dataModel,pathToMovieDb);
					}
									
				});
				addFolderDialog.getContentPane().add(submitPanel);
				addFolderDialog.setVisible(true);				
			}
		};
	}

	private static int getRowNumber(MouseEvent e, JTable table) {
		Point p = e.getPoint();
		int rowAtPoint = table.rowAtPoint(p);
		return rowAtPoint;
	}
	
	public static DefaultTableModel fillDataModel(DefaultTableModel dataModel,
			Object[][] rows) {
		dataModel.addRow(rows[0]);
		dataModel.addRow(rows[1]);
		return dataModel;
	}

	private static DefaultTableModel createTableModel(String[] columns) {
		DefaultTableModel dataModel = new DefaultTableModel(){

			@Override
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
			
		};
		for(int c = 0;c<columns.length;c++)
			dataModel.addColumn(columns[c]);
		return dataModel;
	}
}