package controllers;

import java.util.List;

import views.html.*;
import models.Member;
import models.Message;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	// ルートにアクセスした際のAction
	public static Result index() {
		List<Message> datas  = Message.find.all();
		List<Member>  datas2 = Member.find.all();
		return ok(index.render("データベースのサンプル", datas, datas2));
	}

	// Message Action =================
	// 新規投稿フォームのAction
	public static Result add() {
		Form<Message> f = new Form(Message.class);
		return ok(add.render("投稿フォーム", f));
	}

	// /createにアクセスした際のAction
	public static Result create() {
		Form<Message> f = new Form(Message.class).bindFromRequest();
		if (!f.hasErrors()) {
			Message data = f.get();
			data.member = Member.findByName(data.name);
			data.save();
			return redirect("/");
		} else {
			return badRequest(add.render("ERROR", f));
		}
	}

	// Membet Action =================
	// メンバー作成フォームのAction
	public static Result add2() {
		Form<Member> f = new Form(Member.class);
		return ok(add2.render("メンバー登録フォーム", f));
	}

	// /create2にアクセスした際のAction
	public static Result create2() {
		Form<Member> f = new Form(Member.class).bindFromRequest();
		if (!f.hasErrors()) {
			Member data = f.get();
			data.save();
			return redirect("/");
		} else {
			return badRequest(add2.render("ERROR", f));
		}
	}
}
