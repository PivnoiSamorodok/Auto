package UmobixTest.mainFlowTests;

import java.util.Random;

public class randomEmailGeneration {
    private static final String static_part = "dmitry_kavunenko+";
    private static final String domain = "@tchdm.live";
    private static final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private int randomEmailPartLength;

    public randomEmailGeneration(int randomEmailPartLegth) {
        this.randomEmailPartLength = randomEmailPartLegth;

    }

    public String GenerateEmail() {
        StringBuilder randomPart = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < randomEmailPartLength; i++) {
            int index = random.nextInt(characters.length());
            randomPart.append(characters.charAt(index));
        }
        return static_part + randomPart.toString() + domain;
    }
}

