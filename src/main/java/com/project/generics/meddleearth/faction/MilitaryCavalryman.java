package com.project.generics.meddleearth.faction;

import com.project.generics.meddleearth.unit.mount.Horse;
import com.project.generics.meddleearth.unit.mount.Warg;

public abstract class MilitaryCavalryman extends Military {
    private Mount mount;
    public MilitaryCavalryman(String name, int minPower, int maxPower) {
        super(name, minPower, maxPower);
    }

    @Override
    public <T extends Military> void strike(T unit) {
        int multiPower = this.power + mount.power;
        if (unit instanceof MilitaryCavalryman) {
            Mount mount = ((MilitaryCavalryman) unit).getMount();
            if (mount.power > 0) {
                mount.power -= multiPower;
                mount.power = Math.max(mount.power, 0);
            } else {
                unit.power -= multiPower;
                unit.power = Math.max(unit.power, 0);
            }
        } else {
            unit.power -= multiPower;
            unit.power = Math.max(unit.power, 0);
        }
    }

    protected void setHorseMount() {
        this.mount = new HandHorse();
    }

    protected void setWargMount() {
        this.mount = new HandWarg();
    }

    public Mount getMount() {
        return mount;
    }

    static class HandHorse extends Mount implements Horse {
        public HandHorse() {
            super(minPower, maxPower);
        }

        @Override
        public boolean isAlive() {
            return power > 0;
        }
    }

    static class HandWarg extends Mount implements Warg {
        public HandWarg() {
            super(minPower, maxPower);
        }

        @Override
        public boolean isAlive() {
            return power > 0;
        }
    }
}
