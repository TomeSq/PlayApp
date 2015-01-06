package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
public class Message extends Model {
	@Id
	public Long id;

	@Required
	@MinLength(5)
	@MaxLength(255)
	public String name;

	@Email
	public String mail;

	@Required
	@MinLength(5)
	@MaxLength(1024)
	@Pattern("[a-zA-Z]+")
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
