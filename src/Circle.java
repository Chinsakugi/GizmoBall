public class Circle extends Component{   //圆形组件
    private int radius;  //半径

    public Circle(Point x, int radius) {  //构造函数
        this.type = "圆";
        this.centerPoint = x;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void rotate() {  //旋转
    }

    public void remove() {  //删除
    }

    public void zoomIn() {  //缩小
        if (radius > 40)
            radius = radius - 40;  //一个格子宽度
    }

    public void zoomOut() { //放大
        radius = radius + 40;
    }

}
