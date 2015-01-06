package controllers;

import java.util.List;

import models.Message;
import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	// ルートにアクセスした際のAction
	public static Result index() {
		List<Message> datas = Message.find.all();
		return ok(index.render("データベースのサンプル", datas));
	}

	// 新規作成フォームのAction
	public static Result add() {
		Form<Message> f = new Form(Message.class);
		return ok(add.render("投稿フォーム", f));
	}

	// /createにアクセスした際のAction
	public static Result create() {
		Form<Message> f = new Form(Message.class).bindFromRequest();
		if (!f.hasErrors()) {
			Message data = f.get();
			data.save();
			return redirect("/");
		} else {
			return badRequest(add.render("ERROR", f));
		}
	}

	// /itemにアクセスした際のAction
	public static Result setitem() {
		Form<Message> f = new Form(Message.class);
		return ok(item.render("ID番号を入力。", f));
	}

	// /editにアクセスした際のAction
	public static Result edit() {
		Form<Message> f = new Form(Message.class).bindFromRequest();
		if (!f.hasErrors()) {
			Message obj = f.get();
			Long id = obj.id;
			obj = Message.find.byId(id);
			if (obj != null) {
				f = new Form(Message.class).fill(obj);
				return ok(edit.render("ID=" + id + "の投稿を編集。", f));
			} else {
				return ok(item.render("ERROR:IDの投稿が見つかりません。", f));
			}
		} else {
			return ok(item.render("ERROR:入力に問題があります。", f));
		}
	}

	// /updateにアクセスした際のAction
	public static Result update() {
		Form<Message> f = new Form(Message.class).bindFromRequest();
		if (!f.hasErrors()) {
			Message date = f.get();
			date.update();
			return redirect("/");
		} else {
			return ok(item.render("ERROR:再度入力してください・", f));
		}
	}

}
