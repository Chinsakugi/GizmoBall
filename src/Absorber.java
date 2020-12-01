public class Absorber extends Component{  //吸收器
    int length;

    public Absorber(Point x) {
        this.type = "吸收器";
        this.centerPoint = x;
        this.length = 40;
    }

    public void remove() {  //删除(吸收器不具备这些功能)
    }
    public void rotate(){   //旋转
    }
    public void zoomIn() {  //缩小
    }
    public void zoomOut() { //放大
    }
}
