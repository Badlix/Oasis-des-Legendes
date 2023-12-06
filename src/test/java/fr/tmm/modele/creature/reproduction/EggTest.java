package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.enclosure.Enclosure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

class EggTest {

    Egg egg;
    Dragon mother;
    Enclosure enclos;

    @BeforeEach
    void init() {
        this.mother = new Dragon("dragon","f",500,50,40);
        this.enclos = new Enclosure("Enclos",5,5);
        this.enclos.addCreature(mother);
        this.egg = new Egg(this.mother);
        this.mother.getListener().onEggLaying(this.egg);
    }

    @Test
    void eggType() {
        assertEquals(this.egg.getType(), "Dragon");
        assertEquals(this.egg.getListener(), this.mother.getListener());
        assertEquals(this.enclos.getEggWaitingToHatch().size(), 1);
    }

    @Test
    void hatching() throws NoSuchFieldException, IllegalAccessException {
        Field timeBeforeHatch = Egg.class.getDeclaredField("timeBeforeHatching");
        timeBeforeHatch.setAccessible(true);
        timeBeforeHatch.set(this.egg, 1);
        sleep(4);
        // remove from eggWaiting list
        assertEquals(0, enclos.getEggWaitingToHatch().size());
        // counter before hatching is equal to O
        assertEquals(0,timeBeforeHatch.get(this.egg));
        // baby has been addeed to the creature list of the enclosure
        assertEquals(2, this.enclos.getCreaturesPresent().size());
        // the baby is indeed a baby
        assertEquals(this.enclos.getCreaturesPresent().get(1).getAge(), 0);
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}