package flight_ticket_booking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
public class flight_booking {
	
	public static void listFlightDetails(String [] flights) {
		
		System.out.println("The total number of flights:"+flights.length);
		 
		for (String flight : flights) {
	            String[] details = flight.split("-");
	           
	            if(details.length>=4) {
	            HashMap<String, String> flightDetails = new HashMap<>();
	            
	            flightDetails.put("number", details[1]);
	            flightDetails.put("source", details[2]);
	            flightDetails.put("destination", details[3]);
	           
	            String flightNumber = flightDetails.get("number");
	            String source = flightDetails.get("source");
	            String destination = flightDetails.get("destination");
	           
	            System.out.println("Flight Number: " + flightNumber);
	            System.out.println("Source: " + source);
	            System.out.println("Destination: " + destination);
	            System.out.println("*******************************");
	            }
	        }
			 
	}
	
	public static void searchFlights(String [] flights) {
//search flights
		 ArrayList <String> list = new ArrayList<String>();
		 
		 for (String flight : flights) {
		        list.add(flight);
		    }
		 
		 System.out.println("Enter your source and destination");
		  Scanner sc = new Scanner(System.in);
			String source = sc.nextLine();
			String destination = sc.nextLine();
			 boolean flightsFound = false;
			 
			  for (String flight : flights) 
			  {
		            String[] details = flight.split("-");
		            if(details.length>=4) {
		            HashMap<String, String> flightDetails = new HashMap<>();
		            flightDetails.put("number", details[1]);
		            flightDetails.put("source", details[2]);
		            flightDetails.put("destination", details[3]);
		           
		            String flightNumber = flightDetails.get("number");
		            String sourceLocation = flightDetails.get("source");
		            String destinationLocation = flightDetails.get("destination");

		    		if(sourceLocation.toLowerCase().contains(source) && destinationLocation.toLowerCase().contains(destination))
		    		{
		    				System.out.println("Your Search Results"+flight);
		    				
		    				 flightsFound = true;
		    			}
		            }
		        }
			  if (!flightsFound)
			  {
  	            System.out.println("No Flights Available");
  	          }
	}
	
	
	public static int handleMeals(int price,int mealsAdded) {
		
		Scanner input=new Scanner(System.in);
		
		while(true) 
		{
			   System.out.println("Do you want to add meals to your booking?\n1.Yes\n2.No\n Enter your Choice:");
			   int meals=input.nextInt();
		
			   switch(meals) 
			   {
		
			   case 1:
			
				   price +=200;
				   System.out.println("Meals is added successfuly");
				   mealsAdded=1;
				   break;
		
			   case 2:
			
				   System.out.println("Meals not added ");
				   mealsAdded=0;
				   break;
		 
			   default:
				   System.out.println("Invalid number.");
				   continue;
			   }
		 break;
		   }
		 return price;
	}
	public static String handleSeats(int flightClass,Map<String,Boolean> businessClassSeats ,Map<String,Boolean> economyClassSeats,int businessClassRows,int economyClassRows) 
	{
		  Map<String, Boolean> seats = flightClass==1 ? businessClassSeats : economyClassSeats;
		  String seatNumber;
		  Scanner input=new Scanner(System.in);
		  int maxRows = flightClass==1 ? businessClassRows : economyClassRows;
          int maxColumns = flightClass==1 ? 12 : 20;

          while (true) 
          {
              System.out.print("Enter the seat number (e.g., 5A) ");
               seatNumber = input.nextLine().toUpperCase();
              int row, column;
              try {
                  row = Integer.parseInt(seatNumber.substring(0, seatNumber.length() - 1));
                  char colChar = seatNumber.charAt(seatNumber.length() - 1);
                  column = colChar - 'A' + 1;

                  if (row < 1 || row > maxRows || column < 1 || column > maxColumns) 
                  {
                      System.out.println("Invalid seat number! Please enter a valid seat number.");
                      continue;
                  }

                  if (seats.get(seatNumber) != null && seats.get(seatNumber))
                  {
                      System.out.println("The seat is already booked.");
                  } 
                  else 
                  {
                      // Book the seat
                      seats.put(seatNumber, true);
                      System.out.println("Seat " + seatNumber + " is booked successfully!");
                  }
              } 
              catch (NumberFormatException | StringIndexOutOfBoundsException e) 
              {
                  System.out.println("Invalid seat number format! Please enter a valid seat number.");
              }
      		return seatNumber;
          }
	}
	
	
	public static void bookTickets(String[] flights)throws Exception {
		Scanner input=new Scanner(System.in);
		 boolean flightsFound = false;
		 int businessClassPrice=2000;
		 int economyClassPrice=1000;
		 int mealsAdded=0;
		 int price;
		 List<String> fileDetails = new ArrayList<>();
		 int businessClassRows = 12;
	     int economyClassRows = 20;
	     Map<String, Boolean> businessClassSeats = new HashMap<>();
	     Map<String, Boolean> economyClassSeats = new HashMap<>();
		  try {
			  System.out.println("Enter the flights number:");
			  String flightNumber=input.nextLine();
		 
			  for (int i = 1; i <= businessClassRows; i++) 
			  {
	            for (char c = 'A'; c < 'A' + 3; c++) 
	            {
	                String seatNumber = i + "" + c;
	                businessClassSeats.put(seatNumber, false);
	            }
			  }

	        for (int i = 1; i <= economyClassRows; i++) 
	        {
	            for (char c = 'A'; c < 'A' + 4; c++) 
	            {
	                String seatNumber = i + "" + c;
	                economyClassSeats.put(seatNumber, false);
	            }
	        }
	      
		Random random = new Random();
		
		   String bookingId = String.format("%04d", random.nextInt(10000));
		 	for(String flightDetails:flights) 
		 	{
				if(flightDetails.toLowerCase().contains(flightNumber)) 
				{
					 flightsFound = true;
					System.out.println("Flight Details\nFlight Name:"+flightDetails);
					fileDetails.add(flightDetails);
					break;
				}
			
			}
		 	 String fileValue = fileDetails.toString();
		 	 FileWriter fw = new FileWriter("C:\\flight_ticket_booking\\flights\\"+fileValue.substring(1, fileValue.length() - 1),true);
		     BufferedWriter  bw = new BufferedWriter(fw); 
		     PrintWriter    pw = new PrintWriter(bw);
		     pw.println("*******************\nBooking Details\nBooking ID:"+bookingId+"\nFlight Number:"+flightNumber+"\nFlight Details:"+fileDetails);
		    

		   while(true)
		   {
			   System.out.println("1.Business Class\n2.Economy Class\n Choose which class you want to travel in:");
			   int flightClass=input.nextInt();
		
			   switch(flightClass) 
			   {
			   case 1:
				   price=businessClassPrice;
				   System.out.println("Business Class\n ");
				   Map<String, Boolean> businessSeat=businessClassSeats;
				   String seatNumberBusiness= handleSeats(flightClass,businessClassSeats,economyClassSeats,economyClassRows,businessClassRows);
				   int totalCostWithMealsBusiness=handleMeals(price,mealsAdded);
				   if(mealsAdded==1) 
				   {
					   pw.println("Meals Added");
				   }
				   else 
				   {
					   pw.println("Meals not Added");
				   }
				   System.out.println("Total Cost"+totalCostWithMealsBusiness);
				   pw.println("\nClass preffered:Business Class\nSeat Number:"+seatNumberBusiness+"\nPrice:"+totalCostWithMealsBusiness);
				   break;
		case 2:
			price=economyClassPrice;
			System.out.println("Economy Class\n ");
			 Map<String, Boolean> economySeat=economyClassSeats;
			 String seatNumberEconomy= handleSeats(flightClass,businessClassSeats,economyClassSeats,economyClassRows,businessClassRows);	
			 int totalCostWithMealsEconomy=handleMeals(price,mealsAdded);
			 if(mealsAdded==1) 
			 {
					pw.println("Meals Added");
				}
				else 
				{
					pw.println("Meals not Added");
				}
			 System.out.println("Total Cost"+totalCostWithMealsEconomy);
			 pw.println("\nClass preffered:Economy Class\nSeat Number"+seatNumberEconomy+"\nPrice:"+totalCostWithMealsEconomy);	
			break;
		  default:
              System.out.println("Invalid number.");
             continue;
		}
		 break;
		   }
		   System.out.println("Flight ticket successfuly booked");		 
			   pw.close();
			    } catch (IOException e) {
			      System.out.println("An error occurred."+e); 
			    }
	}
	
	public static void displayBookings(String[] flights) throws Exception 
	{
		Scanner input=new Scanner(System.in);
		 boolean flightsFound = false;
		 List<String> fileDetails = new ArrayList<>();
		 System.out.println("Enter flight number");
		 String flightNumber=input.next();
		for(String flightDetails:flights) 
		{
			if(flightDetails.toLowerCase().contains(flightNumber)) 
			{
				 flightsFound = true;
				System.out.println("Flight Details\nFlight Name:"+flightDetails);
				fileDetails.add(flightDetails);
				break;
			}
		}
	 	 String fileValue = fileDetails.toString();
	 	 File file = new File("C:\\flight_ticket_booking\\flights\\"+fileValue.substring(1, fileValue.length() - 1));
	 	 FileReader fr = new FileReader(file);
	 	 BufferedReader br = new BufferedReader(fr);
	      String data;
	      while((data = br.readLine()) != null)
	      {
	          System.out.println(data);
	      }
	}
	
	  public static void main(String[] args) throws Exception
	    {
		  Scanner sc= new Scanner(System.in);
		  File directoryPath = new File("C:\\flight_ticket_booking\\flights");
			String[] flights = directoryPath.list();
			
		  while(true) 
		  {
			  System.out.println("*****FLIGHT BOOKING SYSTEM*****\n1.List flight details\n2.Search Flights\n3.Book a flight ticket\n4.Display flight summary\n5.Meal ordered for each flight\n Enter your option or enter 0 for 'exit' :");
			  int option=sc.nextInt();

	           if (option==0) {
	                break;
	            }
			  switch(option)
			  {
			  case 1:
			  {
			  listFlightDetails(flights);
			  break;
			  }
			  case 2:
			  {
			  searchFlights(flights);
			  break;
			  }
			  case 3:
			  {
			  bookTickets(flights);
			  break;
			  }
			  case 4:
			  {
			  displayBookings(flights);
			  break;
			  }
			  case 5:
			  {
			  break;
			  }
			  default:
              System.out.println("Invalid number.");
              continue;	
			  }
			  break;
		  }
	    }
}
