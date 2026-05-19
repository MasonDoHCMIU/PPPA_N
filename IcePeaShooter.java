public class IcePeaShooter extends Pea {
    public IcePeaShooter(int x, int y) {
        super(x, y, 300, 175, 90);
    }

    @Override
    public Projectile shoot() {
        if (isReadyToShoot()) {
            setShootTimer(0);
            setReadyToShoot(false);
            return new IceBullet(this.getX(), this.getY());
        }
        return null;
    }
}
