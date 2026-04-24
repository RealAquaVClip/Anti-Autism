package com.arc.antiautism;

public class Violation {
    private int vl;

    public void addVL(int amount) { vl += amount; }
    public void addVL() { vl++; }
    public void reset() { vl = 0; }
    public int getVL() { return vl; }
}