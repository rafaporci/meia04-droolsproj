package org.engcia.model;

import org.engcia.network.NetworkDiscovery;

public class TransportationLane {
    private ServiceLevelType serviceLevelType;
    private LogisticPoint origin;
    private LogisticPoint destination;
    private String courier;

    public TransportationLane(LogisticPoint origin, LogisticPoint destination, String courier, ServiceLevelType serviceLevelType) {
        this.origin = origin;
        this.destination = destination;
        this.courier = courier;
        this.serviceLevelType = serviceLevelType;
    }

    public LogisticPoint getOrigin() {
        return origin;
    }

    public LogisticPoint getDestination() {
        return destination;
    }

    public String getCourier() {
        return courier;
    }

    public ServiceLevelType getServiceLevelType() {
        return serviceLevelType;
    }

    public String toString() {
        return (origin.toString() + " to " + destination.toString() + " by " + courier + "(" + serviceLevelType.toString() + ")" );
    }
}
