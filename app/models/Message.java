package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.annotation.*;

import play.data.validation.Constraints.*;
import play.db.ebean.Model;

@Entity
public class Message extends Model {
	@Id
	public Long id;
	@Required
	public String name;

	public String mail;
	@Required
	public String message;
	@CreatedTimestamp
	public Date postdate;

	public static Finder<Long, Message> find = new Finder<Long, Message>(
			Long.class, Message.class);

	@Override
	public String toString() {
		return ("[id" + id + ", name:" + name + ", mail:" + mail + ", message:"
				+ message + ", date:" + postdate + "]");
	}
}
