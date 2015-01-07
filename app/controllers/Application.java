package controllers;

import java.util.*;

import models.*;
import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	// フォーム管理用クラス
	public static class SampleForm{
		public List<String> inputs;
	}

	// ルートにアクセスした際のAction
	public static Result index(){
		List<Message> msgs = Message.find.all();
		return ok(index.render("please set form.", msgs));
	}
	// Message Action =================
	// 新規投稿フォームのAction
	public static Result add() {
		Form<Message> f = new Form(Message.class);
		List<Member> mems =Member.find.select("name").findList();
		return ok(add.render("投稿フォーム", f, mems));
	}

	// /createにアクセスした際のAction
	public static Result create() {
		/*
		Form<Message> f = new Form(Message.class).bindFromRequest();
		if (!f.hasErrors()) {
			Message data = f.get();
			data.member = Member.findByName(data.name);
			data.save();
			return redirect("/");
		} else {
			return badRequest(add.render("ERROR", f));
		}
		*/
		return redirect("/");
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
