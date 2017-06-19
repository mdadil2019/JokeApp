package com.example;

import java.util.Random;

public class Joke {

    private String[] jokes;
    private Random random;

    public Joke(){
        jokes = new String[3];
        jokes[0] = "Joke: I asked my daughter if she’d seen my newspaper. She told me that newspapers are old school. " +
                "She said that people use tablets nowadays and handed me her iPad. The fly didn’t stand a chance.";
        jokes[1] = "Joke: Anton, do you think I’m a bad mother?\n" +
                "\n" +
                "My name is Paul.";
        jokes[2] = "Joke: Optimist: The glass is half full.\n" +
                "\n" +
                "Pessimist: The glass is half empty.\n" +
                "\n" +
                "Mother: Why didn’t you use a coaster!";
        random = new Random();
    }
    public String[] getJokes() {
        return jokes;
    }

    public String getRandomJoke() {
        return jokes[random.nextInt(jokes.length)];
    }
}
