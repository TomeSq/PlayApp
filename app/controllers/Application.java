package controllers;

import java.util.*;

import play.*;
import play.data.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
	// フォーム管理用クラス
	public static class SampleForm{
		public int number;
		public String input;
		public String pass;
		public boolean check;
		public String radio;
		public String sel;
		public String area;
		public Date date;
	}

	// ルートにアクセスした際のAction
	public static Result index(){
		SampleForm sf = new SampleForm();
		sf.radio = "windows";
		sf.check = true;
		sf.input = "default value";
		sf.sel = "uk";
		Form<SampleForm> form = new Form(SampleForm.class).fill(sf);

		return ok(index.render("please set form.", form));
	}

	// POST送信された時のAction
	public static Result send(){
		Form<SampleForm> f = new Form(SampleForm.class).bindFromRequest();
		if(!f.hasErrors()){
			SampleForm sf = f.get();
			String res = "value: ";
			res += "number=" + sf.number
				+ "input=" + sf.input
				+ ", pass=" + sf.pass
				+ ", check=" + sf.check
				+ ", radio=" + sf.radio
				+ ", sel=" + sf.sel
				+ ", area=" + sf.area
				+ ", date=" + sf.date;
			return ok(index.render(res, f));
		} else {
			return badRequest(index.render("ERROR", f));
		}
	}
}
