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
    }

    public void zoomOut() { //放大
    }

}
