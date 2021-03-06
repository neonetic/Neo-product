package com.docdoku.server.mainchannel.modules;

import com.docdoku.server.mainchannel.MainChannelApplication;
import com.docdoku.server.mainchannel.MainChannelDispatcher;
import com.docdoku.server.mainchannel.MainChannelWebSocket;
import com.docdoku.server.mainchannel.util.ChannelMessagesType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.HashMap;

public class ChatModule {

    private ChatModule(){
    }

    public static void onChatMessage(MainChannelWebSocket socket, JSONObject jsobj) throws JSONException {

        String remoteUser = jsobj.getString("remoteUser");
        String message = jsobj.getString("message");
        String context = jsobj.getString("context");

        if(!MainChannelApplication.hasChannels(remoteUser)){
            MainChannelDispatcher.send(socket, buildChatMessageNotSentMessage(remoteUser, context));
        }else{
            MainChannelDispatcher.sendToAllUserChannels(socket.getUserLogin(),buildChatMessageACK(socket.getUserLogin(),remoteUser, context, message));
            MainChannelDispatcher.sendToAllUserChannels(remoteUser,buildChatMessage(socket.getUserLogin(), context, message));
        }

    }


    private static String buildChatMessage(String callerLogin, String context, String message) throws JSONException {
        JSONObject jsobj = new JSONObject();
        jsobj.put("type", ChannelMessagesType.CHAT_MESSAGE);
        jsobj.put("remoteUser", callerLogin);
        jsobj.put("sender",callerLogin);
        jsobj.put("message", message);
        jsobj.put("context",context);
        return jsobj.toString();
    }

    private static String buildChatMessageACK(String caller, String remoteUser, String context, String message) throws JSONException {
        JSONObject jsobj = new JSONObject();
        jsobj.put("type", ChannelMessagesType.CHAT_MESSAGE_ACK);
        jsobj.put("remoteUser", remoteUser);
        jsobj.put("sender",caller);
        jsobj.put("message", message);
        jsobj.put("context",context);
        return jsobj.toString();
    }

    private static String buildChatMessageNotSentMessage(String calleeLogin, String context) throws JSONException {
        JSONObject jsobj = new JSONObject();
        jsobj.put("type", ChannelMessagesType.CHAT_MESSAGE);
        jsobj.put("remoteUser", calleeLogin);
        jsobj.put("error", "UNREACHABLE");
        jsobj.put("context",context);
        return jsobj.toString();
    }
}
