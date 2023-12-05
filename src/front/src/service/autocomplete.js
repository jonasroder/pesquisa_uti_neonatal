import axios from "axios";
import { setNotification } from "@/service/notificationService";

export const serviceAuthenticateTeste = async (data) => {
	try {
		const res = await axios.post('/api/dictionary/autocomplete', data);
		return res.data;

	} catch (e) {
		console.error(e);
		setNotification("Ocorreu um erro durante a autenticação." + e, "error");
	}
};


