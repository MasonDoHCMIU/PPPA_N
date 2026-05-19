public abstract class Zombie extends GameObject {
    protected int hp;
    protected final int maxHp;
    protected int speed;
    protected int damage;
    protected boolean isEating;
    protected int attackTimer;
    protected final int attackCooldown;

    protected int originalSpeed;
    protected boolean isChilled;
    protected int chillTimer;
    protected final int chillDuration;

    public Zombie(int x, int y, int hp, int speed, int damage) {
        super(x, y);
        this.hp = hp;
        this.maxHp = hp;
        this.speed = speed;
        this.damage = damage;
        this.isEating = false;
        this.attackTimer = 0;
        this.attackCooldown = 60;

        this.originalSpeed = speed;
        this.isChilled = false;
        this.chillTimer = 0;
        this.chillDuration = 300;
    }

    @Override
    public abstract void update();

    public void takeDamage(int damage) {
        hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public void move() {
        if (!isEating) {
            x -= speed;
        }
    }

    public void attack(Plant targetPlant) {
        if (targetPlant != null && !targetPlant.isDead()) {
            isEating = true;
            attackTimer++;
            if (attackTimer >= attackCooldown) {
                targetPlant.takeBite(this.damage);
                attackTimer = 0;
            }
        } else {
            isEating = false;
        }
    }

//----------Chill effect methods----------
    public void applyChill() {
        if (!isChilled) {
            this.isChilled = true;
            this.chillTimer = 0;
            this.speed = this.originalSpeed / 2;
        }
    }

    public void updateChill() {
        if (isChilled) {
            chillTimer++;
            if (chillTimer >= chillDuration) {
                isChilled = false;
                this.speed = originalSpeed;
            }
        }
    }
//----------Getters and setters----------
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public boolean isEating() {
        return isEating;
    }
    public void setEating(boolean isEating) {
        this.isEating = isEating;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getAttackTimer() {
        return attackTimer;
    }
    public void setAttackTimer(int attackTimer) {
        this.attackTimer = attackTimer;
    }
    public int getAttackCooldown() {
        return attackCooldown;
    }
    public boolean isChilled() {
        return isChilled;
    }
    public int getChillTimer() {
        return chillTimer;
    }
    public void setChillTimer(int chillTimer) {
        this.chillTimer = chillTimer;
    }
    public int getChillDuration() {
        return chillDuration;
    }
}
