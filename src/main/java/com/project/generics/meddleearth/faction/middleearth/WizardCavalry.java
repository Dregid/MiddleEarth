package com.project.generics.meddleearth.faction.middleearth;

import com.project.generics.meddleearth.faction.MilitaryCavalryman;
import com.project.generics.meddleearth.unit.profession.Wizard;

public class WizardCavalry extends MilitaryCavalryman implements Wizard {
    public WizardCavalry(String name) {
        super(name, magicPower, magicPower);
        setHorseMount();
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
