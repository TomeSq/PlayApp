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
		return ok(index.render("please set form.", msgs));
	}

	// JSONデータの作成
	public static Result ajax(){
		String input = request().body().asFormUrlEncoded().get("input")[0];
		Member mem = Member.findByName(input);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String str = "<xml><root><err>ERROR!</err></root>";
		Document doc = null;
		try{
			doc = factory.newDocumentBuilder().newDocument();
			Element root = doc.createElement("data");

			// name情報の設定
			Element el = doc.createElement("name");
			el.appendChild(doc.createTextNode(mem.name));
			root.appendChild(el);

			// mail情報の設定
			el = doc.createElement("mail");
			el.appendChild(doc.createTextNode(mem.mail));
			root.appendChild(el);

			// tal情報の設定
			el = doc.createElement("tel");
			el.appendChild(doc.createTextNode(mem.tel));
			root.appendChild(el);

			doc.appendChild(root);
			TransformerFactory tfactory = TransformerFactory.newInstance();
			StringWriter writer = new StringWriter();
			StreamResult stream = new StreamResult(writer);
			Transformer trans = tfactory.newTransformer();
			trans.transform(new DOMSource(doc.getDocumentElement()), stream);
			str = stream.getWriter().toString();
		} catch(ParserConfigurationException e){
			e.printStackTrace();
		} catch(TransformerConfigurationException e){
			e.printStackTrace();
		} catch(TransactionException e){
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		if(doc == null){
			return badRequest(str);
		} else{
			return ok(str);
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
