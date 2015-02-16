package descktop.avi.manager;

import java.io.IOException;

public class TotemPlayer {

	public static void main(String[] args) {
			try {
				Runtime.getRuntime().exec("totem ./etc/CIMG2197.MOV");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
