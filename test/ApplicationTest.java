import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Member;
import models.Message;

import org.junit.Test;

import play.twirl.api.Content;
import play.data.Form;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {
	List<Member> dummy_mems = null;
	List<Message> dummy_msgs = null;

	public ApplicationTest(){
		inintialData();
	}

	// ダミーデータの準備
	public void inintialData(){
		dummy_mems = new ArrayList();
		Member member = new Member();
		member.id = 10001L;
		member.name = "dummy_name";
		member.mail = "dummy@mail";
		member.tel = "000000";
		dummy_mems.add(member);

		dummy_msgs = new ArrayList();
		Message message = new Message();
		message.id = 10002L;
		message.name = member.name;
		message.member = member;
		message.postdate = new Date();
		member.messages = new ArrayList<Message>();
		member.messages.add(message);
		dummy_msgs.add(message);
	}

    @Test
    public void renderTemplate() {
    	String msg = "テストメッセージ";
    	Content add = views.html.add.render(msg, new Form(Message.class));
        assertThat(contentAsString(add)).contains(msg);
    }

    @Test
    public void renderTemplate2() {
    	String msg = "テストメッセージ";
    	Content index = views.html.index.render(msg, dummy_msgs);
    	assertThat(contentType(index)).isEqualTo("text/html");
    	assertThat(contentAsString(index)).contains(msg);
    	assertThat(contentAsString(index)).contains(dummy_msgs.get(0).message);
    }


}
