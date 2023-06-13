package com.project.generics.meddleearth.faction.mordor;

import com.project.generics.meddleearth.faction.Military;
import com.project.generics.meddleearth.unit.profession.Infantry;
import com.project.generics.meddleearth.unit.race.Goblin;

public class GoblinInfantry extends Military implements Goblin, Infantry {
    public GoblinInfantry(String name) {
        super(name, minPower, maxPower);
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
