package com.sda.hibernate.recap;

public class DemoLambda {

    public static void main(String[] args) {
        // anonymous interface implementation
        CosminFunction cosminFunctionOldSchool = new CosminFunction() {
            @Override
            public void run(int distance) {
                System.out.println("cosmin is running");
            }
        };

        CosminFunction cosminFunctionNewSchool = (distance) -> {
            System.out.println("cosmin is running");
        };

        CosminFunction cosminFunctionNewSchoolShort = distance -> System.out.println("cosmin is running " + distance);

        cosminFunctionNewSchoolShort.run(10);
    }
}
