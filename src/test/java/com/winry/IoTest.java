package com.winry;

import java.util.Scanner;

/**
 * Created by cong on 2016/3/23.
 */
public class IoTest {


    public static void main(String[] args) {
//        Console console = System.console();
//        while(true) {
//            String any = console.readLine("type Any:");
//            System.out.println(any);
//        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Type any");
        while (sc.hasNext()) {
            String any = sc.nextLine();
            System.out.println("typed:" + any);
        }
    }


}
