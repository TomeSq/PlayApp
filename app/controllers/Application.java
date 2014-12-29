package controllers;

import java.util.List;

import models.Message;
import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	List<Message> datas = Message.find.all();
    	return ok(index.render("データベースのサンプル", datas));
    }
    
    public static Result add(){
    	Form<Message> f = new Form(Message.class);
    	return ok(add.render("投稿フォーム", f));
    }
    
    public static Result create(){
    	Form<Message> f = new Form(Message.class).bindFromRequest();
    	if(!f.hasErrors()){
    		Message data = f.get();
    		data.save();
    		return redirect("/");
    	} else {
    		return badRequest(add.render("Error", f));
    	}
    }
    
}
