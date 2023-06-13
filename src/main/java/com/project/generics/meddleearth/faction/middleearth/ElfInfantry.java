package com.project.generics.meddleearth.faction.middleearth;

import com.project.generics.meddleearth.faction.Military;
import com.project.generics.meddleearth.unit.profession.Infantry;
import com.project.generics.meddleearth.unit.race.Elf;

public class ElfInfantry extends Military implements Elf, Infantry {
    public ElfInfantry(String name) {
        super(name, minPower, maxPower);
    }

    @Override
    public boolean isAlive() {
        return power > 0;
    }
}
