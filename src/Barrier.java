public class Barrier extends Component { //挡板
    private int length;  //长度

    public Barrier(Point x, int length) {
        this.type = "挡板";
        this.length = length;
        this.centerPoint = x;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void remove() {  //删除
    }

    public void zoomIn() {  //缩小
    }

    public void zoomOut() { //放大
    }
}
