public class ConeheadZombie extends Zombie {
    public ConeheadZombie(int x, int y) {
        super(x, y, 400, 1, 100);//Chỉnh lại tốc độ sau
    }
    @Override
    public void update() {
        this.move();
    }
}
