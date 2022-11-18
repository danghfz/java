package model.template;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/18   8:34
 * 模板模式 在 spring 中的使用
 */
public class Jdk {
    /**
     * interface ConfigurableApplicationContext
     *      void refresh() throws BeansException, IllegalStateException;
     * abstract class AbstractApplicationContext ... implements ConfigurableApplicationContext
     *      public void refresh() throws BeansException, IllegalStateException {
     *         synchronized(this.startupShutdownMonitor) {
     *             this.prepareRefresh();
     *             ConfigurableListableBeanFactory beanFactory = this.obtainFreshBeanFactory();
     *             this.prepareBeanFactory(beanFactory);
     *
     *             try {
     *                 this.postProcessBeanFactory(beanFactory);
     *                 this.invokeBeanFactoryPostProcessors(beanFactory);
     *                 this.registerBeanPostProcessors(beanFactory);
     *                 this.initMessageSource();
     *                 this.initApplicationEventMulticaster();
     *                 this.onRefresh();
     *                 this.registerListeners();
     *                 this.finishBeanFactoryInitialization(beanFactory);
     *                 this.finishRefresh();
     *             } catch (BeansException var5) {
     *                 this.destroyBeans();
     *                 this.cancelRefresh(var5);
     *                 throw var5;
     *             }
     *
     *         }
     *     }
     */
}
