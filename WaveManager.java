import javax.swing.Timer;
import java.awt.event.*;

public class WaveManager {
    private GameManager gameManager;
    private LevelData currentlevelData;
    private int currentLevelNumber;

    private int currentWaveIndex = 0;
    private int zombiesInWve = 0;

    private Timer spawnTime;

    public WaveManager(GameManager gameManager) {
        this.gameManager = gameManager;

        SaveManager.loadCurrentLevel();
        currentlevelData = SaveManager.getCurrentLevel();

        currentLevelNumber = Integer.parseInt(SaveManager.LEVEL_NUMBER);
    }
    public void startLevel(){
        startWave(0);
    }
    private void startWave(int waveIndex){
        WaveOfZombie[] wavesLevel = currentlevelData.getWaves();

        if (waveIndex >= wavesLevel.length){
            currentLevelNumber++;
            SaveManager.saveCurrentLevel(String.valueOf(currentLevelNumber));
            gameManager.showWinReward();
            return;
        }

        currentWaveIndex = waveIndex;
        zombiesInWve = 0;

        WaveOfZombie currentWave = wavesLevel[currentWaveIndex];
        if (currentWave.isHasFlagZombie()){
            System.out.println("A HUGE WAVE OF ZOMBIE IS APPROACHING");
        }
        spawnTime = new Timer(currentWave.getSpawnDelay(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnSingleZombie();
            }
        });
        spawnTime.start();
        spawnSingleZombie();
    }
    private void spawnSingleZombie(){
        WaveOfZombie currentWave = currentlevelData.getWaves()[currentWaveIndex];
        int totalZombieInWave = currentWave.getTotalZombie();

        ZombieType zombieType = currentlevelData.getRandomZombie();
        gameManager.addZombie(zombieType);

        zombiesInWve++;

        if (zombiesInWve >= totalZombieInWave) {
            spawnTime.stop();

            Timer delayNextWave = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startWave(currentWaveIndex + 1);
                }
            });
            delayNextWave.setRepeats(false);
            delayNextWave.start();
        }
    }
}
