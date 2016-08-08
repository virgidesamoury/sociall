/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.push;

import be.isl.desamouryv.sociall.ui.MessageController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Virginie
 */
//@PushEndpoint("/conversation")
public class ConversationResource {

    private static final Logger logger = Logger.getLogger(MessageController.class.getName());

//    @PathParam("convId")
    String convId;

//    @OnOpen
    public void onOpen() {
        System.out.println("onOpen");
    }

//    @OnMessage(encoders = {JSONEncoder.class})
    public Object onMessage(Object conversationId) {
        System.out.println("onMessage");
        logger.log(Level.INFO, "onMessage");
        logger.log(Level.INFO, "pathParam: {0}", convId);
        logger.log(Level.INFO, "conversationId: {0}", conversationId);
        return conversationId;
    }
}
