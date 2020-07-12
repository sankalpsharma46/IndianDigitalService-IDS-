package com.indian.digitalservices.drawer.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> Listing = new ArrayList<String>();
        Listing.add("Architecture");
        Listing.add("Electrical Designing");
        Listing.add("electronics");
        Listing.add("interiors");
        Listing.add("Instrument Testing Certification Service");
        Listing.add("It services");
        Listing.add("Mechanical Designing");

        expandableListDetail.put("Listings", Listing);
        return expandableListDetail;
    }
}
