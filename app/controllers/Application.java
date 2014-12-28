package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(
        		"これは最初の値です。",
        		"これは真ん中の引数です。",
        		"これは最後のテキストです。"));
    }
}
