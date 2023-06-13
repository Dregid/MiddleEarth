package com.project.generics.meddleearth.faction;

import com.project.generics.meddleearth.unit.Unit;

import java.util.Random;

public abstract class Military implements Unit { //К примеру этот класс может наследоваться от какого нибудь общего класса обьеденяющий все расы.
    private final String name;
    protected int power;

    public Military(String name, int minPower, int maxPower) {
        if (name == null)
            this.name = super.toString();
        else
            this.name = name;
        generatePower(minPower, maxPower);
    }

    public <T extends Military> void strike(T unit) {
        if (unit instanceof MilitaryCavalryman) {
            Mount mount = ((MilitaryCavalryman) unit).getMount();
            if (mount.power > 0) {
                mount.power -= this.power;
                mount.power = Math.max(mount.power, 0);
            } else {
                unit.power -= this.power;
                unit.power = Math.max(unit.power, 0);
            }
        } else {
            unit.power -= this.power;
            unit.power = Math.max(unit.power, 0);
        }
    }

    private void generatePower(int minPower, int maxPower) {
        Random random = new Random();
        this.power = random.nextInt(minPower, maxPower + 1);
    }

    @Override
    public String toString() {
        return String.format("%s %s has power %d", this.getClass().getSimpleName(), name, power);
    }
}
