package com.project.generics.meddleearth.faction.mordor;

import com.project.generics.meddleearth.faction.MilitaryCavalryman;
import com.project.generics.meddleearth.unit.genus.UrukHai;
import com.project.generics.meddleearth.unit.profession.Cavalry;

public class UrukHaiCavalry extends MilitaryCavalryman implements UrukHai, Cavalry {
    public UrukHaiCavalry(String name) {
        super(name, minPower, maxPower);
        setWargMount();
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
