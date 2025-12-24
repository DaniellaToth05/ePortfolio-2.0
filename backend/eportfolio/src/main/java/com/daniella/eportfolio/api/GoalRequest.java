package com.daniella.eportfolio.api;

public class GoalRequest {
    private int id;
    private String name;
    private double target;
    private double current;


    public GoalRequest() {}

    public double getCurrent() { return current; }
    public void setCurrent(double current) { this.current = current; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getTarget() { return target; }
    public void setTarget(double target) { this.target = target; }

}
