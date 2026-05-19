public class NormalZombie extends Zombie {
    public NormalZombie(int x, int y) {
        super(x, y, 200, 1, 100);//Chỉnh lại tốc độ sau
    }

    @Override
    public void update() {
       this.move();
    }
}
