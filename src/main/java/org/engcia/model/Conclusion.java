package org.engcia.model;

import org.engcia.network.NetworkDiscovery;

public class Conclusion extends Fact{
    private TradeLane tradeLane;

    public Conclusion(TradeLane tradeLane) {
        this.tradeLane = tradeLane;
        NetworkDiscovery.agendaEventListener.addRhs(this);
    }

    public TradeLane getTradeLane() {
        return tradeLane;
    }

    public String toString() {
        return ("Conclusion: " + tradeLane.toString());
    }

}
