/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package boulder.runner.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class defines and implements the endpoint that provides
 * accessibility to the City of Boulder trail information
 * 
 * @author Kniggit
 */
@RestController
public class BoulderRunnerController {

    private static URL defaultBoulderTrailsFile = null;
    private JSONArray trails = new JSONArray();

    /**
     * Method called on initialization that downloads the latest trail head
     * data and places it into memory.
     */
    @PostConstruct
    public void initialize() {
        // Grab the trail information and load it into memory
        try {
            defaultBoulderTrailsFile = new URL("https://www-static.bouldercolorado.gov/docs/opendata/OSMPTrailheads.csv");
            Reader reader = new InputStreamReader(defaultBoulderTrailsFile.openStream());
            CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
            CSVParser parser = new CSVParser(reader, format);
            
            // Read all records and put them in a JSONArray that will then be
            // added to the Table
            for(CSVRecord record : parser) {
                // Convert this record to a JSONObject so it can be returned
                // in a nicer format
                JSONObject trailObject = new JSONObject();
                Map<String, String> kvp = record.toMap();
                for(Entry<String, String> entry : kvp.entrySet()) {
                    trailObject.put(entry.getKey(), entry.getValue());
                }
                
                // Add this JSONObject to the JSONArray
                trails.put(trailObject);
            }

            // Close connection, clean up
            parser.close();
        } catch (IOException e) {
            // No logging system in place. Write to STDERR
            System.err.println(e.getMessage());
        }
    }

    /**
     * Given search parameters, this endpoint will return all trails
     * that match the search parameters.
     * @param searchParams
     * @return 
     */
    @RequestMapping("/trails")
    public String trails(@RequestParam Map<String, String> searchParams) {
        
        // Find the trails that match
        // If no search parameters are passed in, use an empty one
        if(searchParams == null) {
            searchParams = new HashMap<String, String>();
        }
        JSONArray results = findTrails(searchParams);
        
        // Return the results as a String
        return results.toString();
    }
    
    /**
     * This helper method takes a Map of parameters where:
     * Key = Trail Attribute
     * Value = Value of Trail Attribute
     * 
     * It returns a JSONArray of all trails matching the parameters
     * NOTE: It only handles "AND" , no "OR" operations at this time.
     * NOTE: THERE IS NO SANITIZING OF USER INPUT!!!
     * @param searchParams
     * @return
     */
    private JSONArray findTrails(Map<String, String> searchParams) {
        JSONArray matchingTrails = new JSONArray();

        // Search through the JSONArray for all JSONObjects that
        // match the searchParams
        Iterator<Object> it = getTrails().iterator();
        while(it.hasNext()) {
            JSONObject trail = (JSONObject)it.next();
            
            // Loop through each entry in the searchParams and if there
            // is not a match at any point, move on. Otherwise, add this
            // to the list of trails that will be returned to the caller
            // NOTE: If there are no search parameters, ALL ENTRIES WILL BE RETURNED
            boolean match = true;
            for(Entry<String, String> entry : searchParams.entrySet()) {
                if(trail.has(entry.getKey()) && trail.getString(entry.getKey()).compareTo(entry.getValue()) == 0) {
                    // Match, keep going
                } else {
                    // No match, skip this trail
                    match = false;
                    break;
                }
            }
            
            if(match) {
                matchingTrails.put(trail);
            }
        }
        // Return the results
        return matchingTrails;
    }

    /**
     * Getter for the Trails
     * @return
     */
    public JSONArray getTrails() {
        return trails;
    }

    /**
     * Setter for the Trails
     * @param trails
     */
    public void setTrails(JSONArray trails) {
        this.trails = trails;
    }
}