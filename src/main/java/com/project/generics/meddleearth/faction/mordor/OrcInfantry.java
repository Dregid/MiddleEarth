package com.project.generics.meddleearth.faction.mordor;

import com.project.generics.meddleearth.faction.Military;
import com.project.generics.meddleearth.unit.profession.Infantry;
import com.project.generics.meddleearth.unit.race.Orc;

public class OrcInfantry extends Military implements Orc, Infantry {
    public OrcInfantry(String name) {
        super(name, minPower, maxPower);
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
