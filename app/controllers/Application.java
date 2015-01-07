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
}
