package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Event;

import java.util.List;
import java.util.ArrayList;

public class EventDAO {
    private static ArrayList<Event> events = new ArrayList<>();

    //add event
    public void addEvent(Event event) {
        events.add(event);
        System.out.println("Event added successfully");
    }

    //delete event
    public void deleteEvent(Event event){
        events.remove(event);
        System.out.println("Event deleted successfully");
    }

    //edit/update event
    public void editEvent(Event updatedEvent){
        for (Event e : events) {
            if(e.getEventCode().equals(updatedEvent.getEventCode())){
                e.setEventName(updatedEvent.getEventName());
                e.setEventDescription(updatedEvent.getEventDescription());
                e.setEventHeaderImage(updatedEvent.getEventHeaderImage());
                e.setEventLocation(updatedEvent.getEventLocation());
                e.setEventDateTime(updatedEvent.getEventDateTime());
                e.setEventCapacity(updatedEvent.getEventCapacity());
                e.setEventCost(updatedEvent.getEventCost());
                e.setRegisteredStudents(updatedEvent.getRegisteredStudents());
            }
        }
    }

    //find event by name
    public Event findEventByName(String eventName){
        return events.stream()
                .filter(event -> event.getEventName().equalsIgnoreCase(eventName))
                .findFirst()
                .orElse(null);
    }

    //get all events
    public List<Event> getAllEvents(){
        return events;
    }


    public void clearEvents(){
        events.clear();
    }
}
