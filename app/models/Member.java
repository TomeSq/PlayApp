package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Member extends Model {
	@Id
	public Long id;

	@Required(message="必須項目です。")
	@MinLength(5)
	@MaxLength(255)
	public String name;

	@Email(message="メールアドレスを記入ください。")
	public String mail;

	@Pattern(message="半角数値のみで記入してください。(-[ハイフン]不要)", value="[0-9]+")
	public String tel;

	@ManyToMany
	public List<Message> messages = new ArrayList<Message>();

	public static Finder<Long, Member> find = new Finder<Long, Member>(
			Long.class, Member.class);

	@Override
	public String toString() {
		String ids = "{id:";
		for(Message m: messages){
			ids += " " + m.id;
		}
		ids += "}";

		return ("[id" + id + ", message:" + ids +
				", name:" + name + ", mail:" + mail +
				", tel:" + tel + "]");
	}

	public static Member findByName(String input){
		return Member.find.where().eq("name", input).findList().get(0);
	}
}
