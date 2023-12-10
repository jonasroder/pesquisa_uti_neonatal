import { setNotification } from "@/plugins/notificationService";
export const handleApiError = (e, defaultErrorMessage = "Ocorreu um erro desconhecido.") => {
	const errorMessage = e.response?.data?.message || defaultErrorMessage;

	setNotification(`Error: ${errorMessage}`, "error");

	return {error: true, message: errorMessage};
}
