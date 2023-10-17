package org.engcia.model;

public class Evidence extends Fact{
    public static final String ORIGIN_COUNTRY = "Fill the origin country";
    public static final String DESTINATION_COUNTRY = "Fill the destination country";
    public static final String DESTINATION_STATE = "Fill the destination state";
    private String evidence;
    private String value;

    public Evidence(String ev, String v) {
        evidence = ev;
        value = v;
    }

    public String getEvidence() {
        return evidence;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return (evidence + " = " + value);
    }

}

