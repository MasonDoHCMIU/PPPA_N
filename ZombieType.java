package LevelManager;

//Define one enum Zombie so it will automatically update to LevelData
public enum ZombieType {

    NORMALZOMBIE{
        @Override
        public Zombie generateZom(int x, int y){
            return new NormalZombie(x, y);
        }
    },
    CONEHEAD{
        @Override
        public Zombie generateZom(int x, int y){
            return new ConeHeadZombie(x, y);
        }
    };

    public abstract Zombie generateZom(int x, int y);
}
