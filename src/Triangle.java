public class Triangle extends Component{    //三角形组件
    private Point p1;  //三角形三个顶点
    private Point p2;
    private Point p3;

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
    }

    public void remove() {  //删除
    }

    public void zoomIn() {  //缩小
    }

    public void zoomOut() { //放大
    }
}
