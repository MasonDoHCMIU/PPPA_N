import WaveZombieLv.WaveOfZombie;

public class Level1 extends LevelData {
    @Override
    public void initLevelData() {
        zombieCurrentLevel = new ZombieType[]{ZombieType.NORMALZOMBIE};
        chancesAppearZom = new int[][]{{0, 99}};
        wave = new WaveOfZombie[]{
                new WaveOfZombie(5, 7000, false),
                new WaveOfZombie(10, 4000, true),
        };
        //First wave have 5 zoms
        //Second wave have 10 zoms
    }
}
