package model;

import java.util.ArrayList;
import java.util.List;

public class VetLocation {
    private String name;
    private String address;
    private List<Vet> vets;

    public VetLocation(String name, String address) {
        this.name = name;
        this.address = address;
        this.vets = new ArrayList<Vet>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vet> getVets() {
        return vets;
    }

    public void setVets(List<Vet> vets) {
        this.vets = vets;
    }

    public void addVet(Vet vet) {
        vets.add(vet);
        vet.addLocation(name);
    }

    @Override
    public String toString() {
        return "VetLocation{" + "name='" + name + '\'' + ", address='" + address + '\'' + ", vets=" + vets + '}';
    }
}