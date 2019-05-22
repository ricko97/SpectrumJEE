package enterpriseServices;

import java.util.List;

import javax.ejb.Remote;

import entities.Event;

@Remote
public interface EventDaoRemote {
	public void addEvent(Event e);
	public void updateEvent(Event e);
	public Event findEvent(int id);
	public void deleteEvent(Event e);
	public List<Event> addEvent();

}
