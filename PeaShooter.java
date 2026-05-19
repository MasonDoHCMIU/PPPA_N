public class PeaShooter extends Pea {
    public PeaShooter(int x, int y) {
        super(x, y, 300, 100, 90);
    }
    @Override
    public Projectile shoot() {
        if (isReadyToShoot()) {
            setShootTimer(0);
            setReadyToShoot(false);
            return new Bullet(this.getX(), this.getY());
        }
        return null;
    }
}
