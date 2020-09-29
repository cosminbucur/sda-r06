package com.sda.hibernate.associations.one_to_many_uni_join;

public class DemoOneToManyUnidirectionalJoinColumn {

    public static void main(String[] args) {
        FatherDao fatherDao = new FatherDao();

        // 1 father
        // 2 sons

        // save father:  5 inserts

        Son son1 = new Son();
        son1.setName("son1");

        Son son2 = new Son();
        son2.setName("son2");

        Father father = new Father();
        father.setName("father");

        // add children to parent
        father.getSons().add(son1);
        father.getSons().add(son2);

        // add children to parent version 2
//        List<Son> sons = new ArrayList<>();
//        sons.add(son1);
//        sons.add(son2);
//        father.setSons(sons);

        fatherDao.create(father);
    }
}
