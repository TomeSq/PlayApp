package controllers;

import models.*;
import views.html.*;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	// ルートにアクセスした際のAction
	public static Result index(String msg, int id){
		return ok(index.render("引数は、" + msg + "," + id + "です。"));
	}
}
