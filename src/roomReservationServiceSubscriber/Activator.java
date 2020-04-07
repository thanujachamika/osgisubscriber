package roomReservationServiceSubscriber;


import java.util.Scanner;
import java.io.IOException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import roomReservationServicePublisher.RoomService;

public class Activator implements BundleActivator{

	ServiceReference serviceReference;

    public void start(BundleContext bundleContext) throws Exception{
    	
    	serviceReference = bundleContext.getServiceReference(RoomService.class.getName());
		RoomService servicePublish = (RoomService) bundleContext.getService(serviceReference);

        try{
            while (true){
            	
            	System.out.println();
            	System.out.println("=====Welcome to the Lanka Hospitals====");
            	System.out.println("View room details : Press '1'");
            	System.out.println("Book a room for a patient : Press '2'");
                System.out.println("Type as 'exit' to exit.");
                System.out.println("=======================================");
                System.out.println();
                
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter option: ");
                String option = sc.next();
                System.out.println();

                if (option.equalsIgnoreCase("exit")){
                    break;
                }
                
                else if (servicePublish == null){
                    System.out.println("No hospital room service available.");
                }

                else if (option.equalsIgnoreCase("1")){
                	servicePublish.displayRooms();
                }
                
                else if(option.equalsIgnoreCase("2")){
                	
                    String reservedRoomNumber = "";
                    System.out.print("Enter room number that you want to reserve : ");
                    reservedRoomNumber = sc.next();
                    
                    int reservedDays;
                    System.out.print("Enter no of days that you want to reserve : ");
                    reservedDays = sc.nextInt();
                    
                    System.out.println("Total cost : " +servicePublish.reserveRoom(reservedRoomNumber, reservedDays));
                }
                else {
                	System.out.println("Incorrect option");
                }
            }
        }catch (Exception ex){
        	
        }
    }


    public void stop(BundleContext context){
    	System.out.println("Hospital room reservation service subscriber stopped");
		context.ungetService(serviceReference);
    }
}