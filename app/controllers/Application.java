package controllers;

import java.util.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

import models.*;
import play.*;
import play.data.*;
import play.libs.Json;
import play.mvc.*;
import scala.Tuple2;
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

	// JSONデータの作成
	public static Result ajax(){
		String input = request().body().asFormUrlEncoded().get("input")[0];
		ObjectNode result = Json.newObject();
		if(input == null){
			result.put("status", "BAD");
			result.put("message", "Can't get sending data...");
			return badRequest(result);
		} else{
			result.put("status", "OK");
			result.put("message", input);
			return ok(result);
		}
	}

	// Message Action =================
	// 新規投稿フォームのAction
	public static Result add() {
		Form<Message> f = new Form<Message>(Message.class);
		List<Member> mems = Member.find.select("name").findList();
		List<Tuple2<String,String>> opts = new ArrayList<Tuple2<String,String>>();
		for(Member mem : mems){
			opts.add(new Tuple2<String, String>(mem.name, mem.name));
		}
		return ok(add.render("投稿フォーム", f, opts));
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
//			return badRequest(add.render("ERROR", f));
			return redirect("/");
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
