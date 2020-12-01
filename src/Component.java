public abstract class Component {   //组件
    public String type;   //组件类型
    protected Point centerPoint; //中心点坐标

    public abstract void rotate();  //旋转
    public abstract void remove();  //删除
    public abstract void zoomIn();  //缩小
    public abstract void zoomOut(); //放大
}
