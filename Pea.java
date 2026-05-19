public abstract class Pea extends Plant {
    private final int shootCooldown;
    private int shootTimer;
    private boolean isReadyToShoot;

    public Pea(int x, int y, int hp, int cost, int cooldown) {
        super(x, y, hp, cost);
        this.shootCooldown = cooldown;
        this.shootTimer = 0;
        this.isReadyToShoot = false;
    }

    public void update() {
        if (!isReadyToShoot) {
            shootTimer++;
            if (shootTimer >= shootCooldown) {
                isReadyToShoot = true;
            }
        }
    }

    public abstract Projectile shoot();

//----------Getters and setters----------
    public int getShootCooldown() {
        return shootCooldown;
    }
    public int getShootTimer() {
        return shootTimer;
    }
    public boolean isReadyToShoot() {
        return isReadyToShoot;
    }
    public void setShootTimer(int shootTimer) {
        this.shootTimer = shootTimer;
    }
    public void setReadyToShoot(boolean readyToShoot) {
        isReadyToShoot = readyToShoot;
    }
}