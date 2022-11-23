package model.responsibilityChain;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/23 11:13
 * @since 1.8
 * 责任链模式
 */
public class Demo {
    public static AbstractLogger getAbstractLog(){
        // 三个处理器
        ConsoleLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        FileLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        ErrorLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        return errorLogger;
    }
    public static void main(String[] args) {
        // 获取处理器
        AbstractLogger loggerChain = getAbstractLog();
        // 处理信息 INFO处理
        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");
        // DEBUG  -->  INFO
        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is a debug level information.");
        // ERROR --> DEBUG --> INFO
        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
    }
}
/**
 * 抽象的记录器类
 */
abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    /**
     * 责任链中的下一个元素
     */
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message){
        // 可以处理
        if(this.level <= level){
            write(message);
        }
        // 存在下一个
        if(nextLogger !=null){
            // 下一个处理
            nextLogger.logMessage(level, message);
        }
    }

    /**
     * 处理消息
     * @param message 消息
     */
    abstract protected void write(String message);

}
/**
 * INFO处理
 */
class ConsoleLogger  extends AbstractLogger{
    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO::Logger: " + message);
    }
}

/**
 * DEBUG
 */
class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("DEBUG::Logger: " + message);
    }
}
/**
 * ERROR
 */
class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error::Logger: " + message);
    }
}