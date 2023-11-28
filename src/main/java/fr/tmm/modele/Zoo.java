package fr.tmm.modele;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Megalodon;
import fr.tmm.modele.creature.species.Phenix;
import fr.tmm.modele.creature.species.Unicorn;
import fr.tmm.modele.enclosure.Aquarium;
import fr.tmm.modele.enclosure.Aviary;
import fr.tmm.modele.enclosure.Enclosure;

import java.util.ArrayList;

public class Zoo {

    private String name;
    private ZooMaster zooMaster;
    final private int NB_MAX_ENCLOSURE = 10;
    private ArrayList<Enclosure> enclosures;
    private static Zoo INSTANCE;

    private Zoo() {
        temporaryInit();
    }

    public static Zoo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Zoo();
        }
        return INSTANCE;
    }

    private void temporaryInit() {
        Enclosure enclos1 = new Enclosure("Enclos 1", 50, 5);
        Aquarium aquarium1 = new Aquarium("Aquarium 1", 50, 5, 20, 4);
        Aviary voiliere1 = new Aviary("Voiliere 1", 50, 7);
        enclos1.addCreature(new Unicorn("Licorne 1", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 2", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 3", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 1", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 2", "m", 10, 10, 10));
        voiliere1.addCreature(new Phenix("Phenix 1", "m", 10, 10, 10));
        this.INSTANCE.addAnEnclosure(enclos1);
        this.INSTANCE.addAnEnclosure(aquarium1);
        this.INSTANCE.addAnEnclosure(voiliere1);
    }

    // NAME

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    // ZOO MASTER

    public ZooMaster getZooMaster() {
        return this.zooMaster;
    }

    // NB MAX ENCLOSURE

    public int getNbMaxEnclosure() {
        return this.NB_MAX_ENCLOSURE;
    }

    // Enclosures

    public ArrayList<Enclosure> getEnclosures() {
        return this.enclosures;
    }

    public void setEnclosures(ArrayList<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

    public int addAnEnclosure(Enclosure enclosure) {
        if (this.enclosures.size() < NB_MAX_ENCLOSURE) {
            this.enclosures.add(enclosure);
            return 0;
        }
        return -1;
    }

    public ArrayList<Creature> getAllCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        for (Enclosure enclo : this.enclosures) {
            creatures.addAll(enclo.getCreaturesPresent());
        }
        return creatures;
    }
}
