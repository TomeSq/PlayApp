package controllers;

import models.*;
import views.html.*;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	public static Result index(){
		return ok("<HTML> <body> <h1>HELLO!</h1> <p>This is test.</p> </body> </HTML>").as("text/html");
	}
}
