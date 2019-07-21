package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Case {

    private int id;
    private float sizeX;
    private float sizeY;
    private float sizeZ;

    public Case(int id, int sizeX, int sizeY, int sizeZ) {
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    public float getId() {
        return id;
    }

    public float getSizeX() {
        return sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public float getSizeZ() {
        return sizeZ;
    }

    public float getVolume(){
        return (sizeX * sizeY) * sizeZ;
    }

    public static Case getCaseById(List<Case> Cases, int Id){
        for (Case case_: Cases) {                            // Name "case" is not resolved
            if (case_.getId() == Id) return case_;
        }
        return null;
    }

    public static List<Case> addListOfCases(){
        Scanner in = new Scanner(System.in);
        List<Case> Cases = new ArrayList<Case>();
        while (true){
            System.out.println("Enter case parameters");
            System.out.print("Id: ");
            int id = in.nextInt();
            if(Case.getCaseById(Cases, id) != null){
                System.out.println("Case with id " + id + " already exist");
                continue;
            }
            System.out.print("sizeX: ");
            int sizeX = in.nextInt();
            System.out.print("sizeY: ");
            int sizeY = in.nextInt();
            System.out.print("sizeZ: ");
            int sizeZ = in.nextInt();
            Cases.add(new Case(id, sizeX, sizeY, sizeZ));
            System.out.println("One more?(y/n)");
            String decision = in.next();
            if (decision.equals("n")) break;
        }
        return Cases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Case)) return false;
        Case aCase = (Case) o;
        return id == aCase.id &&
                Float.compare(aCase.sizeX, sizeX) == 0 &&
                Float.compare(aCase.sizeY, sizeY) == 0 &&
                Float.compare(aCase.sizeZ, sizeZ) == 0;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", sizeZ=" + sizeZ +
                '}';
    }
}

