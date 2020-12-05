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
        for (Component com : components) {
            if (com.type.equals("圆"))
                isCollideCircle((Circle) com);
            else if (com.type.equals("正方形"))
                isCollideSquare((Square) com);
            else if (com.type.equals("三角形"))
                isCollideTriangle((Triangle) com);
            else if (com.type.equals("吸收器"))
                isCollideAbsorber((Absorber) com);
            else if (com.type.equals("直轨道"))
                isCollideStraightTrack((StraightTrack) com);
            else if (com.type.equals("弯轨道"))
                isCollideCuredTrack((CuredTrack)com);
            else if (com.type.equals("挡板"))
                isCollideBarrier((Barrier)com);
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

    public void isCollideCircle(Circle circle) {   //处理小球与圆碰撞的情况
        int ball_x = ball.centerPoint.x;   //获取小球中心坐标
        int ball_y = ball.centerPoint.y;

        double distance = distance(new Point(ball_x, ball_y), circle.centerPoint);
        if (distance <= ball.getRadius() + circle.getRadius()) {
            double vx = ball.getX_speed();
            double vy = ball.getY_speed();  //获取小球x与y方向分速度

            double sin_a = Math.abs(ball_y - circle.centerPoint.y) / distance;  //a为切线与水平线夹角
            double cos_a = Math.abs(ball_x - circle.centerPoint.x) / distance;

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

            double vynx = vyn * sin_a;
            double vyny = vyn * cos_a;

            double xSpeed2 = vytx - vynx;
            double ySpeed2 = vyty - vyny;


            double new_vx = xSpeed1 + xSpeed2;
            double new_vy = ySpeed1 + ySpeed2;
            ball.setX_speed((int) new_vx);
            ball.setY_speed((int) new_vy);
        }
    }

    public void isCollideSquare(Square square) {   //处理小球与正方形碰撞的情况
        int ball_x = ball.centerPoint.x;   //获取小球中心坐标
        int ball_y = ball.centerPoint.y;
        int len = square.getLength();   //获取正方形边长

        if (Math.abs(ball_x - square.centerPoint.x) <= ball.getRadius() + len / 2 &&      //左边和右边
                ball_y >= square.centerPoint.y - len / 2 &&
                ball_y <= square.centerPoint.y + len / 2)
            ball.setX_speed(-ball.getX_speed());
        else if (Math.abs(ball_y - square.centerPoint.y) <= ball.getRadius() + len / 2 &&     //上边和下边
                ball_x > square.centerPoint.x - len / 2 - ball.getRadius() &&
                ball_x < square.centerPoint.x + len / 2 + ball.getRadius())
            ball.setY_speed(-ball.getY_speed());
    }

    public boolean isCircleCollideLine(Point circleP, Point p1, Point p2) {   //线段与圆的碰撞检测
        double vx1 = circleP.x - p1.x;
        double vy1 = circleP.y - p1.y;  //圆心与线段一端p1连线向量v1
        double vx2 = p2.x - p1.x;
        double vy2 = p2.y - p1.y;      //线段向量v2
        double r = ball.getRadius();  //球的半径

        double len = Math.sqrt(vx2 * vx2 + vy2 + vy2);   //线段长度
        vx2 /= len;
        vy2 /= len;   //单位化v2

        double u = vx1 * vx2 + vy1 * vy2;
        double x0, y0;   //圆在线段上的投影点p
        if (u <= 0) {  //p在p1左边
            x0 = p1.x;
            y0 = p1.y;
        } else if (u >= len) {   //p在p2右边
            x0 = p2.x;
            y0 = p2.y;
        } else {
            x0 = p1.x + vx2 * u;
            y0 = p1.y + vy2 * u;
        }
        return (circleP.x - x0) * (circleP.x - x0) + (circleP.y - y0) * (circleP.y - y0) <= r * r;
    }

    public boolean isInTriangle(Point A, Point B, Point C, Point P) {  //判断点是否在三角形内
        int a , b , c ;

        Point MA = new Point(P.x - A.x, P.y - A.y);
        Point MB = new Point(P.x - B.x, P.y - B.y);
        Point MC = new Point(P.x - C.x, P.y - C.y);

        a = MA.x * MB.y - MA.y * MB.x;
        b = MB.x * MC.y - MB.y * MC.x;
        c = MC.x * MA.y - MC.y * MA.x;

        if ((a <= 0 && b <= 0 && c <= 0) ||
                (a > 0 && b > 0 && c > 0))
            return true;
        return false;
    }

    public void isCollideTriangle(Triangle tri) {   //处理小球与三角形相撞的情况
        Point ballCenter = ball.centerPoint;  //小球中心点
        Point p1 = tri.getP1();
        Point p2 = tri.getP2();
        Point p3 = tri.getP3();    //三角形三个点

        if (isCircleCollideLine(ballCenter, p1, p2)) {   //小球与三角形直角边p1-p2相撞
            if (p1.x == p2.x)
                ball.setX_speed(-ball.getX_speed());
            if (p1.y == p2.y)
                ball.setY_speed(-ball.getY_speed());
            return;
        }

        if (isCircleCollideLine(ballCenter, p2, p3)) {   //小球与三角形直角边p2-p3相撞
            if (p2.y == p3.y)
                ball.setY_speed(-ball.getY_speed());
            if (p2.x == p3.x)
                ball.setX_speed(-ball.getX_speed());
            return;
        }

        if (isCircleCollideLine(ballCenter, p1, p3)) {   //小球与三角形斜边p1-p3相撞
            int xSpeed = ball.getX_speed();
            int ySpeed = ball.getY_speed();   //球的x与y方向速度
            if (p1.x == p2.x) {
                ball.setX_speed(ySpeed);
                ball.setY_speed(xSpeed);
            }
            if (p1.y == p2.y) {
                ball.setX_speed(-ySpeed);
                ball.setY_speed(-xSpeed);
            }
        }
    }

    public void isCollideAbsorber(Absorber absorber) {    //处理小球与吸收器相撞的情况
        int ball_x = ball.centerPoint.x;   //获取小球中心坐标
        int ball_y = ball.centerPoint.y;
        int len = absorber.length;

        if (Math.abs(ball_x - absorber.centerPoint.x) <= ball.getRadius() + len / 2 &&      //左边和右边
                ball_y >= absorber.centerPoint.y - len / 2 &&
                ball_y <= absorber.centerPoint.y + len / 2)
            ball = null;
        else if (Math.abs(ball_y - absorber.centerPoint.y) <= ball.getRadius() + len / 2 &&     //上边和下边
                ball_x > absorber.centerPoint.x - len / 2 - ball.getRadius() &&
                ball_x < absorber.centerPoint.x + len / 2 + ball.getRadius())
            ball = null;
    }

    public void isCollideStraightTrack(StraightTrack track) {         //处理小球与直轨道相撞的情况
        int halfLen = track.getLength() / 2;   //直轨道边长的一半
        int centerX = track.centerPoint.x;    //中心点横坐标
        int centerY = track.centerPoint.y;    //中心点纵坐标

        if (isCircleCollideLine(ball.centerPoint, new Point(centerX - halfLen, centerY - halfLen), new Point(centerX - halfLen, centerY + halfLen)) ||
                isCircleCollideLine(ball.centerPoint, new Point(centerX + halfLen, centerY - halfLen), new Point(centerX + halfLen, centerY + halfLen))) {
            if (track.isVertical)
                ball.setX_speed(-ball.getX_speed());
            else
                ball.setY_speed(0);
        }
        if (isCircleCollideLine(ball.centerPoint, new Point(centerX - halfLen, centerY - halfLen), new Point(centerX + halfLen, centerY - halfLen)) ||
                isCircleCollideLine(ball.centerPoint, new Point(centerX - halfLen, centerY + halfLen), new Point(centerX + halfLen, centerY + halfLen))){
            if (track.isVertical)
                ball.setX_speed(0);
            else
                ball.setY_speed(-ball.getY_speed());
        }
    }

    public void isCollideCuredTrack(CuredTrack track){      //处理小球与弯轨道相撞的情况
        int halfLen = track.getLength() / 2;
        Point p1 = new Point(track.centerPoint.x-halfLen,track.centerPoint.y-halfLen);
        Point p2 = new Point(track.centerPoint.x+halfLen,track.centerPoint.y-halfLen);
        Point p3 = new Point(track.centerPoint.x-halfLen,track.centerPoint.y+halfLen);
        Point p4 = new Point(track.centerPoint.x +halfLen,track.centerPoint.y+halfLen);
        int state = track.getState();

        if (isCircleCollideLine(ball.centerPoint,p1,p2)){
            if (state == 1 || state ==4)
                ball.setY_speed(-ball.getY_speed());
            else if (state == 2){
                ball.centerPoint.setPoint(track.centerPoint.x,track.centerPoint.y);
                ball.setX_speed(-ball.getY_speed());
                ball.setY_speed(0);
            }
            else if (state == 3) {
                ball.centerPoint.setPoint(track.centerPoint.x, track.centerPoint.y);
                ball.setX_speed(ball.getY_speed());
                ball.setY_speed(0);
            }
            return;
        }

        if (isCircleCollideLine(ball.centerPoint, p2,p4)){
            if (state == 1 || state == 2)
                ball.setX_speed(-ball.getX_speed());
            else if (state == 3){
                ball.centerPoint.setPoint(track.centerPoint.x,track.centerPoint.y);
                ball.setY_speed(ball.getX_speed());
                ball.setX_speed(0);
            }
            else if (state == 4){
                ball.centerPoint.setPoint(track.centerPoint.x,track.centerPoint.y);
                ball.setY_speed(-ball.getX_speed());
                ball.setX_speed(0);
            }
            return;
        }

        if (isCircleCollideLine(ball.centerPoint, p3,p4)){
            if (state == 2 || state == 3)
                ball.setY_speed(-ball.getY_speed());
            else if (state == 1){
                ball.centerPoint.setPoint(track.centerPoint.x,track.centerPoint.y);
                ball.setX_speed(ball.getY_speed());
                ball.setY_speed(0);
            }
            else if (state == 4){
                ball.centerPoint.setPoint(track.centerPoint.x,track.centerPoint.y);
                ball.setX_speed(-ball.getY_speed());
                ball.setY_speed(0);
            }
            return;
        }

        if (isCircleCollideLine(ball.centerPoint, p1,p3)){
            if (state == 3|| state == 4)
                ball.setX_speed(-ball.getX_speed());
            else if (state == 1){
                ball.centerPoint.setPoint(track.centerPoint.x,track.centerPoint.y);
                ball.setY_speed(ball.getX_speed());
                ball.setX_speed(0);
            }
            else if (state == 2){
                ball.centerPoint.setPoint(track.centerPoint.x,track.centerPoint.y);
                ball.setY_speed(-ball.getX_speed());
                ball.setX_speed(0);
            }
            return;
        }
    }

    public void isCollideBarrier(Barrier barrier){    //处理小球与挡板相撞的情况
        int halfLen = barrier.getLength();     //挡板长度的一半
        int barrierX = barrier.centerPoint.x;
        int barrierY = barrier.centerPoint.y;

        if (isCircleCollideLine(ball.centerPoint,new Point(barrierX-halfLen,barrierY),new Point(barrierX+halfLen,barrierY)))
            ball.setY_speed(-ball.getY_speed());
    }
}
