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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.junit.Test;

import boulder.runner.api.BoulderRunnerController;

/**
 * Unit tests for the BoulderRunnerController. As these are unit tests,
 * no Spring testing will be done.
 * 
 * @author Kniggit
 */
public class BoulderRunnerControllerTest {

    // Hardcoded trails for these tests
    private JSONArray testTrails = new JSONArray("[{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"621 Flagstaff Summit Rd\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"12\",\"THLeash\":\"Yes\",\"AccessName\":\"Flagstaff Summit West\",\"FISHING\":\"No\",\"PICNIC\":\"Yes\",\"TrashCans\":\"4.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Moderate\",\"FID\":\"0\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Asphalt\",\"Fee\":\"Yes\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\"Wood Shelter\",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"No\",\"AccessID\":\"279\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"Yes\",\"DateTo\":\"2099-12-31 00:00:00\"},{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"790 Flagstaff Summit Rd\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"56\",\"THLeash\":\"Yes\",\"AccessName\":\"Flagstaff Summit East\",\"FISHING\":\"No\",\"PICNIC\":\"Yes\",\"TrashCans\":\"6.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Difficult\",\"FID\":\"1\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Asphalt\",\"Fee\":\"Yes\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\"Nature Center\",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"Yes\",\"AccessID\":\"277\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"Yes\",\"DateTo\":\"2099-12-31 00:00:00\"},{\"ADAcamping\":\"No\",\"ADAparking\":\"No\",\"DogCompost\":\"No\",\"Address\":\"4705 95th St\",\"RESTROOMS\":\"No\",\"ADApicnic\":\"No\",\"ParkSpaces\":\"6\",\"THLeash\":\"Yes\",\"AccessName\":\"East Boulder Trail at White Rocks\",\"FISHING\":\"No\",\"PICNIC\":\"No\",\"TrashCans\":\"1.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"No\",\"FID\":\"2\",\"ADAfishing\":\"No\",\"DogTube\":\"0.0\",\"ADAsurface\":\"No\",\"Fee\":\"No\",\"RecycleBin\":\"No\",\"Grills\":\"No\",\"BikeTrail\":\"Yes\",\"ADAfacName\":\" \",\"ADAtoilet\":\"No\",\"BikeRack\":\"Yes\",\"AccessID\":\"502a\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T1\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"No\",\"DateTo\":\"2099-12-31 00:00:00\"},{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"2500 Left-hand Canyon Dr\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"35\",\"THLeash\":\"Yes\",\"AccessName\":\"Buckingham Park\",\"FISHING\":\"Yes\",\"PICNIC\":\"Yes\",\"TrashCans\":\"2.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Difficult\",\"FID\":\"3\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Crusher Fines\",\"Fee\":\"No\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\" \",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"Yes\",\"AccessID\":\"417\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Possible\",\"ADAfacilit\":\"No\",\"DateTo\":\"2099-12-31 00:00:00\"}]");

    /**
     * Test to verify given no parameters, all trails are returned
     * @throws Exception
     */
    @Test
    public void noParamsShouldReturnAllTrails() throws Exception {

        // Mock out the part of the Controller that pulls the list of
    	// hiking trails
        BoulderRunnerController btc = spy(new BoulderRunnerController());
        when(btc.getTrails()).thenReturn(testTrails);

    	// Expected trails
    	String expectedResults = "[{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"621 Flagstaff Summit Rd\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"12\",\"THLeash\":\"Yes\",\"AccessName\":\"Flagstaff Summit West\",\"FISHING\":\"No\",\"PICNIC\":\"Yes\",\"TrashCans\":\"4.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Moderate\",\"FID\":\"0\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Asphalt\",\"Fee\":\"Yes\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\"Wood Shelter\",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"No\",\"AccessID\":\"279\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"Yes\",\"DateTo\":\"2099-12-31 00:00:00\"},{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"790 Flagstaff Summit Rd\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"56\",\"THLeash\":\"Yes\",\"AccessName\":\"Flagstaff Summit East\",\"FISHING\":\"No\",\"PICNIC\":\"Yes\",\"TrashCans\":\"6.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Difficult\",\"FID\":\"1\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Asphalt\",\"Fee\":\"Yes\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\"Nature Center\",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"Yes\",\"AccessID\":\"277\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"Yes\",\"DateTo\":\"2099-12-31 00:00:00\"},{\"ADAcamping\":\"No\",\"ADAparking\":\"No\",\"DogCompost\":\"No\",\"Address\":\"4705 95th St\",\"RESTROOMS\":\"No\",\"ADApicnic\":\"No\",\"ParkSpaces\":\"6\",\"THLeash\":\"Yes\",\"AccessName\":\"East Boulder Trail at White Rocks\",\"FISHING\":\"No\",\"PICNIC\":\"No\",\"TrashCans\":\"1.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"No\",\"FID\":\"2\",\"ADAfishing\":\"No\",\"DogTube\":\"0.0\",\"ADAsurface\":\"No\",\"Fee\":\"No\",\"RecycleBin\":\"No\",\"Grills\":\"No\",\"BikeTrail\":\"Yes\",\"ADAfacName\":\" \",\"ADAtoilet\":\"No\",\"BikeRack\":\"Yes\",\"AccessID\":\"502a\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T1\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"No\",\"DateTo\":\"2099-12-31 00:00:00\"},{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"2500 Left-hand Canyon Dr\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"35\",\"THLeash\":\"Yes\",\"AccessName\":\"Buckingham Park\",\"FISHING\":\"Yes\",\"PICNIC\":\"Yes\",\"TrashCans\":\"2.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Difficult\",\"FID\":\"3\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Crusher Fines\",\"Fee\":\"No\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\" \",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"Yes\",\"AccessID\":\"417\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Possible\",\"ADAfacilit\":\"No\",\"DateTo\":\"2099-12-31 00:00:00\"}]";

    	// Assert
    	assertEquals(btc.trails(null), expectedResults);
    }

    /**
     * Test to verify given a key/value parameter unique for 1 trail,
     * that individual trail is returned
     * 
     * @throws Exception
     */
    @Test
    public void oneParamShouldReturnMatchingTrails() throws Exception {

        // Mock out the part of the Controller that pulls the list of
    	// hiking trails
        BoulderRunnerController btc = spy(new BoulderRunnerController());
        when(btc.getTrails()).thenReturn(testTrails);
        
        // Construct the query parameters
    	Map<String, String> searchParams = new HashMap<String, String>();
    	searchParams.put("Address", "621 Flagstaff Summit Rd");
    	
    	// Expected trails
    	String expectedResults = "[{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"621 Flagstaff Summit Rd\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"12\",\"THLeash\":\"Yes\",\"AccessName\":\"Flagstaff Summit West\",\"FISHING\":\"No\",\"PICNIC\":\"Yes\",\"TrashCans\":\"4.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Moderate\",\"FID\":\"0\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Asphalt\",\"Fee\":\"Yes\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\"Wood Shelter\",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"No\",\"AccessID\":\"279\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"Yes\",\"DateTo\":\"2099-12-31 00:00:00\"}]";
    	
    	// Assert
    	assertEquals(btc.trails(searchParams), expectedResults);
    }
    
    /**
     * Test to verify given a key/value parameter common between
     * 3 trails, those 3 trails are returned
     * 
     * @throws Exception
     */
    @Test
    public void oneParamShouldReturnThreeMatchingTrails() throws Exception {

        // Mock out the part of the Controller that pulls the list of
    	// hiking trails
        BoulderRunnerController btc = spy(new BoulderRunnerController());
        when(btc.getTrails()).thenReturn(testTrails);
        
        // Construct the query parameters
    	Map<String, String> searchParams = new HashMap<String, String>();
    	searchParams.put("BikeTrail", "No");
    	
    	// Expected trails
    	String expectedResults = "[{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"621 Flagstaff Summit Rd\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"12\",\"THLeash\":\"Yes\",\"AccessName\":\"Flagstaff Summit West\",\"FISHING\":\"No\",\"PICNIC\":\"Yes\",\"TrashCans\":\"4.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Moderate\",\"FID\":\"0\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Asphalt\",\"Fee\":\"Yes\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\"Wood Shelter\",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"No\",\"AccessID\":\"279\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"Yes\",\"DateTo\":\"2099-12-31 00:00:00\"},{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"790 Flagstaff Summit Rd\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"56\",\"THLeash\":\"Yes\",\"AccessName\":\"Flagstaff Summit East\",\"FISHING\":\"No\",\"PICNIC\":\"Yes\",\"TrashCans\":\"6.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Difficult\",\"FID\":\"1\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Asphalt\",\"Fee\":\"Yes\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\"Nature Center\",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"Yes\",\"AccessID\":\"277\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"Yes\",\"DateTo\":\"2099-12-31 00:00:00\"},{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"2500 Left-hand Canyon Dr\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"35\",\"THLeash\":\"Yes\",\"AccessName\":\"Buckingham Park\",\"FISHING\":\"Yes\",\"PICNIC\":\"Yes\",\"TrashCans\":\"2.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Difficult\",\"FID\":\"3\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Crusher Fines\",\"Fee\":\"No\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\" \",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"Yes\",\"AccessID\":\"417\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Possible\",\"ADAfacilit\":\"No\",\"DateTo\":\"2099-12-31 00:00:00\"}]";
    	
    	// Assert
    	assertEquals(btc.trails(searchParams), expectedResults);
    }
    
    /**
     * Test to verify given 2 key/value parameters common between
     * 2 trails, those 2 trails are returned
     * 
     * @throws Exception
     */
    @Test
    public void twoParamsShouldReturnTwoMatchingTrails() throws Exception {

        // Mock out the part of the Controller that pulls the list of
    	// hiking trails
        BoulderRunnerController btc = spy(new BoulderRunnerController());
        when(btc.getTrails()).thenReturn(testTrails);
        
        // Construct the query parameters
    	Map<String, String> searchParams = new HashMap<String, String>();
    	searchParams.put("ADAcamping", "No");
    	searchParams.put("Fee", "No");
    	
    	// Expected trails
    	String expectedResults = "[{\"ADAcamping\":\"No\",\"ADAparking\":\"No\",\"DogCompost\":\"No\",\"Address\":\"4705 95th St\",\"RESTROOMS\":\"No\",\"ADApicnic\":\"No\",\"ParkSpaces\":\"6\",\"THLeash\":\"Yes\",\"AccessName\":\"East Boulder Trail at White Rocks\",\"FISHING\":\"No\",\"PICNIC\":\"No\",\"TrashCans\":\"1.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"No\",\"FID\":\"2\",\"ADAfishing\":\"No\",\"DogTube\":\"0.0\",\"ADAsurface\":\"No\",\"Fee\":\"No\",\"RecycleBin\":\"No\",\"Grills\":\"No\",\"BikeTrail\":\"Yes\",\"ADAfacName\":\" \",\"ADAtoilet\":\"No\",\"BikeRack\":\"Yes\",\"AccessID\":\"502a\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T1\",\"HorseTrail\":\"Not Recommended\",\"ADAfacilit\":\"No\",\"DateTo\":\"2099-12-31 00:00:00\"},{\"ADAcamping\":\"No\",\"ADAparking\":\"Yes\",\"DogCompost\":\"No\",\"Address\":\"2500 Left-hand Canyon Dr\",\"RESTROOMS\":\"Yes\",\"ADApicnic\":\"Yes\",\"ParkSpaces\":\"35\",\"THLeash\":\"Yes\",\"AccessName\":\"Buckingham Park\",\"FISHING\":\"Yes\",\"PICNIC\":\"Yes\",\"TrashCans\":\"2.0\",\"AKA\":\" \",\"AccessType\":\"TH\",\"ADAtrail\":\"Difficult\",\"FID\":\"3\",\"ADAfishing\":\"No\",\"DogTube\":\"1.0\",\"ADAsurface\":\"Crusher Fines\",\"Fee\":\"No\",\"RecycleBin\":\"Yes\",\"Grills\":\"Yes\",\"BikeTrail\":\"No\",\"ADAfacName\":\" \",\"ADAtoilet\":\"Yes\",\"BikeRack\":\"Yes\",\"AccessID\":\"417\",\"DateFrom\":\"2005-12-31 00:00:00\",\"Class\":\"T3\",\"HorseTrail\":\"Possible\",\"ADAfacilit\":\"No\",\"DateTo\":\"2099-12-31 00:00:00\"}]";
    	
    	// Assert
    	assertEquals(btc.trails(searchParams), expectedResults);
    }
    
    /**
     * Test to verify given a key/value parameter that matches no trails,
     * an empty set is returned
     * 
     * @throws Exception
     */
    @Test
    public void nonMatchingParamsShouldReturnNoTrails() throws Exception {

        // Mock out the part of the Controller that pulls the list of
    	// hiking trails
        BoulderRunnerController btc = spy(new BoulderRunnerController());
        when(btc.getTrails()).thenReturn(testTrails);
        
        // Construct the query parameters
    	Map<String, String> searchParams = new HashMap<String, String>();
    	searchParams.put("Grills", "Perhaps there are some");
    	
    	// Expected trails
    	String expectedResults = "[]";
    	
    	// Assert
    	assertEquals(btc.trails(searchParams), expectedResults);
    }
}