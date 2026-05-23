import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { ref } from 'vue';

let stompClient = null;

export const lastDownloadNotification = ref(null);

export const connectWebSocket = () => {
    stompClient = new Client({
        webSocketFactory: () => new SockJS('/ws'),
        reconnectDelay: 5000,
        onConnect: () => {
            console.log('[WS] Conectado ao broker STOMP');
            stompClient.subscribe('/topic/downloads', (frame) => {
                console.log('[WS] Mensagem recebida:', frame.body);
                lastDownloadNotification.value = JSON.parse(frame.body);
            });
        },
        onDisconnect: () => console.log('[WS] Desconectado'),
        onStompError: (frame) => console.error('[WS] Erro STOMP:', frame.headers['message']),
        onWebSocketError: (event) => console.error('[WS] Erro WebSocket:', event),
    });

    stompClient.activate();
};

export const disconnectWebSocket = () => {
    stompClient?.deactivate();
    stompClient = null;
};
