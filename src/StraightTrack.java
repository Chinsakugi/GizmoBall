public class StraightTrack extends Component {  //直轨道
    private int length = 40;
    boolean isVertical = true;   //轨道是否处于竖直状态
    private Point outPoint1;    //轨道两个出口的中心点坐标
    private Point outPoint2;

    public StraightTrack(Point p){
        this.type = "直轨道";
        this.centerPoint = p;
        outPoint1 = new Point(p.x, p.y-length/2);
        outPoint2 = new Point(p.x, p.y+length/2);
    }

    public void setOpposedVertical(){
        this.isVertical = !isVertical;
    }

    public Point getOutPoint1() {
        return outPoint1;
    }

    public void setOutPoint1(Point outPoint1) {
        this.outPoint1 = outPoint1;
    }

    public Point getOutPoint2() {
        return outPoint2;
    }

    public void setOutPoint2(Point outPoint2) {
        this.outPoint2 = outPoint2;
    }

    public void remove() {  //删除
    }
    public void rotate(){   //旋转
        setOpposedVertical();
        if (this.isVertical=true) {
            this.outPoint1.setPoint(centerPoint.x, centerPoint.y - length / 2);
            this.outPoint2.setPoint(centerPoint.x, centerPoint.y + length / 2);
        }
        else{
            this.outPoint1.setPoint(centerPoint.x - length/2, centerPoint.y);
            this.outPoint2.setPoint(centerPoint.x + length/2, centerPoint.y);
        }
    }
    public void zoomIn() {  //缩小
    }
    public void zoomOut() { //放大
    }
}
