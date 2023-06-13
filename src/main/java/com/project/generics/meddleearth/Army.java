package com.project.generics.meddleearth;

import com.project.generics.meddleearth.unit.Unit;
import com.project.generics.meddleearth.unit.profession.Cavalry;
import com.project.generics.meddleearth.unit.profession.Infantry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Army<T extends Unit> {
    private final List<T> army = new ArrayList<>();


    public List<T> getArmy() {
        return Stream.concat(getCavalry().stream(), getInfantry().stream())
                .collect(Collectors.toList());
    }

    public List<T> getCavalry() {
        List<T> cavalry = new ArrayList<>();
        for (T unit : army) {
            if (unit instanceof Cavalry)
                cavalry.add(unit);
        }
        return cavalry;
    }

    public List<T> getInfantry() {
        List<T> infantry = new ArrayList<>();
        for (T unit : army) {
            if (unit instanceof Infantry)
                infantry.add(unit);
        }
        return infantry;
    }

    public boolean recruit(T unit) {
        return army.add(unit);
    }

    public boolean release(T unit) {
        return army.remove(unit);
    }

    public void print() {
        for (T unit : getArmy())
            System.out.println(unit.toString());
    }

    public T getRandomUnit() {
        if (army.size() == 0)
            return null;
        Random random = new Random();
        return army.get(random.nextInt(army.size()));
    }

    public T getRandomUnit(T unit) {
        if (army.size() == 0)
            return null;
        Random random = new Random();
        List<T> typeArmy;

        if (unit instanceof Cavalry) {
            typeArmy = getCavalry();
            return typeArmy.get(random.nextInt(typeArmy.size()));
        } else if (unit instanceof Infantry) {
            typeArmy = getInfantry();
            return typeArmy.get(random.nextInt(typeArmy.size()));
        } else
            return null;
    }
}
