public class CuredTrack extends Component {   //弯轨道
    private int length = 40;   //边长
    private int state;        //弯轨道的四个状态，分别为1，2，3，4 其中1状态时直角在左下角，依此类推

    public CuredTrack(Point x) {
        this.type = "弯轨道";
        this.centerPoint = x;
        this.state = 1;
    }

    public int getLength() {
        return length;
    }

    public int getState() {
        return state;
    }

    public void rotate() {  //旋转
        state = state % 4 + 1;   //只需变化弯轨道的状态
    }

    public void remove() {  //删除
    }

    public void zoomIn() {  //缩小
    }

    public void zoomOut() { //放大
    }
}
