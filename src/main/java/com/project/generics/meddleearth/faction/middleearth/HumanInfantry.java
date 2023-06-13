package com.project.generics.meddleearth.faction.middleearth;

import com.project.generics.meddleearth.faction.Military;
import com.project.generics.meddleearth.unit.profession.Infantry;
import com.project.generics.meddleearth.unit.race.Human;

import java.util.Random;

public class HumanInfantry extends Military implements Human, Infantry {
    public HumanInfantry(String name) {
        super(name, minPower, maxPower);
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
