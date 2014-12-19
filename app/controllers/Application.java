package controllers;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    // Form用の内部クラス
    public static class SampleForm{
        public String message;
    }
        
    // ルートにアクセスした際のAction
    public static Result index(){
        return ok(index.render("何か書いて！", new Form(SampleForm.class)));
    }

    // /sendにアクセスした際のAction
    public static Result send() {
        Form<SampleForm> f = form(SampleForm.class).bindFromRequest();
        if(!f.hasErrors()){
            SampleForm data = f.get();
            String msg = "you typed: " + data.message;
            return ok(index.render(msg,f));
        } else {
            return badRequest(index.render("ERROR", form(SampleForm.class)));
        }
    }

}
