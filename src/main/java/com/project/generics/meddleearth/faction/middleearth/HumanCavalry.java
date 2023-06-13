package com.project.generics.meddleearth.faction.middleearth;

import com.project.generics.meddleearth.faction.MilitaryCavalryman;
import com.project.generics.meddleearth.unit.profession.Cavalry;
import com.project.generics.meddleearth.unit.race.Human;

public class HumanCavalry extends MilitaryCavalryman implements Human, Cavalry {
    public HumanCavalry(String name) {
        super(name, minPower, maxPower);
        setHorseMount();
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
