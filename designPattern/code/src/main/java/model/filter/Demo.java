package model.filter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/23 11:30
 * @since 1.8
 * 过滤器模式 -> 根据标准过滤
 */
public class Demo {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("Robert","Male"));
        persons.add(new Person("John","Male"));
        persons.add(new Person("Laura","Female"));
        persons.add(new Person("Diana","Female"));
        persons.add(new Person("Mike","Male"));
        persons.add(new Person("Bobby","Male"));
        // 男性过滤器
        CriteriaMale male = new CriteriaMale();
        // 女性过滤器
        CriteriaFemale female = new CriteriaFemale();
        // 组合过滤器 女性 + 男性
        AndCriteria singleMale = new AndCriteria(female, male);
        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

    }
    public static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    +", Gender : " + person.getGender()
                    +" ]");
        }
    }
}
@Data
class Person {
    private String name;
    private String gender;
    public Person(String name,String gender){
        this.name = name;
        this.gender = gender;
    }
}
/**
 * 标准接口
 */
interface Criteria<E> {
    /**
     * 过滤方式
     * @param lists 参数
     * @return list
     */
    List<E> meetCriteria(List<E> lists);
}

/**
 * 筛选出所有的 男性
 */
class CriteriaMale implements Criteria<Person> {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<>();
        for (Person person : persons) {
            if("MALE".equalsIgnoreCase(person.getGender())){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}

/**
 * 筛选出所有女性
 */
class CriteriaFemale implements Criteria<Person> {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if("FEMALE".equalsIgnoreCase(person.getGender())){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}

/**
 * 多个过滤器的组合
 */
class AndCriteria implements Criteria<Person> {
    private final Criteria<Person> criteria;
    private final Criteria<Person> otherCriteria;
    public AndCriteria(Criteria<Person> criteria, Criteria<Person> otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}