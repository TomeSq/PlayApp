package models;

import javax.persistence.Entity;
import javax.persistence.Id;

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

	public static Finder<Long, Member> find = new Finder<Long, Member>(
			Long.class, Member.class);

	@Override
	public String toString() {
		return ("[id" + id + ", name:" + name + ", mail:" + mail +
				", tel:" + tel + "]");
	}

	public static Member findByName(String input){
		return Member.find.where().eq("name", input).findList().get(0);
	}
}
