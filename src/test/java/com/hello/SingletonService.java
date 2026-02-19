package com.hello;


public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성해둔다.
    private static SingletonService instance = new SingletonService();

    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 다른곳에서 new 키워드로 사용하지 못하게 private로 생성한다
    private SingletonService() {}


    public void singletonLogic(){
        System.out.println("singleton logic Start");
    }
}
