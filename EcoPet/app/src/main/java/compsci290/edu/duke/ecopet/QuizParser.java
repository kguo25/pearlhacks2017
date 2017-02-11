package compsci290.edu.duke.ecopet;

/**
 * Created by Katherine on 2/11/2017.
 */

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuizParser {
    private static final String ns = null;
    public EcoQuiz parse(InputStream in) throws XmlPullParserException, IOException {
        try
        {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(in, null);
            parser.nextTag();
            return readQuiz(parser);
        }
        finally {
            in.close();
        }
    }

    public EcoQuiz readQuiz(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Question> quests = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "quiz");
        while(parser.next() != XmlPullParser.END_TAG) {
            if(parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if(name.equals("question")) {
                quests.add(readQuestion(parser));
            }
        }
        return new EcoQuiz(quests);
    }

    public Question readQuestion(XmlPullParser parser) throws XmlPullParserException, IOException {
        String q = "";
        String s = "";
        String good = "";
        String imp = "";
        List<String> choices = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "question");
        while(parser.next() != XmlPullParser.END_TAG) {
            if(parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals("text")) {
                q = readText(parser);
            }
            else if(name.equals("section")) {
                s = readText(parser);
            }
            else if(name.equals("good")) {
                good = readText(parser);
            }
            else if(name.equals("importance")) {
                imp = readText(parser);
            }
            else if(name.equals("answer")) {
                choices.add(readText(parser));
            }
        }
        return new Question(q, s, good, imp, choices);
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

}
