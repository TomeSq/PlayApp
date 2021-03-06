package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.Validator;
import play.db.ebean.Model;
import play.libs.F;
import play.libs.F.Tuple;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
public class Message extends Model {
	@Id
	public Long id;

	@Required(message="必須項目です。")
	@MinLength(5)
	@MaxLength(255)
	public String name;

	@Required(message="必須項目です。")
	@MinLength(5)
	@MaxLength(1024)
	public String message;

	@CreatedTimestamp
	public Date postdate;

	@ManyToOne(cascade=CascadeType.ALL)
	public Member member;

	public static Finder<Long, Message> find = new Finder<Long, Message>(
			Long.class, Message.class);

	@Override
	public String toString() {
		return ("[id" + id + ", member:" + member +
				", date:" + postdate + "]");
	}

	public static Message findByName(String input){
		return Message.find.where().eq("name", input).findList().get(0);
	}

	// 独自バリデーション
	public static class IsUrl extends Validator<String>{

		@Override
		public boolean isValid(String s) {
			return s.toLowerCase().startsWith("http://");
		}

		@Override
		public Tuple<String, Object[]> getErrorMessageKey() {
			return new F.Tuple<String, Object[]>("error.isvalid", new String[]{});
		}
	}


}
