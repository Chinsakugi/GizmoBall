import java.util.ArrayList;

public class Checkerboard {    //棋盘
    int length;  //长度
    int width;  //宽度
    Ball ball;  //球
    ArrayList<Component> components;  //组件列表

    public Checkerboard(int l, int w, Ball ball) {
        this.length = l;
        this.width = w;
        this.ball = ball;
        this.components = new ArrayList<Component>();
    }

    public double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public void isCollide() {   //判断球是否与其他组件相撞，如相撞则改变小球运动方向
        isCollideBorder();
        for (Component com : components){
            if (com.getClass().equals(Circle.class))
                isCollideCircle(com);
            else if (com.getClass().equals(Square.class))
                isCollideSquare(com);
        }
    }

    public void isCollideBorder() {   //处理小球撞到棋盘边界的情况
        int ball_x = ball.centerPoint.x;   //获取小球中心坐标
        int ball_y = ball.centerPoint.y;

        if (ball_x <= ball.getRadius()) {     //左边界
            ball.setX_speed(-ball.getX_speed());
            ball.centerPoint.x = ball.getRadius();
        }
        if (ball_x >= length - ball.getRadius()) {  //右边界
            ball.setX_speed(-ball.getX_speed());
            ball.centerPoint.x = length - ball.getRadius();
        }
        if (ball_y <= ball.getRadius()) {    //上边界
            ball.setY_speed(-ball.getY_speed());
            ball.centerPoint.y = ball.getRadius();
        }
        if (ball_y >= width - ball.getRadius()) {    //下边界
            ball.setY_speed(-ball.getY_speed());
            ball.centerPoint.y = width - ball.getRadius();
        }
    }

    public void isCollideCircle(Component circle){
        int ball_x = ball.centerPoint.x ;   //获取小球中心坐标
        int ball_y = ball.centerPoint.y ;

        double distance = distance(new Point(ball_x,ball_y),circle.centerPoint);
        if (distance <= ball.getRadius() + ((Circle) circle).getRadius()){
            double vx = ball.getX_speed();
            double vy = ball.getY_speed();  //获取小球x与y方向分速度

            double sin_a = Math.abs(ball_y-circle.centerPoint.y)/distance;  //a为切线与水平线夹角
            double cos_a = Math.abs(ball_x-circle.centerPoint.x)/distance;

            double vxt = vx * cos_a;
            double vxn = vx * sin_a;

            double vxtx = vxt * cos_a;
            double vxty = vxt * sin_a;

            double vxnx = vxn * sin_a;
            double vxny = vxn * cos_a;

            double xSpeed1 = vxtx - vxnx;
            double ySpeed1 = vxty - vxny;

            double vyt = vy * cos_a;    //y方向速度在碰撞面切向和法向做分解
            double vyn = vy * sin_a;

            double vytx = vyt * cos_a;    //切向速度和法向速度再沿xy轴分解
            double vyty = vyt * sin_a;

            double vynx = vyn*sin_a;
            double vyny = vyn*cos_a;

            double xSpeed2 = vytx - vynx;
            double ySpeed2 = vyty - vyny;


            double new_vx = xSpeed1 + xSpeed2;
            double new_vy = ySpeed1 + ySpeed2;
            ball.setX_speed((int) new_vx);
            ball.setY_speed((int) new_vy);
        }
    }

    public void isCollideSquare(Component square){
        int ball_x = ball.centerPoint.x ;   //获取小球中心坐标
        int ball_y = ball.centerPoint.y ;
        int length = ((Square)square).getLength();   //获取正方形边长

        if (Math.abs(ball_x-square.centerPoint.x)<= ball.getRadius()+length/2 &&      //左边和右边
                ball_y >= square.centerPoint.y-length/2-ball.getRadius() &&
                ball_y <= square.centerPoint.y +length/2 + ball.getRadius())
            ball.setX_speed(-ball.getX_speed());
        if (Math.abs(ball_y-square.centerPoint.y)<= ball.getRadius()+length/2 &&     //上边和下边
                ball_x> square.centerPoint.x - length/2 && ball_x < square.centerPoint.x + length/2)
            ball.setY_speed(-ball.getY_speed());
    }

}
