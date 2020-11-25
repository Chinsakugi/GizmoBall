public class Square extends Component{    //正方形
    private int length;

    public Square(Point x,int length) {
        this.type = "正方形";
        this.centerPoint = x;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void rotate() {  //旋转
    }

    public void remove() {  //删除
    }

    public void zoomIn() {  //缩小
        if (length > 40) {
            length = length - 40;
            centerPoint.x = centerPoint.x - 20;
            centerPoint.y = centerPoint.y - 20;
        }
    }

    public void zoomOut() { //放大
        centerPoint.x = centerPoint.x + 20;  //中心点坐标变化
        centerPoint.y = centerPoint.y + 20;
        length = length + 40;
    }

}
