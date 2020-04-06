package Arezzo.Controllers;

import Arezzo.Backend.Model;

import java.util.Observer;

public abstract class Controller implements Observer
{
    protected Model model;

    public Controller() {}

    public void setModel(Model m)
    {
        model = m;
    }
}
