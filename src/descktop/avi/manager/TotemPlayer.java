package descktop.avi.manager;

import java.io.IOException;

public class TotemPlayer {

	public static void main(String[] args) {
			try {
				String movieFile = null;
				if(movieFile == null)
					throw new RuntimeException("Movie file is not provided.");
				Runtime.getRuntime().exec("totem " + movieFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
