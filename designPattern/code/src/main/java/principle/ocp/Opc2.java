package principle.ocp;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   18:35
 */
public class Opc2 {
}

//这是一个用于绘图的类 [使用方]
class GraphicEditor2 {
    //接收 Shape 对象，调用 draw 方法
    public void drawShape(Shape2 s) {
        s.draw();
    }
}

//Shape 类，基类
abstract class Shape2 {
    int m_type;

    public abstract void draw();//抽象方法
}

class Rectangle2 extends Shape2 {
    Rectangle2() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制矩形 ");
    }
}

class Circle2 extends Shape2 {
    Circle2() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制圆形 ");
    }
}

//新增画三角形
class Triangle2 extends Shape2 {
    Triangle2() {
        super.m_type = 3;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制三角形 ");
    }
}

//新增一个图形
class OtherGraphic2 extends Shape2 {
    OtherGraphic2() {
        super.m_type = 4;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制其它图形 ");
    }
}