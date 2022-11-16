package model.adapter;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/16   9:17
 */

public class JDK {
    public static void main(String[] args) {
        //DispatcherServlet
        //  protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception
              // 获取需要映射的 controller
        //    mappedHandler = this.getHandler(processedRequest, false);
              // 获取适配器
        //    HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler());
              // 通过适配器调用 controller的方法并返回 ModelAndView
        //    mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
        /*
        protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
            Iterator i$ = this.handlerAdapters.iterator();

            HandlerAdapter ha;
            do {
                if (!i$.hasNext()) {
                    throw new ServletException("No adapter for handler [" + handler + "]: The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler");
                }

                ha = (HandlerAdapter)i$.next();
                if (this.logger.isTraceEnabled()) {
                    this.logger.trace("Testing handler adapter [" + ha + "]");
                }
            } while(!ha.supports(handler));

            return ha;
        }*/


    }
}
