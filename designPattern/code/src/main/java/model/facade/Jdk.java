package model.facade;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Properties;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/17   9:40
 * 外观模式在 mybatis中 的使用
 *   MetaObject()
 */
public class Jdk {
    public static void main(String[] args) {
        /**
         * mybatis Configuration org.apache.ibatis.session.Configuration
         *      protected Properties variables;
         *      protected ReflectorFactory reflectorFactory;
         *      protected ObjectFactory objectFactory;
         *      protected ObjectWrapperFactory objectWrapperFactory;
         *      protected final MapperRegistry mapperRegistry
         *
         *      public MetaObject newMetaObject(Object object) {
         *          return MetaObject.forObject(object, objectFactory, objectWrapperFactory, reflectorFactory);
         *       }
         *
         *       public static MetaObject forObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory, ReflectorFactory reflectorFactory) {
         *           if (object == null) {
         *               return SystemMetaObject.NULL_META_OBJECT;
         *            } else {
         *                  return new MetaObject(object, objectFactory, objectWrapperFactory, reflectorFactory);
         *              }
         *       }
         *
         *       private MetaObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory, ReflectorFactory reflectorFactory) {
         *            this.originalObject = object;
         *            this.objectFactory = objectFactory;
         *            this.objectWrapperFactory = objectWrapperFactory;
         *            this.reflectorFactory = reflectorFactory;
         *
         *           if (object instanceof ObjectWrapper) {
         *           this.objectWrapper = (ObjectWrapper) object;
         *            } else if (objectWrapperFactory.hasWrapperFor(object)) {
         *             this.objectWrapper = objectWrapperFactory.getWrapperFor(this, object);
         *             } else if (object instanceof Map) {
         *            this.objectWrapper = new MapWrapper(this, (Map) object);
         *           } else if (object instanceof Collection) {
         *              this.objectWrapper = new CollectionWrapper(this, (Collection) object);
         *            } else {
         *             this.objectWrapper = new BeanWrapper(this, object);
         *             }
         *       }
         */
    }
}
