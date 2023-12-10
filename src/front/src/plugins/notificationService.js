import { toast } from 'vue3-toastify';

export function setNotification(message, type = 'warning') {
	toast(message, {
		type: type, // Pode ser 'success', 'info', 'warning', 'error', ou 'default'
		position: 'top-right', // Pode ser 'top', 'bottom', 'top-right', etc.
		timeout: 5000, // Tempo em milissegundos até a notificação desaparecer
		showProgressBar: true, // Mostra a barra de progresso
		closeOnClick: true, // Fecha a notificação ao clicar
		pauseOnFocusLoss: false, // Mantém a notificação ao trocar de aba
		pauseOnHover: true, // Pausa o tempo de desaparecimento ao passar o mouse
		draggable: true, // Permite arrastar a notificação
		draggablePercent: 0.6, // Percentual do arrasto necessário para descartar
		showCloseButtonOnHover: true, // Mostra o botão de fechar ao passar o mouse
		hideProgressBar: false, // Esconde a barra de progresso
		closeButton: true, // Pode ser um elemento ou texto para o botão de fechar
		icon: true, // Mostra o ícone na notificação
	});
}
