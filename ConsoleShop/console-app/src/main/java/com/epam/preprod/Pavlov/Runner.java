package com.epam.preprod.Pavlov;


import com.epam.preprod.Pavlov.Initializer.Application;

public class Runner {
    public static void main(String[] args) {
        Application app = new Application(System.in, System.out);
        app.start();
    }
}

