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
}
