package descktop.avi.manager;

import static org.junit.Assert.*;

import java.io.File;

import javax.media.Manager;
import javax.media.Player;

import org.junit.Test;

public class JavaPlayerTest {
	@Test
	public void createUrlToVideoFile() throws Exception {
		assertEquals("Wrong URL to video"
				,"file:/home/dmitriy/Projects/Java/portfolio/descktop.avi.manager/./etc/CIMG2197.MOV"
				, new File("./etc/CIMG2197.MOV").toURL().toString());
	}

	@Test
	public void createPlayerByUrl() throws Exception {
		Player createPlayer = Manager.createPlayer(new File("./etc/CIMG2197.MOV").toURL());
		assertNotNull(createPlayer);
		assertEquals("New Player shoud be Controler.Unrealized state.", Player.Unrealized,createPlayer.getState());

	}
	
}
