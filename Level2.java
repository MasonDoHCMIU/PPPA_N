import WaveZombieLv.WaveOfZombie;

public class Level2 extends LevelData{
    @Override
    public void initLevelData(){
        zombieCurrentLevel = new ZombieType[]{ZombieType.NORMALZOMBIE, ZombieType.CONEHEAD};
        chancesAppearZom = new int[][]{{0, 49}, {50, 99}};
        wave = new WaveOfZombie[]{
                new WaveOfZombie(5, 7000, false),
                new WaveOfZombie(15, 6000, false),
                new WaveOfZombie(20, 4000, true ),
        };
    }
}
