import axios from "axios";
import { setNotification } from "@/service/notificationService";

export const serviceAuthenticateTeste = async () => {
	try {
		const res = await axios.post('/api/pessoa/teste');
		return res.data;

	} catch (e) {
		console.error(e);
		setNotification("Ocorreu um erro durante a autenticação." + e, "error");
	}
};
