package com.project.generics.meddleearth;

import com.project.generics.meddleearth.faction.Military;
import com.project.generics.meddleearth.faction.middleearth.ElfInfantry;
import com.project.generics.meddleearth.faction.middleearth.HumanCavalry;
import com.project.generics.meddleearth.faction.middleearth.HumanInfantry;
import com.project.generics.meddleearth.faction.middleearth.RohhirimCavalry;
import com.project.generics.meddleearth.faction.middleearth.WizardCavalry;
import com.project.generics.meddleearth.faction.middleearth.WoodenElfInfantry;
import com.project.generics.meddleearth.faction.mordor.GoblinInfantry;
import com.project.generics.meddleearth.faction.mordor.OrcCavalry;
import com.project.generics.meddleearth.faction.mordor.OrcInfantry;
import com.project.generics.meddleearth.faction.mordor.TrollInfantry;
import com.project.generics.meddleearth.faction.mordor.UrukHaiCavalry;
import com.project.generics.meddleearth.faction.mordor.UrukHaiInfantry;
import com.project.generics.meddleearth.unit.MiddleEarthUnit;
import com.project.generics.meddleearth.unit.MordorUnit;
import com.project.generics.meddleearth.unit.Unit;
import com.project.generics.meddleearth.unit.profession.Cavalry;
import com.project.generics.meddleearth.unit.profession.Infantry;

import java.util.List;
import java.util.Random;

public class Battle {
    private static final String NAME = "Soldier №";
    private static final String STRIKE = "%s strikes %s and %s\n";
    private static final String KILL = "kills him";
    private static final String NOT_KILL = "does not kill him";
    private static final Random random = new Random();
    public static void fight() {
        Army<MiddleEarthUnit> middleEarthArmy = new Army<>();
        Army<MordorUnit> mordorArmy = new Army<>();
        generateArmy(middleEarthArmy, mordorArmy);

        System.out.println("\nPhase 1:");
        battleOfCavalry(middleEarthArmy, mordorArmy);

        System.out.println("\nPhase 2:");
        battleOfInfantry(middleEarthArmy, mordorArmy);

        List<MiddleEarthUnit> listMiddleEarthArmy = middleEarthArmy.getArmy();
        List<MordorUnit> listMordorArmy = mordorArmy.getArmy();

        if (!listMiddleEarthArmy.isEmpty() && !listMordorArmy.isEmpty()) {
            System.out.println("\nPhase 3:");
            battleProcess(middleEarthArmy, mordorArmy, listMiddleEarthArmy, listMordorArmy);
        }

        if (!middleEarthArmy.getArmy().isEmpty())
            winnerInfo(listMiddleEarthArmy);
        else
            winnerInfo(listMordorArmy);
    }

    private static void battleOfCavalry(Army<MiddleEarthUnit> middleEarthArmy, Army<MordorUnit> mordorArmy) {
        List<MiddleEarthUnit> middleEarthCavalryArmy = middleEarthArmy.getCavalry();
        List<MordorUnit> mordorCavalryArmy = mordorArmy.getCavalry();

        battleProcess(middleEarthArmy, mordorArmy, middleEarthCavalryArmy, mordorCavalryArmy);
        winnerInfo(middleEarthCavalryArmy.isEmpty() ? mordorCavalryArmy : middleEarthCavalryArmy, 1);
    }

    private static void battleOfInfantry(Army<MiddleEarthUnit> middleEarthArmy, Army<MordorUnit> mordorArmy) {
        List<MiddleEarthUnit> middleEarthInfantryArmy = middleEarthArmy.getInfantry();
        List<MordorUnit> mordorInfantryArmy = mordorArmy.getInfantry();

        battleProcess(middleEarthArmy, mordorArmy, middleEarthInfantryArmy, mordorInfantryArmy);
        winnerInfo(middleEarthInfantryArmy.isEmpty() ? mordorInfantryArmy : middleEarthInfantryArmy, 2);
    }

    private static void battleProcess(Army<MiddleEarthUnit> middleEarthArmy, Army<MordorUnit> mordorArmy, List<MiddleEarthUnit> middleEarthTypeArmy, List<MordorUnit> mordorTypeArmy) {
        while (!middleEarthTypeArmy.isEmpty() && !mordorTypeArmy.isEmpty()) {
            MiddleEarthUnit middleEarthUnit = middleEarthArmy.getRandomUnit(middleEarthTypeArmy.get(0));
            MordorUnit mordorUnit = mordorArmy.getRandomUnit(mordorTypeArmy.get(0));

            if (random.nextBoolean()) {
                duel((Military) middleEarthUnit, (Military) mordorUnit);
                if (!mordorUnit.isAlive()) {
                    mordorArmy.release(mordorUnit);
                    mordorTypeArmy.remove(mordorUnit);
                } else if (!middleEarthUnit.isAlive()) {
                    middleEarthArmy.release(middleEarthUnit);
                    middleEarthTypeArmy.remove(middleEarthUnit);
                }
            } else {
                duel((Military) mordorUnit, (Military) middleEarthUnit);
                if (!middleEarthUnit.isAlive()) {
                    middleEarthArmy.release(middleEarthUnit);
                    middleEarthTypeArmy.remove(middleEarthUnit);
                } else if (!mordorUnit.isAlive()) {
                    mordorArmy.release(mordorUnit);
                    mordorTypeArmy.remove(mordorUnit);
                }
            }
        }
    }

    private static void duel(Military attacker, Military defensive) {
        if ((defensive instanceof Cavalry) && (attacker instanceof Infantry)) {
            Military portable = attacker;
            attacker = defensive;
            defensive = portable;
        }
        String statsBeforeAttack = defensive.toString();
        attacker.strike(defensive);

        if (defensive.isAlive()) {
            System.out.printf(STRIKE, attacker, statsBeforeAttack, NOT_KILL);

            statsBeforeAttack = attacker.toString();
            defensive.strike(attacker);
            if (attacker.isAlive())
                System.out.printf(STRIKE, defensive, statsBeforeAttack, NOT_KILL);
            else
                System.out.printf(STRIKE, defensive, statsBeforeAttack, KILL);
        } else {
            System.out.printf(STRIKE, attacker, statsBeforeAttack, KILL);
        }
        System.out.println("----------");
    }

    private static void winnerInfo(List<? extends Unit> phaseWinner, int phase) {
        String resultFormat = "\nArmy of %s has won the %d phase. The winners list:\n";
        System.out.printf(resultFormat, phaseWinner.get(0) instanceof MiddleEarthUnit ? "MiddleEarth" : "Mordor", phase);

        for (Unit unit : phaseWinner)
            System.out.println(unit.toString());
        System.out.printf("Оставшихся в живых %d\n", phaseWinner.size());
    }

    private static void winnerInfo(List<? extends Unit> winnerAllBattle) {
        String resultFormat = "Army of %s has won the battle. The winners list:\n";
        System.out.printf(resultFormat, winnerAllBattle.get(0) instanceof MiddleEarthUnit ? "MiddleEarth" : "Mordor");

        for (Unit unit : winnerAllBattle)
            System.out.println(unit.toString());
        System.out.printf("Оставшихся в живых %d\n", winnerAllBattle.size());
    }

    private static void generateArmy(Army<MiddleEarthUnit> middleEarthArmy, Army<MordorUnit> mordorArmy) {
        int armyCount = random.nextInt(10, 40);
        int difCount = (int) (armyCount * 0.20);
        int countMiddleEarthArmy = random.nextInt(armyCount - difCount, armyCount + difCount);
        int countMordorArmy = random.nextInt(armyCount - difCount, armyCount + difCount);

        generateMiddleEarthArmy(middleEarthArmy, countMiddleEarthArmy);
        System.out.println("Army of MiddleEarth consists of:");
        middleEarthArmy.print();

        generateMordorArmy(mordorArmy, countMordorArmy);
        System.out.println("\nArmy of Mordor consists of:");
        mordorArmy.print();
    }

    public static void generateMiddleEarthArmy(Army<MiddleEarthUnit> middleEarthArmy, int armyCount) {
        if (armyCount % 2 == 0)
            middleEarthArmy.recruit(new WizardCavalry("Гендальф"));
        else
            ++armyCount;
        for (int m = 1; m < armyCount ; m++) {
            int militaryCode = random.nextInt(0, 5);

            if (militaryCode == 0)
                middleEarthArmy.recruit(new ElfInfantry(NAME + m));
            else if (militaryCode == 1)
                middleEarthArmy.recruit(new HumanCavalry(NAME + m));
            else if (militaryCode == 2)
                middleEarthArmy.recruit(new HumanInfantry(NAME + m));
            else if (militaryCode == 3)
                middleEarthArmy.recruit(new RohhirimCavalry(NAME + m));
            else if (militaryCode == 4)
                middleEarthArmy.recruit(new WoodenElfInfantry(NAME + m));
        }
    }

    private static void generateMordorArmy(Army<MordorUnit> mordorArmy, int armyCount) {
        for (int m = 1; m < armyCount + 1; m++) {
            int militaryCode = random.nextInt(0, 6);

            if (militaryCode == 0)
                mordorArmy.recruit(new GoblinInfantry(NAME + m));
            else if (militaryCode == 1)
                mordorArmy.recruit(new OrcCavalry(NAME + m));
            else if (militaryCode == 2)
                mordorArmy.recruit(new OrcInfantry(NAME + m));
            else if (militaryCode == 3)
                mordorArmy.recruit(new TrollInfantry(NAME + m));
            else if (militaryCode == 4)
                mordorArmy.recruit(new UrukHaiCavalry(NAME + m));
            else if (militaryCode == 5)
                mordorArmy.recruit(new UrukHaiInfantry(NAME + m));
        }
    }
}
