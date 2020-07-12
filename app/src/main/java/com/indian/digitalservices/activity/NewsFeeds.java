package com.indian.digitalservices.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import com.indian.digitalservices.R;
import com.indian.digitalservices.adapter.NavigationAdapter;
import com.indian.digitalservices.widget.Utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;


public class NewsFeeds extends AppCompatActivity {

    // Tag name for log data.
    private static final String TAG_XML_PULL_PARSER = "XML_PULL_PARSER";
    // Message command for activity handler to show xml parse result.
    private static final int MESSAGE_SHOW_XML_PARSE_RESULT = 1;
    // Message data bundle key to save xml parsed result.
    private static final String KEY_XML_PARSE_RESULT = "KEY_XML_PARSE_RESULT";
    // Parse xml use XmlPullParser button.
    private Button parseXmlUsePullButton = null;
    // Display xml parse result text view.
    private TextView showXmlParseResultTextView = null;
    // This handler is waiting for child thread message to display xml parse result in text view.
    private Handler showParseResultHandler = null;
    // OkHttpClient to read xml file from url.
    private OkHttpClient okHttpClient = null;
    private NavigationAdapter mAdapter;
    // This is xml file url.
    private String xmlFileUrl = "https://www.amarujala.com/rss/uttar-pradesh.xml";
    ImageView back_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds);
        Utils.darkenStatusBar(this, R.color.green);
        setTitle("dev2qa.com - Android XmlPullParser Example.");

        back_img = findViewById(R.id.back_button);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Init all ui controls first.
        initControls();


        if(URLUtil.isHttpUrl(xmlFileUrl) || URLUtil.isHttpsUrl(xmlFileUrl)) {
            // Create a OkHttpClient request builder.
            Request.Builder builder = new Request.Builder();
            // Set xml file url.
            builder  = builder.url(xmlFileUrl);
            // Build http request.
            Request request = builder.build();
            // Create a OkHttp3 Call object.
            Call call = okHttpClient.newCall(request);
            // Execute the get xml file request asynchronously in an automate child thread.
            call.enqueue(new Callback() {
                // If http get request fail.
                @Override
                public void onFailure(Call call, IOException e) {
                    sendXmlParseResultToActivityHandler(e.getMessage());
                }

                // If http get request success.
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // If server response success.
                    if(response.isSuccessful()) {
                        // Get response return xml string.
                        String resultXml = response.body().string();
                        // Parse the xml string.
                        String xmlParseResult = parseXmlUsePullParser(resultXml);
                        // Send message to activity main thread Handler to show the xml parsed result.
                        sendXmlParseResultToActivityHandler(xmlParseResult);
                    }
                }
            });
        }
    }

    /* Send message to activity main thread Handler to display xml parse result. */
    private void sendXmlParseResultToActivityHandler(String xmlParseResult) {
        // Create message object.
        Message msg = new Message();
        msg.what = MESSAGE_SHOW_XML_PARSE_RESULT;

        // Add error message in message object data.
        Bundle bundle = new Bundle();
        bundle.putString(KEY_XML_PARSE_RESULT, xmlParseResult);
        msg.setData(bundle);

        // Send message to activity ui update Handler.
        showParseResultHandler.sendMessage(msg);
    }


    /* Parse xml string use XmlPullParser.Return parsed string. */
    private String parseXmlUsePullParser(String xmlString) {
        StringBuffer retBuf = new StringBuffer();
        try {
            // Create xml pull parser factory.
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            // Create XmlPullParser.
            XmlPullParser xmlPullParser = parserFactory.newPullParser();
            // Create a new StringReader.
            StringReader xmlStringReader = new StringReader(xmlString);
            // Set the string reader as XmlPullParser input.
            xmlPullParser.setInput(xmlStringReader);
            // Get event type during xml parse.
            int eventType = xmlPullParser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                // Get xml element node name.
                String nodeName = xmlPullParser.getName();

                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("description".equalsIgnoreCase(nodeName) /*|| "link".equalsIgnoreCase(nodeName)*/ || "title".equalsIgnoreCase(nodeName)) {
                            //retBuf.append(nodeName);
                            // Get xml element text value.
                            String value = xmlPullParser.nextText();

                            StringTokenizer stknzr = new StringTokenizer(value, " ");
                            StringBuffer sb = new StringBuffer();
                            while(stknzr.hasMoreElements()) {
                                sb.append(stknzr.nextElement()).append(" ");
                            }

                            Log.d(TAG_XML_PULL_PARSER, "element text : " + value);

                            //retBuf.append(" : ");
                            retBuf.append(sb);

                            retBuf.append("\n\r\n");
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);
                        if("employee".equalsIgnoreCase(nodeName))
                        {
                            retBuf.append("************************\r\n\r\n");
                        }
                    }
                }

                eventType = xmlPullParser.next();
            }
        }catch(XmlPullParserException ex)
        {
            // Add error message.
            retBuf.append(ex.getMessage());
        }finally {
            return retBuf.toString();
        }
    }

    // Initialize ui controls.
    @SuppressLint("HandlerLeak")
    private void initControls()
    {
        if(parseXmlUsePullButton == null)
        {
            //parseXmlUsePullButton = (Button)findViewById(R.id.xml_pull_parser_parse_button);
        }

        if(showXmlParseResultTextView == null)
        {
            showXmlParseResultTextView = (TextView)findViewById(R.id.xml_parse_result_text_view);
        }

        if(showParseResultHandler == null)
        {
            // This handler waiting for message from activity child thread.
            showParseResultHandler = new Handler() {

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void handleMessage(Message msg) {
                    // If the message want to display xml parse result.
                    if(msg.what == MESSAGE_SHOW_XML_PARSE_RESULT) {
                        // Get message bundle data.
                        Bundle bundle = msg.getData();
                        // Get xml parse result.
                        final String xmlParseResult = bundle.getString(KEY_XML_PARSE_RESULT);
                        // Show the result in text view.
                        showXmlParseResultTextView.setText(xmlParseResult);
                        showXmlParseResultTextView.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
                    }
                }
            };
        }

        if(okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
    }
}