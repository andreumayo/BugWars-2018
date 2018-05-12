package bugwars;

import java.io.File;

public class MainServer {
	private static String packageName1, packageName2, mapName, gameId;

  public static void main(String[] args) throws InterruptedException {
    // Parameters
    packageName1 = args[0];
    packageName2 = args[1];
    mapName = args[2];
    gameId  = args[3];

		// Run game
		runGame();
  }

  private static void runGame() {
	  String logName = generateLogName();
    Game g = Game.getInstance(packageName1, packageName2, mapName, logName, false, false);
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
		String name = gameId;
		String fs = File.separator;
		String file = root + fs + ".."  + fs + "games" + fs + name;
		return file.substring(0, file.length());
	}
}
