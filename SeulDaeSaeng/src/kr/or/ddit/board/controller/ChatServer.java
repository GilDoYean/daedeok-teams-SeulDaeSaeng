package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ChatingServer/{classId}/{chatId}")
public class ChatServer {

    private static Map<String, Set<Session>> roomSessions = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("classId") String classId, @PathParam("chatId") String chatId) {
        roomSessions.computeIfAbsent(classId, k -> Collections.synchronizedSet(new HashSet<>())).add(session);
        session.getUserProperties().put("chatId", chatId);
        System.out.println("웹소켓 연결: " + session.getId() + " in room " + classId);
        
     // 새로운 사용자가 입장했음을 알리기 위한 메시지 전송
        String joinMessage = chatId + "님이 입장했습니다.";
        synchronized (roomSessions.get(classId)) {
            for (Session client : roomSessions.get(classId)) {
                try {
                    client.getBasicRemote().sendText("SYSTEM|" + joinMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("classId") String classId) throws IOException {
        System.out.println("메시지 전송: " + session.getId() + " in room " + classId + ": " + message);
        Set<Session> sessions = roomSessions.get(classId);
        synchronized (sessions) {
            for (Session client : sessions) {
                if (!client.equals(session)) {
                    client.getBasicRemote().sendText(message);
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("classId") String classId) {
        Set<Session> sessions = roomSessions.get(classId);
        sessions.remove(session);
        String chatId = (String) session.getUserProperties().get("chatId");
        String leaveMessage = chatId + "님이 퇴장했습니다.";
        synchronized (roomSessions.get(classId)) {
            for (Session client : roomSessions.get(classId)) {
                try {
                    client.getBasicRemote().sendText("SYSTEM|" + leaveMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("웹소켓 종료: " + session.getId() + " in room " + classId);
        if (sessions.isEmpty()) {
            roomSessions.remove(classId);
        }
    }

    @OnError
    public void onError(Throwable e, @PathParam("classId") String classId) {
        System.out.println("에러 발생 in room " + classId);
        e.printStackTrace();
    }
}