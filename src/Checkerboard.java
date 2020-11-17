import java.util.ArrayList;

public class Checkerboard {    //棋盘
    int length;  //长度
    int width;  //宽度
    Ball ball;  //球
    ArrayList<Component> components;  //组件列表

    public Checkerboard(int l,int w,Ball ball){
        this.length=l;
        this.width=w;
        this.ball = ball;
        this.components = new ArrayList<Component>();
    }

    public boolean isCollide(){   //判断球是否与其他组件相撞
        return true;
    }


}
