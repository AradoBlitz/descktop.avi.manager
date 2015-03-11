package descktop.avi.manager;

import static org.junit.Assert.assertEquals;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MoveNotesUI {

	public static class NewTestDialog{
		public static void main(String[] args){
			JFrame testFrame = new JFrame("Test Frame");
			
			DefaultTableModel model = Main.createTableModel(new String[]{"№","Folder","Files"});
			Movie movie = new Movie();
			NewAddFolderDialog addFolderDialog = new NewAddFolderDialog();
			JPanel createDialogPanel = addFolderDialog.createDialogPanel(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					addFolderDialog.submit(movie, model);
					assertEquals(3,model.getRowCount());
					assertEquals(3,model.getColumnCount());
					assertEquals("Test alias",model.getValueAt(0, 1));
					assertEquals("CIMG2220.MOV",model.getValueAt(0, 2));
					assertEquals("/home/dmitriy/Projects/Java/portfolio/etc/",movie.getPathByAlias("Test alias"));
					testFrame.setVisible(false);
					System.exit(0);
					
				}
			});
			testFrame.getContentPane().add(createDialogPanel);
			testFrame.setSize(600, 600);
			testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			testFrame.setVisible(true);
			
		}
	}
	
	public static class TestDialog{
		public static void main(String[] args){
			JFrame testFrame = new JFrame("Test Frame");
			
			DefaultTableModel model = Main.createTableModel(new String[]{"№","Folder","Files"});
			Movie movie = new Movie();
			AddFolderDialog addFolderDialog = new AddFolderEmbeddedFileChooser();
			JPanel createDialogPanel = addFolderDialog.createDialogPanel(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					addFolderDialog.submit(movie, model);
					assertEquals(3,model.getRowCount());
					assertEquals(3,model.getColumnCount());
					assertEquals("Test alias",model.getValueAt(0, 1));
					assertEquals("CIMG2220.MOV",model.getValueAt(0, 2));
					assertEquals("/home/dmitriy/Projects/Java/portfolio/etc/",movie.getPathByAlias("Test alias"));
					testFrame.setVisible(false);
					System.exit(0);
					
				}
			});
			testFrame.getContentPane().add(createDialogPanel);
			testFrame.setSize(600, 600);
			testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			testFrame.setVisible(true);
			
		}
	}
	
	public static class Main{
	public static void main(String args[]){
		String[][] loadedMovies = new String[][]{{"etc", "./etc/","CIMG2197.MOV"},{"etc2", "./etc/","CIMG2220.MOV"}};
		
		try {
			String pathToMovieDb = "./etc/movie.db.txt";
			Movie movie = new Movie(pathToMovieDb);		
			DefaultTableModel dataModel = movie.initTableModel(new FileMoviesDataBase().loadMelodies("./etc/movie.db.txt"),createTableModel(new String[]{"№","Folder","Files"}));
			
			
			JFrame frame = new JFrame("Movie Notes");
			addPanel(frame, creatAddMovieFolderPanel(movie, dataModel,
				new JDialog(frame), new NewAddFolderDialog()), BorderLayout.NORTH);
			
			addTable(frame, configureMediaFilesTable(
					createTable(movie)
					,createPlayMovieListener(movie)
					, dataModel), BorderLayout.CENTER);
					
			frame.setSize(300, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}

	private static void movieNotes(String[][] loadedMovies) {
		String pathToMovieDb = "./etc/movie.db.txt";
		Movie movie = new Movie(pathToMovieDb);		
		DefaultTableModel dataModel = movie.initTableModel(loadedMovies,createTableModel(new String[]{"№","Folder","Files"}));

		
		JFrame frame = new JFrame("Movie Notes");
		addPanel(frame, creatAddMovieFolderPanel(movie, dataModel,
			new JDialog(frame),new AddFolderEmbeddedFileChooser()), BorderLayout.NORTH);
		
		addTable(frame, configureMediaFilesTable(
				createTable(movie)
				,createPlayMovieListener(movie)
				, dataModel), BorderLayout.CENTER);
				
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static void movieNotes(Movie movie, DefaultTableModel dataModel) {
		JFrame frame = new JFrame("Movie Notes");
		addPanel(frame, creatAddMovieFolderPanel(movie, dataModel,
			new JDialog(frame), new AddFolderEmbeddedFileChooser()), BorderLayout.NORTH);
		
		addTable(frame, configureMediaFilesTable(
				createTable(movie)
				,createPlayMovieListener(movie)
				, dataModel), BorderLayout.CENTER);
				
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static void addTable(JFrame frame, JTable configureMediaFilesTable,
			String center) {
		frame
			.getContentPane()
			.add(
					new JScrollPane(
							configureMediaFilesTable)
			,center);
	}

	private static void addPanel(JFrame frame, JPanel creatAddMovieFolderPanel,
			String north) {
		frame
			.getContentPane()
			.add(creatAddMovieFolderPanel
			,north);
	}

	private static JTable configureMediaFilesTable(JTable mediaFiles, MouseListener createPlayMovieListener,
			DefaultTableModel dataModel) {
		mediaFiles.setModel(dataModel);
		mediaFiles.createDefaultColumnsFromModel();
		mediaFiles.addMouseListener(createPlayMovieListener);
		
		return mediaFiles;
	}

	private static JPanel creatAddMovieFolderPanel(Movie movie,
			DefaultTableModel dataModel, JDialog addFolderDialog,AddFolderDialog addFolderDialog2) {
		JPanel buttonPanel = new JPanel();
		JButton addFolderButton = new JButton("Add Folder");		
		
		addFolderButton.addActionListener(createAddFolderListener(addFolderDialog, addFolderDialog2,movie, dataModel));
		buttonPanel.add(addFolderButton);
		return buttonPanel;
	}

	private static JTable createTable(Movie movie) {
		JTable mediaFiles = new JTable(){

			@Override
			public String getToolTipText(MouseEvent event) {
				JTable table = (JTable)event.getSource();				
				int selectedRow = getRowNumber(event, (JTable)event.getSource());
				return movie.getPathByAlias(table.getModel().getValueAt(selectedRow, 1)) + table.getModel().getValueAt(selectedRow, 2);
			}
			
		};
		return mediaFiles;
	}

	private static MouseListener createPlayMovieListener(Movie movie) {
		return new MouseListener() {
			
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
		};
	}

	private static ActionListener createAddFolderListener(
			JDialog addFolderDialog, AddFolderDialog dialog,  Movie movie, DefaultTableModel dataModel) {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addFolderDialog.setSize(600, 400);
				
				JPanel submitPanel = dialog.createDialogPanel(new ActionListener(){
				
						@Override
						public void actionPerformed(ActionEvent e) {
							addFolderDialog.setVisible(false);
							dialog.submit(movie,dataModel);
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
}