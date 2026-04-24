package com.arc.antiautism.checks;

import com.arc.antiautism.Check;
import com.arc.antiautism.checks.combat.*;
import com.arc.antiautism.checks.movement.*;
import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private final List<Check> checks = new ArrayList<>();

    public CheckManager() {
        addCheck(new KillAuraA());
        addCheck(new ReachA());
        addCheck(new AutoClickerA());
        addCheck(new SpeedA());
        addCheck(new FlyA());
        addCheck(new ScaffoldA());
    }
    
    private void addCheck(Check check) { checks.add(check); }
    public List<Check> getChecks() { return checks; }
}