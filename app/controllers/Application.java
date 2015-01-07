package controllers;

import java.util.*;

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
		Form<SampleForm> form = new Form(SampleForm.class);
		return ok(index.render("please set form.", form));
	}

	// POST送信された時のAction
	public static Result send(){
		Form<SampleForm> f = new Form(SampleForm.class).bindFromRequest();
		if(!f.hasErrors()){
			SampleForm sf = f.get();
			String res = "value: ";
			for(String s : sf.inputs){
				res += " " + s;
			}
			sf.inputs.add("");
			return ok(index.render(res, f));
		} else {
			return badRequest(index.render("ERROR", f));
		}
	}
}
