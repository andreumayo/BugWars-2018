package bugwars;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

class Main {
	private static String packageName1, packageName2, mapName;

	public static void main(String[] args) throws InterruptedException {
		// Parameters
		packageName1 = args[0];
		packageName2 = args[1];
		mapName = args[2];

		// Run game
		runGame();
	}

	private static void runGame() {
		String logName = generateLogName();
		Game g = Game.getInstance(packageName1, packageName2, mapName, logName, true, true);
		g.start();
		try {
			g.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static String generateLogName() {
		String root = System.getProperty("user.dir");
		// File name
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String name = "" + dateFormat.format(new Date()) + "-" +
				packageName1.replace('.', '_') + "-" +
				packageName2.replace('.', '_');
		String file = root + File.separator + "games" + File.separator + name;
		int k = 0;
		while ((new File(file)).exists()) {
			k++;
			file = root + File.separator + "games" + File.separator + name + "_" + k;
		}
		return file.substring(0, file.length());
	}
}
