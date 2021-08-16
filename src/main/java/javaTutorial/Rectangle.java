package javaTutorial;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/11/18 14:08
 **/
public class Rectangle extends Shape{

    @Override
    public void getArea()
    {
        System.out.println("Rectangle Area");

    }



    public static void main(String args[])
    {
        Shape shape = new Shape();

        shape.getArea();

        Rectangle rectangle = new Rectangle();

        rectangle.getArea();
    }
}
