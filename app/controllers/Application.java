package controllers;

import java.io.StringWriter;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.ehcache.transaction.TransactionException;

import org.w3c.dom.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

import models.*;
import play.*;
import play.data.*;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Result;
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
		return ok(index.render("投稿メッセージ", msgs));
	}

	// Message Action =================
	// 新規投稿フォームのAction
	public static Result add() {
		Form<Message> f = new Form<Message>(Message.class);
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
