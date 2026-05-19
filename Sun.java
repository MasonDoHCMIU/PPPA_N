public class Sun extends GameObject {
    private final int value;
    private final int maxLifespan;
    private int timeAlive;
    private boolean isExpired;
    private final int fallSpeed;
    private final int yEnd;

    public Sun( int x, int y) {
        super(x, y);
        this.yEnd = y + 50;//chỉnh sửa lại vị trí rơi của sun sau
        this.fallSpeed = 2;
        this.value = 25;
        this.maxLifespan = 480;
        this.timeAlive = 0;
        this.isExpired = false;

    }

    public int collect() {
        this.isExpired = true;
        return this.value;
    }

    @Override
    public void update() {
        if (this.y < this.yEnd) {
            this.y += fallSpeed;

            if (this.y > this.yEnd) {
                this.y = this.yEnd;
            }
        }
        else if (!isExpired) {
            timeAlive++;
            if (timeAlive >= maxLifespan) {
                isExpired = true;
            }
        }
    }

//----------Getters and setters----------
    public int getValue() {
        return value;
    }
    public boolean isExpired() {
        return isExpired;
    }
    public int getYEnd() {
        return yEnd;
    }
    public int getFallSpeed() {
        return fallSpeed;
    }
    public int getTimeAlive() {
        return timeAlive;
    }
    public int getMaxLifespan() {
        return maxLifespan;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
    public void setTimeAlive(int timeAlive) {
        this.timeAlive = timeAlive;
    }
}
