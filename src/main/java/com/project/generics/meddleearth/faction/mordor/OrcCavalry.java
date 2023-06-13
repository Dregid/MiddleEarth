package com.project.generics.meddleearth.faction.mordor;

import com.project.generics.meddleearth.faction.MilitaryCavalryman;
import com.project.generics.meddleearth.unit.profession.Cavalry;
import com.project.generics.meddleearth.unit.race.Orc;

public class OrcCavalry extends MilitaryCavalryman implements Orc, Cavalry {
    public OrcCavalry(String name) {
        super(name, minPower, maxPower);
        setWargMount();
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
