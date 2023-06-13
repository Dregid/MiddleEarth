package com.project.generics.meddleearth.faction.middleearth;

import com.project.generics.meddleearth.faction.Military;
import com.project.generics.meddleearth.unit.profession.Infantry;
import com.project.generics.meddleearth.unit.race.WoodenElf;

public class WoodenElfInfantry extends Military implements WoodenElf, Infantry {
    public WoodenElfInfantry(String name) {
        super(name, minPower, maxPower);
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
