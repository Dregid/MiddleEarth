package com.project.generics.meddleearth.faction;

import java.util.Random;

public abstract class Mount {
    protected int power;

    public Mount(int minPower, int maxPower) {
        generatePower(minPower, maxPower);
    }

    private void generatePower(int minPower, int maxPower) {
        Random random = new Random();
        this.power = random.nextInt(minPower, maxPower);
    }
}
