package org.engcia.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

import org.kie.api.runtime.ClassObjectFilter;

import org.engcia.network.NetworkDiscovery;
import org.engcia.model.Evidence;
import org.engcia.model.LogisticPoint;

public class UI {
    private static BufferedReader br;

    public static void uiInit() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void uiClose() {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean answer(String ev, String v) {
        @SuppressWarnings("unchecked")
        Collection<Evidence> evidences = (Collection<Evidence>) NetworkDiscovery.KS.getObjects(new ClassObjectFilter(Evidence.class));
        boolean questionFound = false;
        Evidence evidence = null;
        for (Evidence e: evidences) {
            if (e.getEvidence().compareTo(ev) == 0) {
                questionFound = true;
                evidence = e;
                break;
            }
        }
        if (questionFound) {
            if (evidence.getValue().compareTo(v) == 0) {
                NetworkDiscovery.agendaEventListener.addLhs(evidence);
                return true;
            } else {
                // Clear LHS conditions set if a condition is false (conjunctive rules)
                NetworkDiscovery.agendaEventListener.resetLhs();
                return false;
            }
        }
        System.out.print(ev + "? ");
        String value = readLine();

        Evidence e = new Evidence(ev, value);
        NetworkDiscovery.KS.insert(e);

        if (Arrays.asList(v.split(",")).contains(value)) {
            NetworkDiscovery.agendaEventListener.addLhs(e);
            return true;
        } else {
            // Clear LHS conditions set if a condition is false (conjunctive rules)
            NetworkDiscovery.agendaEventListener.resetLhs();
            return false;
        }
    }

    public static String getPreviousAnswer(String ev) {
        @SuppressWarnings("unchecked")
        Collection<Evidence> evidences = (Collection<Evidence>) NetworkDiscovery.KS.getObjects(new ClassObjectFilter(Evidence.class));
        for (Evidence e: evidences) {
            if (e.getEvidence().compareTo(ev) == 0) {
                return e.getValue();
            }
        }

        return "";
    }

    public static LogisticPoint getOriginCountry() {
        return new LogisticPoint(getPreviousAnswer(Evidence.ORIGIN_COUNTRY));
    }

    public static LogisticPoint getDestinationCountry() {
        return new LogisticPoint(getPreviousAnswer(Evidence.DESTINATION_COUNTRY));
    }

    public static LogisticPoint getDestinationState() {
        return new LogisticPoint(getPreviousAnswer(Evidence.DESTINATION_STATE));
    }

    private static String readLine() {
        String input = "";

        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

}
