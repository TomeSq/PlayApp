
import java.util.*;

import com.avaje.ebean.*;

import models.*;

import play.*;
import play.libs.Yaml;

public class Global extends GlobalSettings {
	@Override
	public void onStart(Application app){
		insert(app);
	}

	@SuppressWarnings("unchecked")
	private void insert(Application app) {
		Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("test-data.yml");
		String mapKeys = "";
		for(String key : all.keySet()){
			mapKeys += "val" + all.get(key) +",";
		}

		Ebean.save(all.get("messages"));
		Ebean.save(all.get("members"));
		for(Object message : all.get("messages")){
			Message tartget = Message.find.byId(((Message)message).id);
			tartget.member = Member.findByName(tartget.name);
			tartget.update();
		}

	}
}
