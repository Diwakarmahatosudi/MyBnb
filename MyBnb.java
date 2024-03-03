/**
Date: 10/04/2023
Course: CSCI 2073
Description: This program reads informations from a file and manages the rental rooms services.
   it provides with the no of rooms available with the no of staffs needed for the service.
   It updates if room is taken and make the room unavailable and can be used as a great rental room service program. 


On my honor, I have neither given nor received unauthorized help while
completing this assignment.

Name and CWID.         Diwakar Mahato Sudi    30155595

*/

import java.util.*;

import java.io.*;



/**
   MyBnb manages rental rooms 
   It reads room information from a file and manage rental rooms
   Each line in the file contains room information in the form of id, beds

   @param filename  The name of the file that contains room information

   @throws  FileNotFoundException  if the desiared file doesnot exist then it throws this error
*/
public class MyBnb {

    List<RentalRoom> rentalRooms = new ArrayList<>();

    public MyBnb(String filename) {
    
        
       
        try (Scanner fileSc = new Scanner(new File(filename))) {
            
                     
            while (fileSc.hasNextLine()) {
               
                String room = fileSc.nextLine();
               
                Scanner secfileSc = new Scanner(room);
               
                String id = secfileSc.next();
               
                int bed = Integer.parseInt(secfileSc.next());
                
                RentalRoom newRoom = new RentalRoom(id, bed);
                
                rentalRooms.add(newRoom);
           
            }
            
        } catch (FileNotFoundException e) {

        }
    }
    
    
    
/**
   it calculates and returns the number of rental rooms available 
   with the specified number of beds .

   param beds   The number of beds to look for in the rooms available 

   @return  it returns the number of rental rooms available with the bed count
*/    
    
    public int numberOfRooms(int beds) {
    
    int availability = 0;
    
    for (int i = 0; i < rentalRooms.size(); i++) {
    
        if (rentalRooms.get(i).getNumBeds() == beds) {
    
            availability++;
        }
    }
    return availability;
}    
    

/**
   This method chooses a rental room based on the number of min beds asked 
   
   It goes through all the rental rooms and when matched with the asked no of beds
   returns its ID and mark that room unavailable
   
   @param  minBeds  The minimum no of beds required to choose a rental room
   
   @return  ID   it returns the id of the room choosen or "NONE" if no rooms are available
   
*/


    public String chooseRoom(int minBeds) {
   
    for (int i = 0; i < rentalRooms.size(); i++) {
   
        RentalRoom room = rentalRooms.get(i);
   
        if (room.isAvailable() && room.getNumBeds() >= minBeds) {
   
            room.setAvailable(false); 
   
            return room.getID();
        } 
    }
    return "NONE"; 
}    
    
    

/**
   it calculated the number of staffs required based on the availability of rooms
   it goes through the rental rooms verify no of stafs required and returns the count.
   
   two staffs are needed to clean each room with three or more beds
   one staff is needed for each group of three one bed rooms 
   
   @return it returns the no of staff required for rental rooms

*/

    public int numberOfStaffNeeded() {
   
    int noOfStaffAvai = 0;
   
    int roomWithOneBed = 0;

    for (int i = 0; i < rentalRooms.size(); i++) {
   
        RentalRoom room = rentalRooms.get(i);

        if (!room.isAvailable()) {
   
            if (room.getNumBeds() >= 3) {
   
                noOfStaffAvai += 2;
   
            } else if (room.getNumBeds() >= 1) {
   
                roomWithOneBed++;
            }
        }
    }

    noOfStaffAvai += (roomWithOneBed + 2) / 3;

    return noOfStaffAvai;
}
    
}