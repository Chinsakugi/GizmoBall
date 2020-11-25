public class Triangle extends Component{    //三角形组件
    private Point p1;  //三角形三个顶点   p1
    private Point p2;               //   |\
    private Point p3;               //   | \
                                    // p2|__\ p3

    public Triangle(Point p1, Point p2, Point p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.centerPoint = new Point((p1.x+ p3.x)/2,(p1.y+ p3.y)/2);
        this.type = "三角形";
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    public void rotate() {  //旋转
        int a = Math.abs(p3.x - p1.x);  //三角形直角边长

        if (p1.y<p2.y){    //根据三角形的不同状态分不同的情况
            p1.x += a;
            p2.y -= a;
            p3.x -= a;
        }
        else if (p2.y<p3.y){
            p1.y += a;
            p2.x += a;
            p3.y -= a;
        }
        else if (p2.y<p1.y){
            p1.x -= a;
            p2.y += a;
            p3.x += a;
        }
        else if (p3.y<p2.y){
            p1.y -= a;
            p2.x -= a;
            p3.y += a;
        }
    }

    public void remove() {  //删除
    }

    public void zoomIn() {  //缩小
        if (Math.abs(p1.x - p3.x)<40)
            return;
        int a = 40;   //一个格子宽度
        if (p1.y<p2.y){           //根据三角形的不同状态分不同的情况
            p1.y += a;
            p3.x -= a;
        }
        else if (p2.y<p3.y){
            p1.x -= a;
            p3.y -= a;
        }
        else if (p2.y<p1.y){
            p1.y -= a;
            p3.x += a;
        }
        else if (p3.y<p2.y){
            p1.x += a;
            p3.y += a;
        }
        centerPoint.x = (p1.x+ p3.x)/2;
        centerPoint.y = (p1.y+ p3.y)/2;
    }

    public void zoomOut() { //放大
        int a = 40;  //一个格子宽度
        if (p1.y<p2.y){    //根据三角形的不同状态分不同的情况,直角顶点坐标不变
            p1.y -= a;
            p3.x += a;
        }
        else if (p2.y<p3.y){
            p1.x += a;
            p3.y += a;
        }
        else if (p2.y<p1.y){
            p1.y += a;
            p3.x -= a;
        }
        else if (p3.y<p2.y){
            p1.x -= a;
            p3.y -= a;
        }
        centerPoint.x = (p1.x+ p3.x)/2;
        centerPoint.y = (p1.y+ p3.y)/2;
    }
}
