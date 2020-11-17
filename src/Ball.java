public class Ball extends Component{  //弹球
    private int radius;   //半径
    private int x_speed;  //x方向速度
    private int y_speed;  //y方向速度

    public Ball(Point x, int radius, int vx, int vy){
        this.type = "球";
        this.centerPoint = x;
        this.radius = radius;
        this.x_speed = vx;
        this.y_speed = vy;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getX_speed() {
        return x_speed;
    }

    public void setX_speed(int x_speed) {
        this.x_speed = x_speed;
    }

    public int getY_speed() {
        return y_speed;
    }

    public void setY_speed(int y_speed) {
        this.y_speed = y_speed;
    }

    public void move(){  //小球移动一个时间单位
    }
}
