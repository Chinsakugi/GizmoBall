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
        length = length / 2;
        centerPoint.x = centerPoint.x - length/2;
        centerPoint.y = centerPoint.y - length/2;
    }

    public void zoomOut() { //放大
        centerPoint.x = centerPoint.x + length/2 ;  //中心点坐标变化
        centerPoint.y = centerPoint.y + length/2;
        length = length * 2;
    }

}
