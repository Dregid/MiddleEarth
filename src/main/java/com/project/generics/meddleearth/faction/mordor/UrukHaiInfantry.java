package com.project.generics.meddleearth.faction.mordor;

import com.project.generics.meddleearth.faction.Military;
import com.project.generics.meddleearth.unit.genus.UrukHai;
import com.project.generics.meddleearth.unit.profession.Infantry;

public class UrukHaiInfantry extends Military implements UrukHai, Infantry {
    public UrukHaiInfantry(String name) {
        super(name, minPower, maxPower);
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
