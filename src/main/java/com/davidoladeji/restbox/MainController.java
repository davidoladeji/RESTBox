package com.davidoladeji.restbox;

import com.davidoladeji.restbox.model.Distance;
import com.davidoladeji.restbox.model.Distances;
import com.davidoladeji.restbox.model.Town;
import com.healthmarketscience.jackcess.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MainController {

    @RequestMapping(produces = {"text/xml"}, method = RequestMethod.GET)
    public
    @ResponseBody
    Distances<Distance> getAllDistances() throws IOException {


        Database db = DatabaseBuilder.open(new File("src/file/distanceDB.mdb"));
        Table table = db.getTable("Distance");
        List<Distance> distanceList = new ArrayList<>();

        for (Row row : table) {
            Distance distance = new Distance();

            distance.setId(row.getInt(("DistanceID")));
            distance.setMiles(row.getInt("DistanceInMiles"));
            distance.setTownone(row.getInt("Town1"));
            distance.setTowntwo(row.getInt(("Town2")));
            distanceList.add(distance);

        }

        Distances<Distance> distancesList = new Distances<Distance>(distanceList);


        return distancesList;
    }

    @RequestMapping(value = "calcdistance", produces = {"text/xml", "application/json"}, method = RequestMethod.GET)
    public
    @ResponseBody
    Distances calcdistance(@ModelAttribute(value = "Distances") Distances distances, @RequestParam(value = "dept", required = true) String townone, @RequestParam(value = "dest", required = true) String towntwo) throws IOException {

        Database db = DatabaseBuilder.open(new File("src/file/distanceDB.mdb"));
        Table table = db.getTable("Distance");

        int town1ID = Integer.parseInt(townone);
        int town2ID = Integer.parseInt(towntwo);

        Distances<Distance> distancesList = new Distances<>();
        Map<String, Integer> criteria = new HashMap<String, Integer>();

        /**
         * Allow switching the parameter arrangement depending on the
         * parameter entry
         */


        if (town1ID > town2ID) {
            criteria.put("Town1", town2ID);
            criteria.put("Town2", town1ID);
        } else if (town1ID < town2ID) {

            criteria.put("Town1", town1ID);
            criteria.put("Town2", town2ID);

        }


        /**
         * Search through the Table (Distance) and return row with given
         * Town IDs
         * Returns More than one distance if available
         */

        Row row = CursorBuilder.findRow(table, criteria);

        if (row != null) {
            Distance distance = new Distance();
            distance.setMiles(row.getInt(("DistanceInMiles")));
            distance.setTownone(town1ID);
            distance.setTowntwo(town2ID);
            distance.setId(row.getInt(("DistanceID")));

            List<Distance> distanceList = new ArrayList<>();
            distanceList.add(distance);
            distancesList = new Distances<Distance>(distanceList);

            System.out.println(String.format("Row found: ID=%d.", row.get("DistanceID")));
            return distancesList;
        } else if (row == null) {
            System.out.println("Row not found");
            Distance distance = new Distance();
            distance.setMiles(0);
            distance.setTownone(0);
            distance.setTowntwo(0);
            distance.setId(0);
            List<Distance> distanceList = new ArrayList<>();
            distanceList.add(distance);
            distancesList = new Distances<Distance>(distanceList);
            return distancesList;
        } else {
            System.out.println("Some other funny error");
            Distance distance = new Distance();
            distance.setMiles(0);
            distance.setTownone(0);
            distance.setTowntwo(0);
            distance.setId(0);
            List<Distance> distanceList = new ArrayList<>();
            distanceList.add(distance);
            distancesList = new Distances<Distance>(distanceList);
            return distancesList;
        }

    }

    @RequestMapping(value = "alltowns", produces = {"text/xml", "application/json"}, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Town> getAllTowns() throws IOException {
        List<Town> townList = new ArrayList<Town>();

        Database db = DatabaseBuilder.open(new File("src/file/distanceDB.mdb"));
        Table table = db.getTable("Town");
        for (Row row : table) {
            Town town = new Town();
            town.setId(row.getInt(("TownID")));
            town.setName(row.getString("Town"));
            townList.add(town);
        }
        return townList;
    }
}
