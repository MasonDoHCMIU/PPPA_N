public abstract class Plant extends GameObject {
    protected int hp;
    protected final int maxHp;
    protected int cost;

    public Plant(int x, int y, int hp, int cost) {
        super(x, y);
        this.hp = hp;
        this.maxHp = hp;
        this.cost = cost;
    }

    @Override
    public abstract void update();

    public void takeBite(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }


    public boolean isAlive() {
        return hp > 0;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void stop() {
        //update later
    }

//----------Getters and setters----------
    public int getHp() {
        return hp;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getCost() {
        return cost;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
}
