package dao;

import java.util.Scanner;

public class Singleton {
    Scanner sc = new Scanner(System.in);
    String id;
    private static Singleton singleton = new Singleton();

    public String getId() {
        return id;
    }
    private Singleton() {
        this.id = sc.next();

    }
    static Singleton getSingleton() {
        return singleton;
    }
}
