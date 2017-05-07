import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
////////////////////////////////////////////////////
//Class: CS 361
//Authors: Philip Kocol
//
//
//Description: Server for the Chronotimer system.
//
//This server opens on port 8000 of localhost and
//updates race tables when races end.
//
/////////////////////////////////////////////////////
public class Server {
	// a shared area where we get the POST data and then use it in the other handler
    static String sharedResponse = "";
    static boolean gotMessageFlag = false;
    static ArrayList<Racer> laneOneRacers = new ArrayList<Racer>();
    static ArrayList<Racer> laneTwoRacers = new ArrayList<Racer>();
    static ArrayList<Racer> clientRacers = new ArrayList<Racer>();

    /**
     * Main sets up GUI, server, and display style.
     * 
     * @author Philip K
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
    	//open up that goey gui
    	JFrame frame;
		JPanel panel;
		JTextArea text;
		frame = new JFrame ("Results Server");
		panel = new JPanel();
		text = new JTextArea("Server running. Close window to end server operations.");
		
		text.setEditable(false);
		panel.add(text);
		frame.add(panel);
		frame.setSize(350,75);
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set up a simple HTTP server on our local host
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // create a context to get the request to display the results
        server.createContext("/style.css", new CSSHandler());
        server.createContext("/displayresults", new DisplayHandler());
        
        
        // create a context to get the request for the POST
        server.createContext("/sendresults",new PostHandler());
        server.setExecutor(null); // creates a default executor

        // get it going
        System.out.println("Starting Server...");
        server.start();
    }
    
    
    /**
     * Initializes the Racer lists from file "racers.txt"
     * racers.txt MUST be of the format "[BIB NUM] [LNAME] [FNAME]"
     * with one racer per line
     * 
     * @author Philip K
     */
    static void initializeRacers(){
    	laneOneRacers = new ArrayList<Racer>();
    	laneTwoRacers = new ArrayList<Racer>();
    	Scanner scan = null;
    	try{	
			scan = new Scanner(new File("racers.txt"));
		}
		catch(FileNotFoundException e){return;}
    	
    	int bibNum;
    	String last = "";
    	String first = "";
    	while(scan.hasNext()){
    		String[] params = scan.nextLine().split(" ");
    		bibNum = Integer.parseInt(params[0]);
    		last = params[1];
    		first = params[2];
    		laneOneRacers.add(new Racer(bibNum, "", -1, last, first));
    	}
    	
    }
    
    /**
     * Handler for /displayresults
     * 
     * @author Philip K
     */
    static class DisplayHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            String response = "Begin of response\n";
			Gson g = new Gson();
			
			// set up the header
            System.out.println(response);
            
        	initializeRacers();
        	
        	//two lane race
        	if(!clientRacers.isEmpty() && clientRacers.get(0).number < 0){
        		int i = 1;
        		while(clientRacers.get(i).number > 0){
        			boolean exists = false;
        			for(Racer j: laneOneRacers){
        				if(clientRacers.get(i).number == j.number){
        					j.raceTimeString = clientRacers.get(i).raceTimeString;
        					j.raceTimeLong = clientRacers.get(i).raceTimeLong;
        					exists = true;
        					break;
        				}
        			}
        			if(!exists){
        				laneOneRacers.add(clientRacers.get(i));
        			}
        			i++;
        		}
        		
        		i++;
        		while(i < clientRacers.size()){
        			boolean exists = false;
        			for(Racer j: laneOneRacers){
        				if(clientRacers.get(i).number == j.number){
        					Racer temp = j;
        					laneOneRacers.remove(j);
        					temp.raceTimeString = clientRacers.get(i).raceTimeString;
        					temp.raceTimeLong = clientRacers.get(i).raceTimeLong;
        					laneTwoRacers.add(temp);
        					exists = true;
        					break;
        				}
        			}
        			if(!exists){
        				laneTwoRacers.add(clientRacers.get(i));
        			}
        			i++;
        		}		
        		
        	}
        	else{
        		//one lane race
        		for(Racer i: clientRacers){
                	boolean exists = false;
                	for(Racer j: laneOneRacers){
                		if(i.number == j.number){
                			j.raceTimeString = i.raceTimeString;
                			j.raceTimeLong = i.raceTimeLong;
                			exists = true;
                			break;
                		}
                	}
                	if(!exists){
                		laneOneRacers.add(i);
                	}
                }
        	}
        	
        	
        	String htmlOut = "";
        	if(laneTwoRacers.isEmpty()){
        		Collections.sort(laneOneRacers);
                htmlOut = generateHTML(laneOneRacers);
        	}
        	else{
        		Collections.sort(laneOneRacers);
        		Collections.sort(laneTwoRacers);
                htmlOut = generateHTML(laneOneRacers, laneTwoRacers);
        	}
			
            response += "End of response\n";
            System.out.println(response);
            // write out the response
            t.sendResponseHeaders(200, htmlOut.length());
            OutputStream os = t.getResponseBody();
            os.write(htmlOut.getBytes());
            os.close();
        }

        /**
         * Helper function to generate HTML for display
         * 
         * @author Phil
         * @param list of racers
         * @return Formatted HTML doc
         */
		public String generateHTML(ArrayList<Racer> list){
			String html;
			html = "<!DOCTYPE html><html><head><link href=\"https://fonts.googleapis.com/css?family=Indie+Flower\" rel=\"stylesheet\"><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"></head><body>"
					+ "<table style=\"width:40%\"><caption style=\"text-align:center\">Race Results</caption><tr><th>Place</th><th>Bib Number</th><th>Last Name</th>"
					+ "<th>First Initial</th><th>Time</th></tr>";

			int i = 1;
			for(Racer r: list){
				html += "<tr><td>" + i + "</td><td>" + r.number + "</td><td>" + r.last + "</td><td>" + r.first + "</td><td>" 
						+ (r.raceTimeLong < 0 ? "<div id=\"DNF\">DNF</div>": r.raceTimeString) + "</td></tr>";
				i++;
			}
        	html+= "</table></body></html>";

        	return html;
        }
		
		/**Overload of generateHTML for two lane races
		 * 
		 * 
		 * @author Philip K.
		 * @param listOne: list of Racers in lane one
		 * @param listTwo: list of Racers in lane two
		 * @return Formatted HTML doc
		 */
		public String generateHTML(ArrayList<Racer> listOne, ArrayList<Racer> listTwo){

			String html = "<!DOCTYPE html><html><head><link href=\"https://fonts.googleapis.com/css?family=Indie+Flower\" rel=\"stylesheet\"><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"></head><body>"
					+ "<table style=\"width:40%\"><caption style=\"text-align:center\">Race Results<br>Lane One</caption><tr><th>Place</th><th>Bib Number</th><th>Last Name</th>"
					+ "<th>First Initial</th><th>Time</th></tr>";

			int i = 1;
			for(Racer r: listOne){
				html += "<tr><td>" + i + "</td><td>" + r.number + "</td><td>" + r.last + "</td><td>" + r.first + "</td><td>" 
						+ (r.raceTimeLong < 0 ? "<div id=\"DNF\">DNF</div>": r.raceTimeString) + "</td></tr>";
				i++;
			}

			html += "</table><br><table style=\"width:40%\"><caption style=\"text-align:center\">Lane Two</caption><tr><th>Place</th><th>Bib Number</th><th>Last Name</th>"
					+ "<th>First Initial</th><th>Time</th></tr>";

			i = 1;
			for(Racer r: listTwo){
				html += "<tr><td>" + i + "</td><td>" + r.number + "</td><td>" + r.last + "</td><td>" + r.first + "</td><td>" 
						+ (r.raceTimeLong < 0 ? "<div id=\"DNF\">DNF</div>": r.raceTimeString) + "</td></tr>";
				i++;
			}

			html+= "</table></body></html>";
			return html;
		}
    }
    
    
    /**
     * Handler to generate CSS formatting for /displayresults
     * 
     * @author Philip K.
     */
    static class CSSHandler implements HttpHandler {
    	public void handle(HttpExchange exchange) throws IOException {
    	
			//File file = new File("style.css");
    		Scanner in = new Scanner(new File("style.css"));
    		String cssOut = "";
			while(in.hasNext()){
				cssOut += in.nextLine();
			}
			exchange.sendResponseHeaders(200, cssOut.length());
            OutputStream os = exchange.getResponseBody();
            os.write(cssOut.getBytes());
            os.close();
    	}
    }
    
    /**
     * Handler to receive Race results on /sendresults
     * 
     * @author Philip K.
     */
    static class PostHandler implements HttpHandler {
        public void handle(HttpExchange transmission) throws IOException {
        	clientRacers = new ArrayList<Racer>();

            //  shared data that is used with other handlers
            sharedResponse = "";

            // set up a stream to read the body of the request
            InputStream inputStr = transmission.getRequestBody();

            // set up a stream to write out the body of the response
            OutputStream outputStream = transmission.getResponseBody();

            // string to hold the result of reading in the request
            StringBuilder sb = new StringBuilder();

            // read the characters from the request byte by byte and build up the sharedResponse
            int nextChar = inputStr.read();
            while (nextChar > -1) {
                sb=sb.append((char)nextChar);
                nextChar=inputStr.read();
            }

            // create our response String to use in other handler
            sharedResponse = sharedResponse+sb.toString();

            // respond to the POST with ROGER
            String postResponse = "ROGER JSON RECEIVED";
            ArrayList<Racer> temp = deJSON(sb.toString());
            clientRacers.addAll(temp);
            System.out.println("response: " + sharedResponse);
            String consoleText = "";
            for (int i =0; i < temp.size(); i++) {
            	consoleText += temp.get(i).toString() + "\n";
            }
            //Desktop dt = Desktop.getDesktop();
            //dt.open(new File("raceresults.html"));

            // assume that stuff works all the time
            transmission.sendResponseHeaders(300, postResponse.length());

            // write it and return it
            outputStream.write(postResponse.getBytes());
            
            inputStr.close();
            outputStream.close();
        }
        
        
        /**
         * Helper method to conver JSON documents to ArrayLists
         * 
         * @author Philip K.
         * @param json
         * @return ArrayList containing JSON entities
         */
    	private static ArrayList<Racer> deJSON(String json) {
    		Type listType = new TypeToken<ArrayList<Racer>>() {}.getType();
    		Gson g = new Gson();
    		ArrayList<Racer> temp = g.fromJson(json, listType);
    		return temp;
    	}
    }

}
