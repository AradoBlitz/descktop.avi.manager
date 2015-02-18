package descktop.avi.manager;

import static org.junit.Assert.*;

import java.io.File;

import javax.media.Manager;
import javax.media.Player;

import org.junit.Before;
import org.junit.Test;

public class JavaPlayerTest {
	
	private final String pathToMovie = "./etc/CIMG2197.MOV";

	@Before
	public void checkIsPathToMovieFileProvided(){
		assertNotNull("Path to movie file should be provided.",pathToMovie);
	}
	
	@Test
	public void createUrlToVideoFile() throws Exception {
		assertEquals("Wrong URL to video"
				,"file:" + new File(pathToMovie).getAbsolutePath()
				, new File(pathToMovie).toURL().toString());
	}

	@Test
	public void createPlayerByUrl() throws Exception {
		Player createPlayer = Manager.createPlayer(new File(pathToMovie).toURL());
		assertNotNull(createPlayer);
		assertEquals("New Player shoud be Controler.Unrealized state.", Player.Unrealized,createPlayer.getState());

	}
	
}
