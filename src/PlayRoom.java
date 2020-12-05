public class PlayRoom {    //控制器类
    Checkerboard board;

    public PlayRoom(){
        this.board = new Checkerboard(800,800,null);
    }

    public void deal(Point p, int mode){    //处理工具栏的操作
        Component com = checkCom(p);
        if (com == null)
            return;
        if (mode == 1)      //旋转选中组件
            rotateCom(com);
        if (mode == 2)       //删除选中组件
            deleteComponent(com);
        if (mode == 3)       //放大选中组件
            zoomOutCom(com);
        if (mode == 4)       //缩小选中组件
            zoomInCom(com);
    }

    public void addComponent(Point pCenter, String type){    //向board中添加组件
        switch (type) {
            case "圆":
                Circle circle = new Circle(pCenter, 20);
                board.components.add(circle);
                break;
            case "正方形":
                Square square = new Square(pCenter, 40);
                board.components.add(square);
                break;
            case "三角形":
                Triangle tri = new Triangle(pCenter);
                board.components.add(tri);
                break;
            case "球":
                board.ball = new Ball(pCenter, 20, 0, 10);
                break;
            case "吸收器":
                Absorber absorber = new Absorber(pCenter);
                board.components.add(absorber);
                break;
            case  "直轨道":
                StraightTrack straightTrack = new StraightTrack(pCenter);
                board.components.add(straightTrack);
                break;
            case  "弯轨道":
                CuredTrack track = new CuredTrack(pCenter);
                board.components.add(track);
                break;
            case "挡板":
                Barrier barrier = new Barrier(pCenter,80);
                board.components.add(barrier);
                break;
            default:
                break;
        }
    }

    public Component checkCom(Point p){            //判断某点上是否有组件存在，有则返回组件类型，无则返回null
        for (Component com : board.components){
            if (com.type.equals("圆")) {
                if (board.distance(p,com.centerPoint)<=(((Circle)com).getRadius()))
                    return com;
            }
            else if (com.type.equals("正方形")) {
                int halfLen = ((Square)com).getLength()/2;
                if ((p.x<=com.centerPoint.x+ halfLen)&&(p.x>=com.centerPoint.x- halfLen)&&(p.y<=com.centerPoint.y+ halfLen
                     )&&(p.y>=com.centerPoint.y- halfLen))
                    return com;
            }
            else if (com.type.equals("三角形")) {
                if (board.isInTriangle(((Triangle) com).getP1(), ((Triangle) com).getP2(), ((Triangle) com).getP3(), p))
                    return com;
            }
            else if (com.type.equals("吸收器")){
                int halfLen = ((Absorber)com).length/2;
                if ((p.x<=com.centerPoint.x+ halfLen)&&(p.x>=com.centerPoint.x- halfLen)&&(p.y<=com.centerPoint.y+ halfLen
                )&&(p.y>=com.centerPoint.y- halfLen))
                    return com;
            }
            else if (com.type.equals("直轨道")){
                int halfLen = ((StraightTrack)com).getLength()/2;
                if ((p.x<=com.centerPoint.x+ halfLen)&&(p.x>=com.centerPoint.x- halfLen)&&(p.y<=com.centerPoint.y+ halfLen
                )&&(p.y>=com.centerPoint.y- halfLen))
                    return com;
            }
            else if (com.type.equals("弯轨道")){
                int halfLen = ((CuredTrack)com).getLength()/2;
                if ((p.x<=com.centerPoint.x+ halfLen)&&(p.x>=com.centerPoint.x- halfLen)&&(p.y<=com.centerPoint.y+ halfLen
                )&&(p.y>=com.centerPoint.y- halfLen))
                    return com;
            }
            else if (com.type.equals("挡板")){
                int halfLen = ((Barrier)com).getLength()/2;
                if (p.x>=com.centerPoint.x-halfLen&&p.x<=com.centerPoint.x+halfLen&&p.y == com.centerPoint.y)
                    return com;
            }
        }
        return null;
    }

    public void rotateCom(Component component){   //旋转组件
        component.rotate();
    }

    public void zoomOutCom(Component component){   //放大组件
        component.zoomOut();
    }

    public void zoomInCom(Component component){   //缩小组件
        component.zoomIn();
    }

    public void deleteComponent(Component component){        //删除组件
        for (Component com : board.components){
            if (com.equals(component)) {
                board.components.remove(com);
                break;
            }
        }
    }

}
