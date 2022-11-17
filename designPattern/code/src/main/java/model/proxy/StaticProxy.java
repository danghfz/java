package model.proxy;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/17   17:56
 * 代理模式 -> 静态代理
 * 代理对象 和 被代理对象 实现相同的接口
 */
public class StaticProxy {
    public static void main(String[] args) {
        TeacherDao teacherDao = new TeacherDao();
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(teacherDao);
        teacherDaoProxy.teach();
    }
}
class TeacherDaoProxy implements ITeacher{
    private final TeacherDao teacherDao;

    public TeacherDaoProxy(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void teach() {
        System.out.println("开始代理...");
        teacherDao.teach();
        System.out.println("提交...");
    }
}
interface ITeacher{
    /**
     * 教学方法
     */
    void teach();
}
class TeacherDao implements ITeacher{
    @Override
    public void teach() {
        System.out.println("开始上课");
    }
}
/**
 * 1. 优点：在不修改目标对象的功能前提下, 能通过代理对象对目标功能扩展
 * 2. 缺点：因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类
 * 3. 一旦接口增加方法,目标对象与代理对象都要维护
 */